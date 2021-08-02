package com.iktpreobuka.eeeDnevnik.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktpreobuka.eeeDnevnik.entities.NastavnikPredmetEntity;
import com.iktpreobuka.eeeDnevnik.entities.NastavnikPredmetOdeljenjeEntity;
import com.iktpreobuka.eeeDnevnik.entities.PredmetEntity;
import com.iktpreobuka.eeeDnevnik.entities.RazredEntity;
import com.iktpreobuka.eeeDnevnik.entities.RazredPredmetEntity;
import com.iktpreobuka.eeeDnevnik.entities.dto.PredmetDto;
import com.iktpreobuka.eeeDnevnik.repositories.NastavnikPredmetOdeljenjeRepository;
import com.iktpreobuka.eeeDnevnik.repositories.NastavnikPredmetRepository;
import com.iktpreobuka.eeeDnevnik.repositories.NastavnikRepository;
import com.iktpreobuka.eeeDnevnik.repositories.OdeljenjeRazredRepository;
import com.iktpreobuka.eeeDnevnik.repositories.OdeljenjeRepository;
import com.iktpreobuka.eeeDnevnik.repositories.PredmetRepository;
import com.iktpreobuka.eeeDnevnik.repositories.RazredPredmetRepository;
import com.iktpreobuka.eeeDnevnik.repositories.RazredRepository;



@Service
public class PredmetServiceImpl implements PredmetService{
	
	@Autowired
	private RazredPredmetRepository razredPredmetRepository;
	
	@Autowired
	private NastavnikPredmetRepository nastavnikPredmetRepository;
	
	@Autowired
	private OdeljenjeRazredRepository odeljenjeRazredRepository;
	
	@Autowired
	private PredmetRepository predmetRepository;
	
	@Autowired
	private NastavnikRepository nastavnikRepository;

	@Autowired
	private RazredRepository razredRepository;
	
	@Autowired
	private OdeljenjeRepository odeljenjeRepository;
	
	@Autowired
	private NastavnikPredmetOdeljenjeRepository nastavnikPredmetOdeljenjeRepository;
	
	@Override
	public PredmetEntity dodajNoviPredmet(PredmetDto noviPredmet) throws Exception {
		if (noviPredmet.getImePredmet() == null || noviPredmet.getNedeljniBrojCasova() == null) {
			throw new Exception("Some data is null.");
		}
		PredmetEntity predmet = new PredmetEntity();
		try {	
			predmet.setImePredmet(noviPredmet.getImePredmet());
			predmet.setNedeljniBrojCasova(noviPredmet.getNedeljniBrojCasova());
			predmet.setAktivan();
			predmetRepository.save(predmet);
			return predmet;
		} catch (Exception e) {
			throw new Exception("Nije uspelo dodavanje novog predmeta.");
		}
	}
	
	@Override
	public void promeniPredmet(PredmetEntity predmet, PredmetDto azurirajPredmet) throws Exception {
		if (azurirajPredmet.getImePredmet() == null && azurirajPredmet.getNedeljniBrojCasova() == null) {
			throw new Exception("Neki od padataka su prazni.");
		}
		try {
			if (azurirajPredmet.getImePredmet() != null && !azurirajPredmet.getImePredmet().equals(" ") && !azurirajPredmet.getImePredmet().equals("")) {
				predmet.setImePredmet(azurirajPredmet.getImePredmet());
			}
			if (azurirajPredmet.getNedeljniBrojCasova() != null ) {
				predmet.setNedeljniBrojCasova(azurirajPredmet.getNedeljniBrojCasova());
			}
			predmetRepository.save(predmet);

		} catch (Exception e) {
			throw new Exception("Izmena predmeta nije uspela.");
		}
	}
	
	
	@Override
	public void izbrisiPredmet(PredmetEntity predmet) throws Exception {
		try {		
			for (RazredPredmetEntity rp : predmet.getRazredi()) {
				if (rp.getStatus() == 1) {
					rp.setNeaktivan();
					razredPredmetRepository.save(rp);
				}
			}
			for (NastavnikPredmetEntity np : predmet.getNastavnici()) {
				if (np.getStatus() == 1) {
					np.setNeaktivan();
					nastavnikPredmetRepository.save(np);
				}
			}
			for (NastavnikPredmetOdeljenjeEntity npo : predmet.getNastavnici_odeljenja()) {
				if (npo.getStatus() == 1) {
					npo.setNeaktivan();
					nastavnikPredmetOdeljenjeRepository.save(npo);
				}
			}
			predmet.setNeaktivan();
			predmetRepository.save(predmet);
		} catch (Exception e) {
			throw new Exception("Nije uspelo brisanje predmeta");
		}
	}
	
	@Override
	public void povratiPredmet(PredmetEntity predmet) throws Exception {
		try {
			predmet.setAktivan();
			predmetRepository.save(predmet);
		} catch (Exception e) {
			throw new Exception("Povratak pedmeta nije uspelo.");
		}		
	}
	
	@Override
	public void arhivirajPredmet(PredmetEntity predmet) throws Exception {
		try {
			for (RazredPredmetEntity rp : predmet.getRazredi()) {
				if (rp.getStatus() != -1) {
					rp.setNeaktivan();
					razredPredmetRepository.save(rp);
				}
			}
			
			for (NastavnikPredmetEntity np : predmet.getNastavnici()) {
				if (np.getStatus() == 1) {
					np.setArhiviran();
					nastavnikPredmetRepository.save(np);
				}
			}
			
			for (NastavnikPredmetOdeljenjeEntity npo : predmet.getNastavnici_odeljenja()) {
				if (npo.getStatus() == 1) {
					npo.setArhiviran();
					nastavnikPredmetOdeljenjeRepository.save(npo);
				}
			}
			predmet.setArhiviran();;
			predmetRepository.save(predmet);
		} catch (Exception e) {
			throw new Exception("Arhiviranje predmeta niej uspelo.");
		}		
	}
	
	@Override
	public List<RazredPredmetEntity> dodajRazredPredmetu(List<String> razredi, PredmetEntity predmet, String planNastave) throws Exception {
		try {
			List<RazredPredmetEntity> rpe = new ArrayList<RazredPredmetEntity>();
			if (predmet !=null && predmet.getStatus() ==1 && !razredi.isEmpty() && planNastave !=null && !planNastave.equals("") && !planNastave.equals(" ")) {
				for (String t : razredi) {
					if (t !=null && !t.equals("") && !t.equals(" ")) {
						RazredEntity razredd = razredRepository.findByIdAndStatusLike(Integer.parseInt(t), 1);
						if (razredd==null)
							throw new Exception("Razred ne postoji");
						boolean sadrzi = false;
						if (planNastave != null && razredd.getStatus() == 1 && predmet.getStatus() == 1) {
							for (RazredPredmetEntity rp : predmet.getRazredi()) {
								if (rp.getRazred() == razredd && rp.getStatus() == 1) {
										sadrzi = true;
								}
							}
						} else
							sadrzi = true;
						if (!sadrzi) {
							RazredPredmetEntity razredPredmet = new RazredPredmetEntity(razredd, predmet, planNastave);
							razredPredmet.setStatus(1); // božeeeeeeeeeeee
							razredPredmetRepository.save(razredPredmet);
							predmet.getRazredi().add(razredPredmet);
							predmetRepository.save(predmet);
							rpe.add(razredPredmet);
						}
					}
				}
			}
			return rpe;
		} catch (Exception e) {
			throw new Exception("Nije uspelo dodvanje razreda predmetu.");
		}
	}

}
