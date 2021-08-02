package com.iktpreobuka.eeeDnevnik.services;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktpreobuka.eeeDnevnik.entities.KorisnikEntity;
import com.iktpreobuka.eeeDnevnik.entities.NastavnikEntity;
import com.iktpreobuka.eeeDnevnik.entities.dto.NastavnikDto;
import com.iktpreobuka.eeeDnevnik.enumeration.EPol;
import com.iktpreobuka.eeeDnevnik.enumeration.EUloga;
import com.iktpreobuka.eeeDnevnik.repositories.KorisnikRepository;
import com.iktpreobuka.eeeDnevnik.repositories.NastavnikPredmetOdeljenjeRepository;
import com.iktpreobuka.eeeDnevnik.repositories.NastavnikPredmetRepository;
import com.iktpreobuka.eeeDnevnik.repositories.NastavnikRepository;
import com.iktpreobuka.eeeDnevnik.repositories.OdeljenjeRazredRepository;
import com.iktpreobuka.eeeDnevnik.repositories.OdeljenjeRepository;
import com.iktpreobuka.eeeDnevnik.repositories.PredmetRepository;
import com.iktpreobuka.eeeDnevnik.repositories.RazredniStaresinaOdeljenjeRepository;


@Service
public class NastavnikServiceImpl implements NastavnikService{
	
	@Autowired
	private NastavnikRepository nastavnikRepository;

	@Autowired
	private PredmetRepository predmetRepository;
	
	@Autowired
	private OdeljenjeRepository OdeljenjeRepository;
	
	@Autowired
	private OdeljenjeRazredRepository OdeljenjeRazredRepository;
	
	@Autowired
	private NastavnikPredmetRepository NastavnikPredmetRepository;
	
	@Autowired
	private RazredniStaresinaOdeljenjeRepository razredniStaresinaOdeljenjeRepository;
	
	@Autowired
	private NastavnikPredmetOdeljenjeRepository nastavnikPredmetOdeljenjeRepository;

	@Autowired
	private KorisnikRepository korisnikRepository;
	

	public KorisnikEntity dodajNovogNastavnika(NastavnikDto noviNastavnik) throws Exception {
		if (noviNastavnik.getIme() == null || noviNastavnik.getPrezime() == null || noviNastavnik.getDiploma() == null || noviNastavnik.getDatumZaposlenja() == null || noviNastavnik.getPol() == null || noviNastavnik.getJmbg() == null ) {
			throw new Exception("Neki od podataka su prazni");
		}
		KorisnikEntity korisnikk = new NastavnikEntity();
		try {
			korisnikk = korisnikRepository.findByJmbg(noviNastavnik.getJmbg());
		} catch (Exception e1) {
			throw new Exception("Vec postoji. Greska!");
		}
		if (korisnikk != null && (!korisnikk.getIme().equals(noviNastavnik.getIme()) || !korisnikk.getPrezime().equals(noviNastavnik.getPrezime()) || !korisnikk.getPol().toString().equals(noviNastavnik.getPol()) || !korisnikk.getJmbg().equals(noviNastavnik.getJmbg()))) {
			throw new Exception("Korisnik postoji, ali ulazni podaci se ne poklapaju.");
		}
		NastavnikEntity korisnik = new NastavnikEntity();
	try {
		if (korisnikk == null) {
			try {
				korisnik.setIme(noviNastavnik.getIme());
				korisnik.setPrezime(noviNastavnik.getPrezime());
				korisnik.setJmbg(noviNastavnik.getJmbg());
				korisnik.setPol(EPol.valueOf(noviNastavnik.getPol()));
			    Date datumZaposlenja = Date.valueOf(noviNastavnik.getDatumZaposlenja());
			    korisnik.setDatumZaposlenja(datumZaposlenja);
			    korisnik.setDiploma(noviNastavnik.getDiploma());
			    korisnik.setUloga(EUloga.nastavnik);
			    korisnik.setAktivan();
				nastavnikRepository.save(korisnik);
				korisnikk = korisnik;
			} catch (Exception e) {
				throw new Exception("Dodavanje novog nastavnika nije uspelo.");
			}
		} 
		return korisnikk;
	} catch (Exception e) {
		throw new Exception("Dodavanje novog nastavika nije uspelo.");
	}
}



}
