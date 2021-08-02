package com.iktpreobuka.eeeDnevnik.services;

import com.iktpreobuka.eeeDnevnik.entities.NastavnikEntity;
import com.iktpreobuka.eeeDnevnik.entities.NastavnikPredmetOdeljenjeEntity;
import com.iktpreobuka.eeeDnevnik.entities.OcenaEntity;
import com.iktpreobuka.eeeDnevnik.entities.UcenikEntity;
import com.iktpreobuka.eeeDnevnik.entities.dto.OcenaDto;

public interface OcenaService {
	
	public OcenaEntity dodajNovuOcenu(NastavnikEntity nastavnik, UcenikEntity ucenik, NastavnikPredmetOdeljenjeEntity nastavnikOdeljenja, OcenaDto novaOcena) throws Exception;

}
