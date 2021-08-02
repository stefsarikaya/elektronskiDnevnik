package com.iktpreobuka.eeeDnevnik.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktpreobuka.eeeDnevnik.entities.KorisnikEntity;
import com.iktpreobuka.eeeDnevnik.entities.NastavnikEntity;
import com.iktpreobuka.eeeDnevnik.entities.RoditeljEntity;
import com.iktpreobuka.eeeDnevnik.entities.UcenikEntity;
import com.iktpreobuka.eeeDnevnik.entities.dto.RoditeljDto;
import com.iktpreobuka.eeeDnevnik.enumeration.EPol;
import com.iktpreobuka.eeeDnevnik.enumeration.EUloga;
import com.iktpreobuka.eeeDnevnik.repositories.KorisnikRepository;
import com.iktpreobuka.eeeDnevnik.repositories.RoditeljRepository;
import com.iktpreobuka.eeeDnevnik.repositories.UcenikRepository;


@Service
public class RoditeljServiceImpl implements RoditeljService{
	
	@Autowired
	private RoditeljRepository roditeljRepository;

	@Autowired
	private KorisnikRepository korisnikRepository;
	
	@Autowired
	private UcenikRepository ucenikRepository;
	
	@Override
	public KorisnikEntity dodajNovogRoditelja(RoditeljDto noviRoditelj) throws Exception {
		if (noviRoditelj.getIme() == null || noviRoditelj.getPrezime() == null || noviRoditelj.getPol() == null || noviRoditelj.getJmbg() == null || noviRoditelj.getEmail() == null) {
			throw new Exception("Neki od podataka su prazni.");
		}
		KorisnikEntity trenutniKorisnik = new NastavnikEntity();
		try {
			trenutniKorisnik = korisnikRepository.findByJmbg(noviRoditelj.getJmbg());
		} catch (Exception e1) {
			throw new Exception("Dodavanje novog roditelja nije uspelo..");
		}
		RoditeljEntity korisnik = new RoditeljEntity();
			if (trenutniKorisnik == null) {
				try {
					korisnik.setIme(noviRoditelj.getIme());
					korisnik.setPrezime(noviRoditelj.getPrezime());
					korisnik.setJmbg(noviRoditelj.getJmbg());
					korisnik.setPol(EPol.valueOf(noviRoditelj.getPol()));
					korisnik.setEmail(noviRoditelj.getEmail());
					korisnik.setUloga(EUloga.roditelj);
					korisnik.setAktivan();
					roditeljRepository.save(korisnik);
					trenutniKorisnik = korisnik;
				} catch (Exception e) {
					throw new Exception("Nije uspelo dodavanje novog roditelja.");
				}
	}
			return trenutniKorisnik;
	}
	
	@Override
	public void promeniRoditelja(RoditeljEntity roditelj, RoditeljDto azurirajRoditelj) throws Exception {
		if (azurirajRoditelj.getIme() == null && azurirajRoditelj.getPrezime() == null && azurirajRoditelj.getPol() == null && azurirajRoditelj.getJmbg() == null && azurirajRoditelj.getEmail() == null) {
			throw new Exception("All data is null.");
		}
		try {
			if (azurirajRoditelj.getIme() != null && !azurirajRoditelj.getIme().equals(" ") && !azurirajRoditelj.getIme().equals("") && !azurirajRoditelj.getIme().equals(roditelj.getIme())) {
				roditelj.setIme(azurirajRoditelj.getIme());
			}
			if (azurirajRoditelj.getPrezime() != null && !azurirajRoditelj.getPrezime().equals(roditelj.getPrezime()) && !azurirajRoditelj.getPrezime().equals(" ") && !azurirajRoditelj.getPrezime().equals("")) {
				roditelj.setPrezime(azurirajRoditelj.getPrezime());
			}
			if (azurirajRoditelj.getJmbg() != null && !azurirajRoditelj.getJmbg().equals(roditelj.getJmbg()) && !azurirajRoditelj.getJmbg().equals(" ") && !azurirajRoditelj.getJmbg().equals("") ) {
				roditelj.setJmbg(azurirajRoditelj.getJmbg());

			}
			if (azurirajRoditelj.getPol() != null && EPol.valueOf(azurirajRoditelj.getPol()) != roditelj.getPol() && (EPol.valueOf(azurirajRoditelj.getPol()) == EPol.zenski || EPol.valueOf(azurirajRoditelj.getPol()) == EPol.muski)) {
				roditelj.setPol(EPol.valueOf(azurirajRoditelj.getPol()));

			}
			if (azurirajRoditelj.getEmail() != null && !azurirajRoditelj.getEmail().equals(roditelj.getEmail()) && !azurirajRoditelj.getEmail().equals(" ") && !azurirajRoditelj.getEmail().equals("")) {
				roditelj.setEmail(azurirajRoditelj.getEmail());

			}
				roditeljRepository.save(roditelj);
		} catch (Exception e) {
			throw new Exception("Promena roditelja nije uspela.");
		}
	}
	
	@Override
	public void izbrisiRoditelja(RoditeljEntity roditelj) throws Exception {
		try {
			roditelj.setNeaktivan();
			roditeljRepository.save(roditelj);
		} catch (Exception e) {
			throw new Exception("Brisanje roditelja nije uspelo.");
		}
	}
	
	@Override
	public void povratiRoditelja(RoditeljEntity roditelj) throws Exception {
		try {
			roditelj.setAktivan();
			roditeljRepository.save(roditelj);
		} catch (Exception e) {
			throw new Exception("Povratak za roditelja nije uspelo.");
		}		
	}
	
	@Override
	public void arhivirajRoditelja(RoditeljEntity roditelj) throws Exception {
		try {
			roditelj.setAktivan();
			roditeljRepository.save(roditelj);
		} catch (Exception e) {
			throw new Exception("Arhiviranje za roditelja nije uspelo.");
		}		
	}
	
	@Override
	public void dodajUcenikaRoditelju(RoditeljEntity roditelj, UcenikEntity ucenik) throws Exception {
		try {
			ucenik.setRoditelj(roditelj);
			ucenikRepository.save(ucenik);
		} catch (Exception e) {
			throw new Exception("Dodavanje ucenika roditelju nije uspelo.");
		}
	}
	
	@Override
	public void ukloniUcenikaRoditelju(RoditeljEntity roditelj, UcenikEntity ucenik) throws Exception {
		try {
			ucenik.remove(roditelj);
			ucenikRepository.save(ucenik);
		} catch (Exception e) {
			throw new Exception("Uklanjanje ucenika roditelju nije uspelo.");
		}
	}


}
