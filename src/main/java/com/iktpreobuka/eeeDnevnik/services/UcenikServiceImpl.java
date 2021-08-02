package com.iktpreobuka.eeeDnevnik.services;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktpreobuka.eeeDnevnik.entities.KorisnikEntity;
import com.iktpreobuka.eeeDnevnik.entities.OdeljenjeEntity;
import com.iktpreobuka.eeeDnevnik.entities.RoditeljEntity;
import com.iktpreobuka.eeeDnevnik.entities.UcenikEntity;
import com.iktpreobuka.eeeDnevnik.entities.UcenikOdeljenjeEntity;
import com.iktpreobuka.eeeDnevnik.entities.dto.UcenikDto;
import com.iktpreobuka.eeeDnevnik.enumeration.EPol;
import com.iktpreobuka.eeeDnevnik.enumeration.EUloga;
import com.iktpreobuka.eeeDnevnik.repositories.UcenikOdeljenjeRepository;
import com.iktpreobuka.eeeDnevnik.repositories.UcenikRepository;

@Service
public class UcenikServiceImpl implements UcenikService{
	
	@Autowired
	private UcenikRepository ucenikRepository;
	
	@Autowired
	private UcenikOdeljenjeRepository ucenikOdeljenjeRepository;
	
	@Override
	public KorisnikEntity dodajNovogUcenika(UcenikDto noviUcenik) throws Exception {
		if (noviUcenik.getIme() == null || noviUcenik.getPrezime() == null
				|| noviUcenik.getUloga() == null || noviUcenik.getPol() == null
				|| noviUcenik.getJmbg() == null || noviUcenik.getSkolskiIdentifikacioniBroj() == null) {
			throw new Exception("Neki od unešenih atributa su prazni.");
		}
		UcenikEntity korisnik = new UcenikEntity();
		try {
			korisnik.setIme(noviUcenik.getIme());
			korisnik.setPrezime(noviUcenik.getPrezime());
			korisnik.setJmbg(noviUcenik.getJmbg());
			korisnik.setPol(EPol.valueOf(noviUcenik.getPol()));
			korisnik.setDatumUpisa(Date.valueOf(noviUcenik.getDatumUpisa()));
			korisnik.setSkolskiIdentifikacioniBroj(noviUcenik.getSkolskiIdentifikacioniBroj());
			korisnik.setUloga(EUloga.ucenik);
			korisnik.setAktivan(); 
			ucenikRepository.save(korisnik);
			return korisnik;
		} catch (Exception e) {
			throw new Exception("Dodavanje novog ucenika nije uspelo.");
		}

	}
	
	@Override
	public void izmeniUcenika(UcenikEntity ucenik, UcenikDto izmeniUcenika) throws Exception {
		try {
			if (izmeniUcenika.getIme() == null && izmeniUcenika.getPrezime() == null
					&& izmeniUcenika.getUloga() == null && izmeniUcenika.getPol() == null
					&& izmeniUcenika.getJmbg() != null && izmeniUcenika.getSkolskiIdentifikacioniBroj() != null) {
				throw new Exception("Svi unešeni atributi su prazni.");
			}
		} catch (Exception e1) {
			throw new Exception("ucenikDto nije uspelo da se generiše.");
		}
		try {
			if (izmeniUcenika.getIme() != null && !izmeniUcenika.getIme().equals(" ")
					&& !izmeniUcenika.getIme().equals("")
					&& !izmeniUcenika.getIme().equals(ucenik.getIme())) {
				ucenik.setIme(izmeniUcenika.getIme());
			}
			if (izmeniUcenika.getPrezime() != null && !izmeniUcenika.getPrezime().equals(ucenik.getPrezime())
					&& !izmeniUcenika.getPrezime().equals(" ") && !izmeniUcenika.getPrezime().equals("")) {
				ucenik.setPrezime(izmeniUcenika.getPrezime());
			}
			if (izmeniUcenika.getJmbg() != null && !izmeniUcenika.getJmbg().equals(ucenik.getJmbg())
					&& !izmeniUcenika.getJmbg().equals(" ") && !izmeniUcenika.getJmbg().equals("")) {
				ucenik.setJmbg(izmeniUcenika.getJmbg());
			}
			if (izmeniUcenika.getPol() != null
					&& EPol.valueOf(izmeniUcenika.getPol()) != ucenik.getPol()
					&& (EPol.valueOf(izmeniUcenika.getPol()) == EPol.muski
							|| EPol.valueOf(izmeniUcenika.getPol()) == EPol.zenski)) {
				ucenik.setPol(EPol.valueOf(izmeniUcenika.getPol()));
			}
			if (izmeniUcenika.getSkolskiIdentifikacioniBroj() != null
					&& !izmeniUcenika.getSkolskiIdentifikacioniBroj().equals(ucenik.getSkolskiIdentifikacioniBroj())
					&& !izmeniUcenika.getSkolskiIdentifikacioniBroj().equals(" ")
					&& !izmeniUcenika.getSkolskiIdentifikacioniBroj().equals("")) {
				ucenik.setSkolskiIdentifikacioniBroj(izmeniUcenika.getSkolskiIdentifikacioniBroj());
			}
			if (izmeniUcenika.getDatumUpisa() != null
					&& !Date.valueOf(izmeniUcenika.getDatumUpisa()).equals(ucenik.getDatumUpisa())
					&& !izmeniUcenika.getDatumUpisa().equals(" ")
					&& !izmeniUcenika.getDatumUpisa().equals("")) {
				ucenik.setDatumUpisa(Date.valueOf(izmeniUcenika.getDatumUpisa()));
			}
			ucenikRepository.save(ucenik);
		} catch (Exception e) {
			throw new Exception("Promena naloga nije uspela.");
		}
	}
	
	
	@Override
	public void izbrisiUcenika(UcenikEntity ucenik) throws Exception {
		try {
			for (UcenikOdeljenjeEntity uo : ucenik.getOdeljenja()) {
				if (uo.getStatus() == 1) {
					uo.setNeaktivan();
					ucenikOdeljenjeRepository.save(uo);
				}
			}
			ucenik.setNeaktivan();
			ucenikRepository.save(ucenik);
		} catch (Exception e) {
			throw new Exception("Birsanje ucenika nije uspelo.");
		}
	}
	
	@Override
	public void povratiUcenika(UcenikEntity ucenik) throws Exception {
		try {
			ucenik.setAktivan();
			ucenikRepository.save(ucenik);
		} catch (Exception e) {
			throw new Exception("Povratak ucenika nije uspelo");
		}
	}
	
	@Override
	public void arhivirajUcenika(UcenikEntity ucenik) throws Exception {
		try {
			for (UcenikOdeljenjeEntity uo : ucenik.getOdeljenja()) {
				if (uo.getStatus() != 1) {
					uo.setArhiviran();
					ucenikOdeljenjeRepository.save(uo);
				}
			}
			ucenik.setArhiviran();
			ucenikRepository.save(ucenik);
		} catch (Exception e) {
			throw new Exception("Arhvirainej studenta nije uspelo.");
		}
	}
	
	@Override
	public void dodajRoditeljaUceniku(UcenikEntity ucenik, RoditeljEntity roditelj) throws Exception {
		try {
			ucenik.setRoditelj(roditelj); //OneToMany veza, stavio si da ima jednog roditelja. bizzare
			ucenikRepository.save(ucenik);
		} catch (Exception e) {
			throw new Exception("Dodavanje jednog roditelja ili staratelja navedenom učeniku nije uspelo.");
		}
	}
	
	@Override
	public void ukloniRoditeljaIzUcenika(UcenikEntity ucenik, RoditeljEntity roditelj) throws Exception {
		try {
			ucenik.getRoditelj().remove(roditelj); // dodao si metodu u EntityRoditelj 
			ucenikRepository.save(ucenik);
		} catch (Exception e) {
			throw new Exception("Uklanjanje roditelja navedenom učeniku nije uspelo.");
		}
	}
	
	@Override
	public UcenikOdeljenjeEntity dodajOdeljenjeUceniku(UcenikEntity ucenik,
			OdeljenjeEntity odeljenje, String datum_prebacivanja) throws Exception {
		try {
			UcenikOdeljenjeEntity uoe = null;
			if (odeljenje != null && odeljenje.getStatus() == 1 && ucenik != null && ucenik.getStatus() == 1
					&& datum_prebacivanja != null && !datum_prebacivanja.equals("") && !datum_prebacivanja.equals(" ")) {
				boolean sadrzi = false;
				for (UcenikOdeljenjeEntity uo : ucenik.getOdeljenja()) {
					if (uo.getStatus() == 1)
						if (uo.getOdeljenje() == odeljenje) {
							sadrzi = true; // znači već priapda tom odeljenju
						} else { // ako ga ne pripada, postavi to odeljenje da je neaktivno (status), jer ceš mus ad dodeliti novo					
							uo.setNeaktivan(); 
							ucenikOdeljenjeRepository.save(uo);
						}
				}
				if (!sadrzi) { // ne priapda tom odeljnju, pa mu sad dodaješ novo kome ce pripadati.
					// proveri da li radi kada Marka zameniš sa Jankom
					UcenikOdeljenjeEntity ucenikOdeljenje = new UcenikOdeljenjeEntity(ucenik, odeljenje,
							Date.valueOf(datum_prebacivanja));
					ucenikOdeljenjeRepository.save(ucenikOdeljenje);
					ucenik.getOdeljenja().add(ucenikOdeljenje);
					ucenikRepository.save(ucenik);
					uoe = ucenikOdeljenje;
				}
			}
			return uoe;
		} catch (Exception e) {
			throw new Exception("Dodavanje novog odeljenja za učenika nije uspelo.");
		}
	}

	@Override
	public UcenikOdeljenjeEntity ukloniOdeljenjeIzUcenika(UcenikEntity ucenik,
			OdeljenjeEntity odeljenje) throws Exception {
		try {
			UcenikOdeljenjeEntity uoe = null;
			if (odeljenje != null && odeljenje.getStatus() == 1 && ucenik != null && ucenik.getStatus() == 1) {
				for (UcenikOdeljenjeEntity uo : ucenik.getOdeljenja()) {
					if (uo.getStatus() == 1 && uo.getOdeljenje() == odeljenje) {
						uo.setNeaktivan();
						ucenikOdeljenjeRepository.save(uo);
						uoe = uo;
					}
				}
			}
			return uoe;
		} catch (Exception e) {
			throw new Exception("Uklanjanje odeljenja za učenika nije uspelo.");
		}
	}
	


}
