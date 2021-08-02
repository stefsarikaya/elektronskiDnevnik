package com.iktpreobuka.eeeDnevnik.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.eeeDnevnik.controllers.util.RESTError;
import com.iktpreobuka.eeeDnevnik.entities.OdeljenjeEntity;
import com.iktpreobuka.eeeDnevnik.entities.RazredEntity;
import com.iktpreobuka.eeeDnevnik.entities.dto.OdeljenjeDto;
import com.iktpreobuka.eeeDnevnik.enumeration.ERazred;
import com.iktpreobuka.eeeDnevnik.repositories.KorisnikNalogRepository;
import com.iktpreobuka.eeeDnevnik.repositories.NastavnikRepository;
import com.iktpreobuka.eeeDnevnik.repositories.OdeljenjeRazredRepository;
import com.iktpreobuka.eeeDnevnik.repositories.OdeljenjeRepository;
import com.iktpreobuka.eeeDnevnik.repositories.PredmetRepository;
import com.iktpreobuka.eeeDnevnik.repositories.RazredRepository;
import com.iktpreobuka.eeeDnevnik.repositories.UcenikRepository;
import com.iktpreobuka.eeeDnevnik.security.Views;
import com.iktpreobuka.eeeDnevnik.services.OdeljenjeService;

@Controller
@RestController
@RequestMapping(value= "/project/odeljenje")
public class OdeljenjeController {
	
	@Autowired
	private OdeljenjeService odeljenjeService;

	@Autowired
	private OdeljenjeRepository odeljenjeRepository;
	
	@Autowired
	private OdeljenjeRazredRepository odeljenjeRazredRepository;
	
	@Autowired
	private KorisnikNalogRepository korisnikNalogRepository;

	@Autowired
	private UcenikRepository ucenikRepository;

	@Autowired
	private NastavnikRepository nastavnikRepository;

	@Autowired
	private PredmetRepository predmetRepository;

	@Autowired
	private RazredRepository razredRepository;
	
	@JsonView(Views.admin.class)
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> dodajNovo(@Valid @RequestBody OdeljenjeDto novoOdeljenje) {
		if (novoOdeljenje == null) {
	        return new ResponseEntity<RESTError>(new RESTError(5, "Novo odeljenje je prazno"), HttpStatus.BAD_REQUEST);
	      }
		if (novoOdeljenje.getOdeljenje_razred() == null || novoOdeljenje.getSkolskaGodina() == null || novoOdeljenje.getOdeljenjeOznaka() == null || novoOdeljenje.getGodinaUpisa() == null) {
	        return new ResponseEntity<RESTError>(new RESTError(5, "Neki od podataka su prazni."), HttpStatus.BAD_REQUEST);
		}
		if (novoOdeljenje.getOdeljenje_razred() != null && (novoOdeljenje.getOdeljenje_razred().equals(" ") || novoOdeljenje.getOdeljenje_razred().equals("") ) ) {
	        return new ResponseEntity<RESTError>(new RESTError(2, "Niste uneli ispravno podatke."), HttpStatus.NOT_ACCEPTABLE);
		}
		if (novoOdeljenje.getOdeljenjeOznaka() != null && (novoOdeljenje.getOdeljenjeOznaka().equals(" ") || novoOdeljenje.getOdeljenjeOznaka().equals("") ) ) {
	        return new ResponseEntity<RESTError>(new RESTError(2, "Niste uneli ispravnu oznaku odeljenja"), HttpStatus.NOT_ACCEPTABLE);
		}
		if (novoOdeljenje.getGodinaUpisa() != null && (novoOdeljenje.getGodinaUpisa().equals(" ") || novoOdeljenje.getGodinaUpisa().equals("") ) ) {
	        return new ResponseEntity<RESTError>(new RESTError(2, "Godina upisa mora biti dodata."), HttpStatus.NOT_ACCEPTABLE);
		}
		if (novoOdeljenje.getSkolskaGodina() != null && (novoOdeljenje.getSkolskaGodina().equals(" ") || novoOdeljenje.getSkolskaGodina().equals("") ) ) {
	        return new ResponseEntity<RESTError>(new RESTError(2, "Školska godine nije ispravno uneta"), HttpStatus.NOT_ACCEPTABLE);
		}
		OdeljenjeEntity odeljenje = new OdeljenjeEntity();
		try {
			if (novoOdeljenje.getOdeljenjeOznaka() != null && novoOdeljenje.getGodinaUpisa() != null && odeljenjeRepository.findByOdeljenjeOznakaAndGodinaUpisaAndStatusLike(novoOdeljenje.getOdeljenjeOznaka(), novoOdeljenje.getGodinaUpisa(), 1) != null) {
				return new ResponseEntity<RESTError>(new RESTError(2, "Oznaka odeljenja za godinu upisa vec postoji"), HttpStatus.NOT_ACCEPTABLE);
			}
			RazredEntity razredd = razredRepository.findByRazredBrojAndStatusLike(ERazred.valueOf(novoOdeljenje.getOdeljenje_razred()), 1);
			if (razredd==null || razredd.getStatus()!=1) {
				return new ResponseEntity<RESTError>(new RESTError(4, "Razred nije pronadjen."), HttpStatus.NOT_FOUND);
			}
			odeljenje = odeljenjeService.dodajNovoOdeljenje(novoOdeljenje);		
			return new ResponseEntity<>(odeljenje, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(1, "Napravljana je greška: "+ e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	
}
