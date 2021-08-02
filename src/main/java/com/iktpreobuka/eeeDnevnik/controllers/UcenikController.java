package com.iktpreobuka.eeeDnevnik.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.eeeDnevnik.controllers.util.RESTError;
import com.iktpreobuka.eeeDnevnik.entities.KorisnikEntity;
import com.iktpreobuka.eeeDnevnik.entities.KorisnikNalogEntity;
import com.iktpreobuka.eeeDnevnik.entities.UcenikEntity;
import com.iktpreobuka.eeeDnevnik.entities.dto.UcenikDto;
import com.iktpreobuka.eeeDnevnik.enumeration.EUloga;
import com.iktpreobuka.eeeDnevnik.repositories.KorisnikNalogRepository;
import com.iktpreobuka.eeeDnevnik.repositories.KorisnikRepository;
import com.iktpreobuka.eeeDnevnik.repositories.PredmetRepository;
import com.iktpreobuka.eeeDnevnik.repositories.RoditeljRepository;
import com.iktpreobuka.eeeDnevnik.repositories.UcenikOdeljenjeRepository;
import com.iktpreobuka.eeeDnevnik.repositories.UcenikRepository;
import com.iktpreobuka.eeeDnevnik.security.Views;
import com.iktpreobuka.eeeDnevnik.services.KorisnikNalogService;
import com.iktpreobuka.eeeDnevnik.services.UcenikService;

@Controller
@RestController
@RequestMapping(value= "/project/ucenik")
public class UcenikController {
	
	@Autowired
	private KorisnikNalogService korisnikNalogService;

	@Autowired
	private UcenikService ucenikService;

	@Autowired
	private RoditeljRepository roditeljRepository;

	@Autowired
	private UcenikRepository ucenikRepository;
	
	@Autowired
	private PredmetRepository predmetRepository;
	
	@Autowired
	private KorisnikNalogRepository korisnikNalogRepository;
	
	//@Autowired
	//private OdeljenjeRepository odeljenjeRepository;
	
	@Autowired
	private UcenikOdeljenjeRepository ucenikOdeljenjeRepository;

	@Autowired
	private KorisnikRepository korisnikRepository;
	

	@JsonView(Views.admin.class)
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> dodajNovogUcenikaa(@Valid @RequestBody UcenikDto noviUcenik) {
		if (noviUcenik == null) {
	        return new ResponseEntity<RESTError>(new RESTError(5, "Novi ucenik je prazan."), HttpStatus.BAD_REQUEST);
	      }
		if (noviUcenik.getIme() == null || noviUcenik.getPrezime() == null || noviUcenik.getSkolskiIdentifikacioniBroj() == null || noviUcenik.getDatumUpisa() == null|| noviUcenik.getPol() == null || noviUcenik.getJmbg() == null) {
			return new ResponseEntity<RESTError>(new RESTError(5, "Neki atributi su prazni."), HttpStatus.BAD_REQUEST);
		}
		KorisnikEntity korisnik = new UcenikEntity();
		try {
			if (noviUcenik.getJmbg() != null && ucenikRepository.getByJmbg(noviUcenik.getJmbg()) != null) {
				return new ResponseEntity<RESTError>(new RESTError(2, "Jmbg vec postoji."), HttpStatus.NOT_ACCEPTABLE);
			}
			if (noviUcenik.getSkolskiIdentifikacioniBroj() != null && ucenikRepository.getBySkolskiIdentifikacioniBroj(noviUcenik.getSkolskiIdentifikacioniBroj()) != null) {
				return new ResponseEntity<RESTError>(new RESTError(2, "Skolski identifikacijski broj vec postoji"), HttpStatus.NOT_ACCEPTABLE);
			}
			if (noviUcenik.getUloga() != null && !noviUcenik.getUloga().equals("ucenik")) {
		        return new ResponseEntity<RESTError>(new RESTError(2, "Uloga mora biti ucenik."), HttpStatus.NOT_ACCEPTABLE);
			}		
			if (noviUcenik.getUsername() != null && korisnikNalogRepository.getByUsername(noviUcenik.getUsername()) != null) {
		        return new ResponseEntity<RESTError>(new RESTError(2, "Username vec postoji."), HttpStatus.NOT_ACCEPTABLE);
		    }
			
			korisnik = ucenikService.dodajNovogUcenika(noviUcenik);
			if (noviUcenik.getUsername() != null && noviUcenik.getPassword() != null) {
				KorisnikNalogEntity nalog = korisnikNalogService.dodajNoviNalog(korisnik, noviUcenik.getUsername(), EUloga.ucenik, noviUcenik.getPassword());
				return new ResponseEntity<>(nalog, HttpStatus.OK);
			}
			return new ResponseEntity<>(korisnik, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(1, "Došlo je do greške: "+ e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@JsonView(Views.admin.class)
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAll(UcenikEntity ucenikEntity) {
		try {
			Iterable<UcenikEntity> ucenici= ucenikRepository.findByStatusLike(1);
			return new ResponseEntity<Iterable<UcenikEntity>>(ucenici, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(1, "Načinjena je greška: "+ e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@JsonView(Views.admin.class)
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> getById(@PathVariable Integer id) {
		try {
			UcenikEntity korisnik= ucenikRepository.findByIdAndStatusLike(id, 1);
			return new ResponseEntity<UcenikEntity>(korisnik, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(1, "Došlo je do greške: "+ e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

	@JsonView(Views.admin.class)
	@RequestMapping(method = RequestMethod.GET, value = "/izbrisani")
	public ResponseEntity<?> getAllDeleted(UcenikEntity ucenikEntity) {
		try {
			Iterable<UcenikEntity> korisnici= ucenikRepository.findByStatusLike(0);
			return new ResponseEntity<Iterable<UcenikEntity>>(korisnici, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(1, "Napravljena je greška: "+ e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@JsonView(Views.admin.class)
	@RequestMapping(method = RequestMethod.GET, value = "/arhivirani")
	public ResponseEntity<?> getAllArchived(UcenikEntity ucenikEntity) {
		try {
			Iterable<UcenikEntity> korisnici= ucenikRepository.findByStatusLike(-1);
			return new ResponseEntity<Iterable<UcenikEntity>>(korisnici, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(1, "Napravljena je greška: "+ e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
