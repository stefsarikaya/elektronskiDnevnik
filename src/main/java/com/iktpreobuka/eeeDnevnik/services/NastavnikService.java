package com.iktpreobuka.eeeDnevnik.services;

import java.util.List;

import com.iktpreobuka.eeeDnevnik.entities.KorisnikEntity;
import com.iktpreobuka.eeeDnevnik.entities.NastavnikEntity;
import com.iktpreobuka.eeeDnevnik.entities.NastavnikPredmetEntity;
import com.iktpreobuka.eeeDnevnik.entities.NastavnikPredmetOdeljenjeEntity;
import com.iktpreobuka.eeeDnevnik.entities.RazredniStaresinaOdeljenjeEntity;
import com.iktpreobuka.eeeDnevnik.entities.dto.NastavnikDto;

public interface NastavnikService {
	
	public KorisnikEntity dodajNovogNastavnika(NastavnikDto newTeacher) throws Exception;

	// public void izmeniNastavnika(NastavnikEntity nastavnik, NastavnikDto noviNastavnik) throws Exception;

	// public void izbrisiNastavnika(NastavnikEntity nastavnik) throws Exception;

	// public void povratiNastavnika(NastavnikEntity nastavnik) throws Exception;

	// public void arhivirajNastavnika(NastavnikEntity nastavnik) throws Exception;

	// public List<NastavnikPredmetEntity> dodajPredmeteNastavniku( NastavnikEntity korisnik, List<String> predmeti) throws Exception;
	
	// public List<NastavnikPredmetEntity> ukloniPredmeteNastavniku(NastavnikEntity korisnik, List<String> predmeti) throws Exception;

	// public RazredniStaresinaOdeljenjeEntity dodajGlavnoOdeljenjeNastavniku(NastavnikEntity korisnik, String glavnoOdeljenje) throws Exception;
	
	// public RazredniStaresinaOdeljenjeEntity ukloniGlavnoOdeljenjeNastavniku(NastavnikEntity korisnik, String glavnoOdeljenje) throws Exception;

	// public NastavnikPredmetOdeljenjeEntity addSubjectsInDepartmentsToTeacher(NastavnikEntity korisnik, String predajeOdeljenje, String predajePredmet, String skolskaGodina) throws Exception;
	
	// public NastavnikPredmetOdeljenjeEntity removeSubjectsInDepartmentsFromTeacher(NastavnikEntity korisnik, String predajeOdeljenje, String predajePredmet) throws Exception;

	// public List<NastavnikPredmetOdeljenjeEntity> addDepartmentsToSubjectForTeacher(NastavnikEntity korisnik, List<String> predajeOdeljenjima, String predajePredmet, String skolskaGodina) throws Exception;

	// public List<NastavnikPredmetOdeljenjeEntity> removeDepartmentsFromSubjectForTeacher(NastavnikEntity korisnik, List<String> predajeOdeljenjima, String predajePredmet) throws Exception;

}
