package com.iktpreobuka.eeeDnevnik.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktpreobuka.eeeDnevnik.entities.OdeljenjeEntity;
import com.iktpreobuka.eeeDnevnik.entities.OdeljenjeRazredEntity;
import com.iktpreobuka.eeeDnevnik.entities.RazredEntity;
import com.iktpreobuka.eeeDnevnik.entities.dto.OdeljenjeDto;
import com.iktpreobuka.eeeDnevnik.enumeration.ERazred;
import com.iktpreobuka.eeeDnevnik.repositories.NastavnikPredmetOdeljenjeRepository;
import com.iktpreobuka.eeeDnevnik.repositories.OdeljenjeRazredRepository;
import com.iktpreobuka.eeeDnevnik.repositories.OdeljenjeRepository;
import com.iktpreobuka.eeeDnevnik.repositories.RazredRepository;
import com.iktpreobuka.eeeDnevnik.repositories.RazredniStaresinaOdeljenjeRepository;
import com.iktpreobuka.eeeDnevnik.repositories.UcenikOdeljenjeRepository;

@Service
public class OdeljenjeServiceImpl implements OdeljenjeService{
	
	@Autowired
	private OdeljenjeRepository odeljenjeRepository;

	@Autowired
	private RazredRepository razredRepository;
	
	@Autowired
	private OdeljenjeRazredRepository odeljenjeRazredRepository;
	
	@Autowired
	private UcenikOdeljenjeRepository ucenikOdeljenjeRepository;

	@Autowired
	private RazredniStaresinaOdeljenjeRepository razredniStaresinaOdeljenjeRepository;
	
	@Autowired
	private NastavnikPredmetOdeljenjeRepository nastavnikPredmetOdeljenjeRepository;
	
	@Override
	public OdeljenjeEntity dodajNovoOdeljenje(OdeljenjeDto novoOdeljenje) throws Exception {
		try {
			if (novoOdeljenje.getOdeljenjeOznaka() == null || novoOdeljenje.getGodinaUpisa() == null || novoOdeljenje.getOdeljenje_razred() == null) {
			     throw new Exception("Neki od podataka su prazni.");
			}
		} catch (Exception e) {
			throw new Exception("Nije se učitao OdeljenjeDto.");
		}
		OdeljenjeEntity odeljenje = new OdeljenjeEntity();
		try {
			odeljenje.setOdeljenjeOznaka(novoOdeljenje.getOdeljenjeOznaka());
			odeljenje.setGodinaUpisa(novoOdeljenje.getGodinaUpisa());
			odeljenje.setAktivan();
			odeljenjeRepository.save(odeljenje);
			RazredEntity razredd = razredRepository.findByRazredBrojAndStatusLike(ERazred.valueOf(novoOdeljenje.getOdeljenje_razred()), 1);
			OdeljenjeRazredEntity odeljenjeRarezred = new OdeljenjeRazredEntity(razredd, odeljenje, novoOdeljenje.getSkolskaGodina());
			odeljenjeRazredRepository.save(odeljenjeRarezred);
			odeljenje.getRazredi().add(odeljenjeRarezred);
			odeljenjeRepository.save(odeljenje);
			return odeljenje;
		} catch (Exception e) {
			throw new Exception("Dodavanje novog odeljenja nije uspelo.");
		}
	}


}
