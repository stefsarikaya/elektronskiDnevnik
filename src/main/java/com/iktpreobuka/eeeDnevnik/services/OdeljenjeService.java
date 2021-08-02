package com.iktpreobuka.eeeDnevnik.services;

import com.iktpreobuka.eeeDnevnik.entities.NastavnikEntity;
import com.iktpreobuka.eeeDnevnik.entities.NastavnikPredmetOdeljenjeEntity;
import com.iktpreobuka.eeeDnevnik.entities.OdeljenjeEntity;
import com.iktpreobuka.eeeDnevnik.entities.OdeljenjeRazredEntity;
import com.iktpreobuka.eeeDnevnik.entities.PredmetEntity;
import com.iktpreobuka.eeeDnevnik.entities.RazredEntity;
import com.iktpreobuka.eeeDnevnik.entities.RazredniStaresinaOdeljenjeEntity;
import com.iktpreobuka.eeeDnevnik.entities.UcenikEntity;
import com.iktpreobuka.eeeDnevnik.entities.UcenikOdeljenjeEntity;
import com.iktpreobuka.eeeDnevnik.entities.dto.OdeljenjeDto;

public interface OdeljenjeService {
	
	//public UcenikOdeljenjeEntity dodajUcenikaOdeljenju(UcenikEntity ucenik, OdeljenjeEntity odeljenje, String datum) throws Exception;

	public OdeljenjeEntity dodajNovoOdeljenje(OdeljenjeDto novoOdeljenje) throws Exception;

	//public void promeniOdeljenje(OdeljenjeEntity odeljenje, OdeljenjeDto azurirajOdeljenje) throws Exception;

	//public void izbrisiOdeljenje(OdeljenjeEntity odeljenje) throws Exception;
    
	//public void povratiOdeljenje(OdeljenjeEntity odeljenje) throws Exception;

	//public void arhivirajOdeljenje(OdeljenjeEntity odeljenje) throws Exception;

	//public OdeljenjeRazredEntity dodajRazredOdeljenju(RazredEntity razred, OdeljenjeEntity odeljenje, String skoslkaGodina)  throws Exception;

	//public RazredniStaresinaOdeljenjeEntity dodajRazrednogStaresinuOdeljenju(NastavnikEntity nastavnik, OdeljenjeEntity odeljenje, String datumDodele) throws Exception;

	//public NastavnikPredmetOdeljenjeEntity dodajNastavnikaIPredmetOdeljenju(NastavnikEntity nastavnik, OdeljenjeEntity odeljenje,RazredEntity razred, PredmetEntity predmet, String skolskaGodina) throws Exception;

	//public RazredniStaresinaOdeljenjeEntity ukloniRazrednogStaresinuOdeljenju(NastavnikEntity nastavnik, OdeljenjeEntity odeljenje) throws Exception;

	//public NastavnikPredmetOdeljenjeEntity ukloniNastavnikaIPredmetOdeljenju(NastavnikEntity nastavnik, OdeljenjeEntity odeljenje, RazredEntity razred, PredmetEntity predmet) throws Exception;

	//public OdeljenjeRazredEntity ukloniRazredIzOdeljenja(RazredEntity razred, OdeljenjeEntity odeljenje) throws Exception;

	//public UcenikOdeljenjeEntity ukloniUcenikaIzOdeljenja(UcenikEntity ucenik, OdeljenjeEntity odeljenje) throws Exception;


}
