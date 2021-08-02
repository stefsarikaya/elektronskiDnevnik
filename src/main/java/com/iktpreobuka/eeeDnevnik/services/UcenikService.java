package com.iktpreobuka.eeeDnevnik.services;

import com.iktpreobuka.eeeDnevnik.entities.KorisnikEntity;
import com.iktpreobuka.eeeDnevnik.entities.OdeljenjeEntity;
import com.iktpreobuka.eeeDnevnik.entities.RoditeljEntity;
import com.iktpreobuka.eeeDnevnik.entities.UcenikEntity;
import com.iktpreobuka.eeeDnevnik.entities.UcenikOdeljenjeEntity;
import com.iktpreobuka.eeeDnevnik.entities.dto.UcenikDto;

public interface UcenikService {
	
	public KorisnikEntity dodajNovogUcenika(UcenikDto noviUcenik) throws Exception;

	public void izmeniUcenika(UcenikEntity ucenik, UcenikDto noviUcenik) throws Exception;

	public void izbrisiUcenika(UcenikEntity ucenik) throws Exception;

	public void povratiUcenika(UcenikEntity ucenik) throws Exception;

	public void arhivirajUcenika(UcenikEntity ucenik) throws Exception;

	public void dodajRoditeljaUceniku(UcenikEntity ucenik, RoditeljEntity roditelj) throws Exception;
	
	public void ukloniRoditeljaIzUcenika(UcenikEntity ucenik, RoditeljEntity roditelj) throws Exception;

	public UcenikOdeljenjeEntity dodajOdeljenjeUceniku(UcenikEntity ucenik, OdeljenjeEntity odeljenje, String datum_prebacivanja) throws Exception;

	public UcenikOdeljenjeEntity ukloniOdeljenjeIzUcenika(UcenikEntity ucenik, OdeljenjeEntity odeljenje) throws Exception;


}
