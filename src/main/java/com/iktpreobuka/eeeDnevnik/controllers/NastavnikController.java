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
import com.iktpreobuka.eeeDnevnik.entities.KorisnikEntity;
import com.iktpreobuka.eeeDnevnik.entities.KorisnikNalogEntity;
import com.iktpreobuka.eeeDnevnik.entities.NastavnikEntity;
import com.iktpreobuka.eeeDnevnik.entities.dto.NastavnikDto;
import com.iktpreobuka.eeeDnevnik.enumeration.EUloga;
import com.iktpreobuka.eeeDnevnik.repositories.KorisnikNalogRepository;
import com.iktpreobuka.eeeDnevnik.repositories.KorisnikRepository;
import com.iktpreobuka.eeeDnevnik.repositories.NastavnikPredmetOdeljenjeRepository;
import com.iktpreobuka.eeeDnevnik.repositories.NastavnikRepository;
import com.iktpreobuka.eeeDnevnik.repositories.OdeljenjeRepository;
import com.iktpreobuka.eeeDnevnik.repositories.PredmetRepository;
import com.iktpreobuka.eeeDnevnik.security.Views;
import com.iktpreobuka.eeeDnevnik.services.KorisnikNalogService;
import com.iktpreobuka.eeeDnevnik.services.NastavnikService;

@Controller
@RestController
@RequestMapping(value= "/project/nastavnik")
public class NastavnikController {
	
	@Autowired
	private KorisnikNalogService korisnikNalogService;

	@Autowired
	private NastavnikService nastavnikService;

	@Autowired
	private NastavnikRepository nastavnikRepository;
	
	@Autowired
	private PredmetRepository predmetRepository;
	
	@Autowired
	private OdeljenjeRepository odeljenjeRepository;
	
	@Autowired
	private KorisnikRepository korisnikRepository;
	
	@Autowired
	private KorisnikNalogRepository korisnikNalogRepository;

	@Autowired
	private NastavnikPredmetOdeljenjeRepository nastavnikPredmetOdeljenjeRepository;
	
	
	
	@JsonView(Views.admin.class)
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> dodajNovogNastavnika(@Valid @RequestBody NastavnikDto noviNastavnik) {
		if (noviNastavnik == null) {
	        return new ResponseEntity<RESTError>(new RESTError(5, "Novi nastavnik je prazan."), HttpStatus.BAD_REQUEST);
	      }
		if (noviNastavnik.getIme() == null || noviNastavnik.getPrezime() == null || noviNastavnik.getDiploma() == null || noviNastavnik.getDatumZaposlenja() == null || noviNastavnik.getPol() == null || noviNastavnik.getJmbg() == null) {
			return new ResponseEntity<RESTError>(new RESTError(5, "Neki od atributa su prazni"), HttpStatus.BAD_REQUEST);
		}
		KorisnikEntity korisnik = new NastavnikEntity();
		try {
			if (noviNastavnik.getJmbg() != null && nastavnikRepository.getByJmbg(noviNastavnik.getJmbg()) != null) {
		        return new ResponseEntity<RESTError>(new RESTError(2, "Jmbg vec postoji."), HttpStatus.NOT_ACCEPTABLE);
			}
			if (noviNastavnik.getUloga() != null && !noviNastavnik.getUloga().equals("nastavnik")) {
		        return new ResponseEntity<RESTError>(new RESTError(2, "Uloga mora biti nastavnik"), HttpStatus.NOT_ACCEPTABLE);
			}		
			if (noviNastavnik.getUsername() != null && korisnikNalogRepository.getByUsername(noviNastavnik.getUsername()) != null) {
		        return new ResponseEntity<RESTError>(new RESTError(2, "Username vec postoji."), HttpStatus.NOT_ACCEPTABLE);
		      }
			korisnik = nastavnikService.dodajNovogNastavnika(noviNastavnik);
			if (noviNastavnik.getUsername() != null && noviNastavnik.getPassword() != null) {
				KorisnikNalogEntity nalog = korisnikNalogService.dodajNoviNalog(korisnik,noviNastavnik.getUsername(), EUloga.nastavnik, noviNastavnik.getPassword());
				return new ResponseEntity<>(nalog, HttpStatus.OK);
			}
			return new ResponseEntity<>(korisnik, HttpStatus.OK);
		} catch (NumberFormatException e) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Greška u formatu: "+ e.getLocalizedMessage()), HttpStatus.NOT_ACCEPTABLE);
		} catch (Exception e) {
			if (korisnik != null && nastavnikRepository.findByIdAndStatusLike(korisnik.getId(), 1) != null) {
				korisnikRepository.deleteById(korisnik.getId());
			}
			return new ResponseEntity<RESTError>(new RESTError(1, "Načinjena je greška: "+ e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
