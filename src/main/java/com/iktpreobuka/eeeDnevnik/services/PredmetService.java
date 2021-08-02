package com.iktpreobuka.eeeDnevnik.services;

import java.util.List;

import com.iktpreobuka.eeeDnevnik.entities.NastavnikPredmetEntity;
import com.iktpreobuka.eeeDnevnik.entities.NastavnikPredmetOdeljenjeEntity;
import com.iktpreobuka.eeeDnevnik.entities.PredmetEntity;
import com.iktpreobuka.eeeDnevnik.entities.RazredPredmetEntity;
import com.iktpreobuka.eeeDnevnik.entities.dto.PredmetDto;



public interface PredmetService {
	
	public PredmetEntity dodajNoviPredmet(PredmetDto noviPredmet) throws Exception;

	public void promeniPredmet(PredmetEntity predmet, PredmetDto azurirajPredmet) throws Exception;

	public void izbrisiPredmet( PredmetEntity predmet) throws Exception;

	public void povratiPredmet(PredmetEntity predmet) throws Exception;

	public void arhivirajPredmet(PredmetEntity predmet) throws Exception;
	
	public List<RazredPredmetEntity> dodajRazredPredmetu(List<String> razredi, PredmetEntity predmet, String planNastave) throws Exception;
	
	// ovaj nećeš sad opet pisati jer ti se izbrisalo lol :D
	//public List<RazredPredmetEntity> ukloniRazredIzPredmeta(List<String> razredi, PredmetEntity predmet) throws Exception;

	//public List<NastavnikPredmetEntity> dodajNastavnikePredmetu(PredmetEntity predmet, List<String> nastavnici) throws Exception;
	
	//public List<NastavnikPredmetEntity> ukloniNastavnikeIzPredmeta(PredmetEntity predmet, List<String> nastavnici) throws Exception;
	
	//public NastavnikPredmetOdeljenjeEntity dodajNastavnikaIOdeljenjePredmetu(PredmetEntity predmet, String predajeOdeljenje, String predajuciNastavnik, String skolskaGodina) throws Exception;

	//public NastavnikPredmetOdeljenjeEntity ukloniNastavnikaIOdeljenjePredmetu(PredmetEntity predmet,	String predajeOdeljenje, String predajuciNastavnik) throws Exception;


}
