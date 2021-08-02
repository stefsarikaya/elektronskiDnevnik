package com.iktpreobuka.eeeDnevnik.services;

import com.iktpreobuka.eeeDnevnik.entities.OdeljenjeEntity;
import com.iktpreobuka.eeeDnevnik.entities.OdeljenjeRazredEntity;
import com.iktpreobuka.eeeDnevnik.entities.PredmetEntity;
import com.iktpreobuka.eeeDnevnik.entities.RazredEntity;
import com.iktpreobuka.eeeDnevnik.entities.RazredPredmetEntity;
import com.iktpreobuka.eeeDnevnik.entities.dto.RazredDto;
;

public interface RazredService {
	
	public RazredEntity dodajNoviRazred(RazredDto noviRazred) throws Exception;

	public void izmeniRazred(RazredEntity razred, RazredDto azurirajRazred) throws Exception;

	public RazredPredmetEntity dodajPredmetRazredu(RazredEntity razred, PredmetEntity predmet, String ime) throws Exception;

	public OdeljenjeRazredEntity dodajOdeljenjeRazredu(RazredEntity razredd, OdeljenjeEntity odeljenje, String skolskaGodina) throws Exception;

	 public void izbrisiRazred( RazredEntity razred) throws Exception;

	// public void povratiRazred(RazredEntity razed) throws Exception; IRELEVANTNO

	//  public void arhivirajRazred(RazredEntity razred) throws Exception; IRELEVANTNO

	public RazredPredmetEntity ukloniPredmetIzRazreda(RazredEntity razred, PredmetEntity predmet) throws Exception;

	public OdeljenjeRazredEntity ukloniOdeljenjeIzRazreda(RazredEntity razred, OdeljenjeEntity odeljenje) throws Exception;


}
