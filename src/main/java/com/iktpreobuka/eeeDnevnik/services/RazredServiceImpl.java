package com.iktpreobuka.eeeDnevnik.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktpreobuka.eeeDnevnik.entities.NastavnikPredmetOdeljenjeEntity;
import com.iktpreobuka.eeeDnevnik.entities.OdeljenjeEntity;
import com.iktpreobuka.eeeDnevnik.entities.OdeljenjeRazredEntity;
import com.iktpreobuka.eeeDnevnik.entities.PredmetEntity;
import com.iktpreobuka.eeeDnevnik.entities.RazredEntity;
import com.iktpreobuka.eeeDnevnik.entities.RazredPredmetEntity;
import com.iktpreobuka.eeeDnevnik.entities.dto.RazredDto;
import com.iktpreobuka.eeeDnevnik.enumeration.ERazred;
import com.iktpreobuka.eeeDnevnik.repositories.NastavnikPredmetOdeljenjeRepository;
import com.iktpreobuka.eeeDnevnik.repositories.OdeljenjeRazredRepository;
import com.iktpreobuka.eeeDnevnik.repositories.RazredPredmetRepository;
import com.iktpreobuka.eeeDnevnik.repositories.RazredRepository;


@Service
public class RazredServiceImpl implements RazredService{
	
	@Autowired
	private RazredRepository razredRepository;
	
	@Autowired
	private RazredPredmetRepository razredPredmetRepository;

	@Autowired
	private OdeljenjeRazredRepository odeljenjeRazredRepository;
	
	@Autowired
	private NastavnikPredmetOdeljenjeRepository nastavnikPredmetOdeljenjeRepository;
	
	@Override
	public RazredEntity dodajNoviRazred(RazredDto noviRazred) throws Exception {
		if (noviRazred.getRazredBroj() == null) {
			throw new Exception("Neki od podataka su prazni.");
		}
		RazredEntity razred = new RazredEntity();
		try {
			razred.setRazredBroj(ERazred.valueOf(noviRazred.getRazredBroj()));
			razred.setAktivan();
			razredRepository.save(razred);
			return razred;
		} catch (Exception e) {
			throw new Exception("Nije uspelo dodavanje novog razreda.");
		}
	}
	
	@Override
	public void izmeniRazred(RazredEntity razredd, RazredDto azurirajRazred) throws Exception {
		if (azurirajRazred.getRazredBroj() == null) {
			throw new Exception("All data is null.");
		}
		try {
			if (azurirajRazred.getRazredBroj() != null && !azurirajRazred.getRazredBroj().equals(" ") && !azurirajRazred.getRazredBroj().equals("")) {
				razredd.setRazredBroj(ERazred.valueOf(azurirajRazred.getRazredBroj()));
				razredRepository.save(razredd);
			}
		} catch (Exception e) {
			throw new Exception("Nije uspelo ažuriranje razreda.");
		}
	}
	
	@Override
	public RazredPredmetEntity dodajPredmetRazredu(RazredEntity razred, PredmetEntity predmet, String ime) throws Exception {
		try {
			boolean sadrzi = false;
			if (ime != null && razred.getStatus() == 1 && predmet.getStatus() == 1) {
				for (RazredPredmetEntity rp : razred.getPredmeti()) {
					if (rp.getPredmet() == predmet && rp.getStatus() == 1) {
						sadrzi = true;			// ako vec sadrži razred taj predmet stavi ga na true
					}							
				}
			} else 
				sadrzi = true;      
			RazredPredmetEntity razredPredmet = null;
			if (!sadrzi) {       // a ako ga ne sadrži, onda ima smisla da ga dodaješ(predmet)
				razredPredmet = new RazredPredmetEntity(razred, predmet, ime);
				razredPredmetRepository.save(razredPredmet);
				razred.getPredmeti().add(razredPredmet);	// dodaješ u razred novi predmet !!!
				razredRepository.save(razred);
			}
			return razredPredmet;
		} catch (Exception e) {
			throw new Exception("addSubjectToClass failed on saving.");
		}
	}
	
	@Override
	public RazredPredmetEntity ukloniPredmetIzRazreda(RazredEntity razredd, PredmetEntity predmet) throws Exception {
		try {
			RazredPredmetEntity rp1 = null;
			if (razredd.getStatus() == 1 && predmet.getStatus() == 1) {
				for (RazredPredmetEntity rp : razredd.getPredmeti()) {
					if (rp.getPredmet() == predmet && rp.getStatus() == 1) {
						rp.setNeaktivan();
						razredPredmetRepository.save(rp);
						rp1 = rp;
					}
				}
			}
			// sada za izbrisani predmet iz odeljenja stavljaš da je profesor koji ga predaje STATUS neaktivan razume se
			if (rp1 != null) {
				for (OdeljenjeRazredEntity or : rp1.getRazred().getOdeljenja())
					if (or.getStatus() == 1) {
						for (NastavnikPredmetOdeljenjeEntity npo : or.getOdeljenje().getNastavnici_predmeti()) {
							if (npo.getPredajeRazred() == rp1.getRazred() && npo.getPredajePredmet() == rp1.getPredmet() && npo.getStatus() == 1) {
								npo.setNeaktivan();
								nastavnikPredmetOdeljenjeRepository.save(npo);
							}
						}
					}
			}
			return rp1;
		} catch (Exception e) {
			throw new Exception("Brisanje predmeta iz Razreda nije uspelo");
		}

	}
	
	@Override
	public OdeljenjeRazredEntity dodajOdeljenjeRazredu(RazredEntity razredd, OdeljenjeEntity odeljenje, String skolskaGodina) throws Exception {
		try {
			boolean sadrzi = false;
			if (razredd.getStatus() == 1 && odeljenje.getStatus() == 1) {
				for (OdeljenjeRazredEntity or : razredd.getOdeljenja()) {
					if (or.getStatus() == 1) {
						if (or.getOdeljenje() == odeljenje) {
							sadrzi = true;
						}
					}
				}
			}  else
				sadrzi = true;
			OdeljenjeRazredEntity odeljenjeRazred = null;
			if (!sadrzi) {
				for (OdeljenjeRazredEntity ore : odeljenje.getRazredi()) {
					if (ore.getStatus() == 1) {
						ore.setNeaktivan();
						odeljenjeRazredRepository.save(ore);
					}
				}
				for (NastavnikPredmetOdeljenjeEntity npo : odeljenje.getNastavnici_predmeti()) {
					if (npo.getStatus() == 1) {
						npo.setNeaktivan();
						nastavnikPredmetOdeljenjeRepository.save(npo);
					}
				}
				// dodajemo novi odeljenje u razred 
				odeljenjeRazred = new OdeljenjeRazredEntity(razredd, odeljenje, skolskaGodina);
				odeljenjeRazredRepository.save(odeljenjeRazred);
				razredd.getOdeljenja().add(odeljenjeRazred);
				razredRepository.save(razredd);
			}
			return odeljenjeRazred;
		} catch (Exception e) {
			throw new Exception("addDepartmentToClass failed on saving.");
		}
	}
	
	@Override
	public void izbrisiRazred(RazredEntity razred) throws Exception {
		try {
			// moraš ga izbrašati svu gde je imao neke veze i pojavljivao se u vezama OneToMany
			for (OdeljenjeRazredEntity or : razred.getOdeljenja()) {
				if (or.getStatus() == 1) {
					or.setNeaktivan();
					odeljenjeRazredRepository.save(or);
				}
			}
			
			// moraš ga izbrašati svu gde je imao neke veze i pojavljivao se u vezama OneToMany
			for (RazredPredmetEntity or1 : razred.getPredmeti()) {
				if (or1.getStatus() == 1) {
					or1.setNeaktivan();
					razredPredmetRepository.save(or1);
				}
			}
			for (NastavnikPredmetOdeljenjeEntity npo : razred.getNastavnici_predmeti_odeljenja()) {
				if (npo.getStatus() == 1) {
					npo.setNeaktivan();
					nastavnikPredmetOdeljenjeRepository.save(npo);
				}
			}
			razred.setNeaktivan();
			razredRepository.save(razred);
		} catch (Exception e) {
			throw new Exception("Brisanje nije uspelo");
		}
	}
	
	public OdeljenjeRazredEntity ukloniOdeljenjeIzRazreda(RazredEntity razred, OdeljenjeEntity odeljenje) throws Exception {
		try {
			OdeljenjeRazredEntity or1 = null;
			if (razred.getStatus() == 1 && odeljenje.getStatus() == 1) {
				for (OdeljenjeRazredEntity or : razred.getOdeljenja()) {
					if (or.getStatus() == 1 && or.getOdeljenje() == odeljenje) {
						or.setNeaktivan();
						odeljenjeRazredRepository.save(or);
						or1 = or;
					}
				}
			}
			// stavljamo profesoru statsu neaktivan jer to odeljenje više ne postoji
			if (or1 != null) {
				for (NastavnikPredmetOdeljenjeEntity npo : or1.getOdeljenje().getNastavnici_predmeti()) {
					if (npo.getPredajeRazred() == or1.getRazred() && npo.getPredajeOdeljenje() == or1.getOdeljenje()) {
						npo.setNeaktivan();
						nastavnikPredmetOdeljenjeRepository.save(npo);
					}
				}
			}
			return or1;
		} catch (Exception e) {
			throw new Exception("removeDepartmentFromClass failed on saving.");
		}
	}

}
