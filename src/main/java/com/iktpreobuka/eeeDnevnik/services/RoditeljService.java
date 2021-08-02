package com.iktpreobuka.eeeDnevnik.services;

import com.iktpreobuka.eeeDnevnik.entities.KorisnikEntity;
import com.iktpreobuka.eeeDnevnik.entities.RoditeljEntity;
import com.iktpreobuka.eeeDnevnik.entities.UcenikEntity;
import com.iktpreobuka.eeeDnevnik.entities.dto.RoditeljDto;

public interface RoditeljService {

	public KorisnikEntity dodajNovogRoditelja(RoditeljDto noviRoditelj) throws Exception;

	public void promeniRoditelja(RoditeljEntity roditelj, RoditeljDto noviRoditelj) throws Exception;

	public void izbrisiRoditelja(RoditeljEntity roditelj) throws Exception;

	public void povratiRoditelja(RoditeljEntity roditelj) throws Exception;

	public void arhivirajRoditelja(RoditeljEntity roditelj) throws Exception;

	public void dodajUcenikaRoditelju(RoditeljEntity roditelj, UcenikEntity ucenik) throws Exception;

	public void ukloniUcenikaRoditelju(RoditeljEntity roditelj, UcenikEntity ucenik) throws Exception;

}
