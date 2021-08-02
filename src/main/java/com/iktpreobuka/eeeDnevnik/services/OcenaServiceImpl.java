package com.iktpreobuka.eeeDnevnik.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktpreobuka.eeeDnevnik.entities.NastavnikEntity;
import com.iktpreobuka.eeeDnevnik.entities.NastavnikPredmetOdeljenjeEntity;
import com.iktpreobuka.eeeDnevnik.entities.OcenaEntity;
import com.iktpreobuka.eeeDnevnik.entities.UcenikEntity;
import com.iktpreobuka.eeeDnevnik.entities.dto.OcenaDto;
import com.iktpreobuka.eeeDnevnik.enumeration.ESemestar;
import com.iktpreobuka.eeeDnevnik.repositories.OcenaRepository;

@Service
public class OcenaServiceImpl implements OcenaService{
	
	@Autowired
	OcenaRepository ocenaRepository;
	
	
	   @Override
	   public OcenaEntity dodajNovuOcenu(NastavnikEntity nastavnik, UcenikEntity ucenik, NastavnikPredmetOdeljenjeEntity nastavnikOdeljenja, OcenaDto novaOcena) throws Exception {
		   if (nastavnik ==null || ucenik==null || nastavnikOdeljenja==null || novaOcena == null || novaOcena.getSemestar() == null || novaOcena.getVrednostOcene() == null) {
			   throw new Exception("neki od podataka su prazni.");
		   }
		   OcenaEntity ocena = new OcenaEntity();
		   try {
			   ocena.setNastavnik_predmet_odeljenje(nastavnikOdeljenja);
			   ocena.setUcenik(ucenik);
			   ocena.setAktivan();
			   ocena.setSemestar(ESemestar.valueOf(novaOcena.getSemestar()));
			   ocena.setVrednostOcene(novaOcena.getVrednostOcene());
			   ocena.setDatumOcene(new Date());
			   ocenaRepository.save(ocena);
				return ocena;
		   } catch (Exception e) {
				throw new Exception("Dodavanje nove ocene nije uspelo da se sačuva.");
		   }
	   }

}
