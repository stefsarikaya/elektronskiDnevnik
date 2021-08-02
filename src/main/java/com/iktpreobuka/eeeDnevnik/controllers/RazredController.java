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
import com.iktpreobuka.eeeDnevnik.entities.OdeljenjeEntity;
import com.iktpreobuka.eeeDnevnik.entities.OdeljenjeRazredEntity;
import com.iktpreobuka.eeeDnevnik.entities.RazredEntity;
import com.iktpreobuka.eeeDnevnik.entities.dto.RazredDto;
import com.iktpreobuka.eeeDnevnik.enumeration.ERazred;
import com.iktpreobuka.eeeDnevnik.repositories.KorisnikNalogRepository;
import com.iktpreobuka.eeeDnevnik.repositories.OdeljenjeRepository;
import com.iktpreobuka.eeeDnevnik.repositories.RazredRepository;
import com.iktpreobuka.eeeDnevnik.security.Views;
import com.iktpreobuka.eeeDnevnik.services.RazredService;

@Controller
@RestController
@RequestMapping(value= "/project/razred")
public class RazredController {
	
	@Autowired
	private RazredService razredService;

	@Autowired
	private RazredRepository razredRepository;
	
	@Autowired
	private OdeljenjeRepository odeljenjeRepository;

	@Autowired
	private KorisnikNalogRepository korisnikNalogRepository;


	// @Autowired
	// private PredmetRepository predmetRepository;
	
	@JsonView(Views.admin.class)
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> dodajNovog(@RequestBody RazredDto noviRazred) {
		// @Valid
		// if (noviRazred == null || noviRazred.getRazredBroj() == null || noviRazred.getOdeljenje() != null || noviRazred.getOdeljenja() != null || noviRazred.getPredmet() != null || noviRazred.getPredmeti() != null) {
	    //     return new ResponseEntity<RESTError>(new RESTError(5, "Neki od unešenih atributa su prazni."), HttpStatus.BAD_REQUEST);
	    //   }
		RazredEntity razredd = new RazredEntity();
		try {
			if (noviRazred.getRazredBroj() != null && razredRepository.findByRazredBrojAndStatusLike(ERazred.valueOf(noviRazred.getRazredBroj()), 1) != null) {
		        return new ResponseEntity<RESTError>(new RESTError(2, "Već postoji taj razred."), HttpStatus.NOT_ACCEPTABLE);
			}
			if (noviRazred.getRazredBroj() != null && !noviRazred.getRazredBroj().equals(" ") && !noviRazred.getRazredBroj().equals("")) {
				razredd = razredService.dodajNoviRazred(noviRazred);
			}
			return new ResponseEntity<>(razredd, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(1, "Doslo je do greske: "+ e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

	@JsonView(Views.admin.class)
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAll(RazredEntity razred) {
		try {
			Iterable<RazredEntity> razredi= razredRepository.findByStatusLike(1);
			return new ResponseEntity<Iterable<RazredEntity>>(razredi, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(1, "Doslo je do greske"+ e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

	@JsonView(Views.admin.class)
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> getById(@PathVariable String id, RazredEntity razred) {
		try {
			RazredEntity razredd = razredRepository.findByIdAndStatusLike(Integer.parseInt(id), 1);
			return new ResponseEntity<RazredEntity>(razredd, HttpStatus.OK);
		} catch (NumberFormatException e) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Greska pri genersianju "+ e.getLocalizedMessage()), HttpStatus.NOT_ACCEPTABLE);
		} catch(Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(1, "Napravljena je greška: "+ e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

	@JsonView(Views.admin.class)
	@RequestMapping(method = RequestMethod.GET, value = "/izbrisani")
	public ResponseEntity<?> getAllDeleted(RazredEntity razred) {
		try {
			Iterable<RazredEntity> razredi= razredRepository.findByStatusLike(0);
			return new ResponseEntity<Iterable<RazredEntity>>(razredi, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(1, "Doslo je do greske "+ e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@JsonView(Views.admin.class)
	@RequestMapping(method = RequestMethod.GET, value = "/arhivirani")
	public ResponseEntity<?> getAllArchived(RazredEntity razred) {
		try {
			Iterable<RazredEntity> razredi= razredRepository.findByStatusLike(-1);
			return new ResponseEntity<Iterable<RazredEntity>>(razredi, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(1, "Doslo je do greske: "+ e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@JsonView(Views.admin.class)
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<?> modify(@PathVariable String id, @Valid @RequestBody RazredDto azurirajRazredd) {
		if (id == null || azurirajRazredd == null || azurirajRazredd.getRazredBroj() == null || azurirajRazredd.getOdeljenje() != null || azurirajRazredd.getOdeljenja() != null || azurirajRazredd.getPredmet() != null || azurirajRazredd.getPredmeti() != null) {
	        return new ResponseEntity<RESTError>(new RESTError(5, "Neki od unešenih podataka su prazni ili pogrešno uneti."), HttpStatus.BAD_REQUEST);
	      }
		RazredEntity razredd = new RazredEntity();
		try {
			razredd = razredRepository.findByIdAndStatusLike(Integer.parseInt(id), 1);
			if (razredd==null) { 
		        return new ResponseEntity<RESTError>(new RESTError(4, "Razred koji želite da izmenite nije pronadjen."), HttpStatus.NOT_FOUND);
			}
			if (azurirajRazredd.getRazredBroj() != null && ERazred.valueOf(azurirajRazredd.getRazredBroj()).equals(razredd.getRazredBroj()) ) {
				azurirajRazredd.setRazredBroj(null);
			}
			if (azurirajRazredd.getRazredBroj() != null && razredRepository.findByRazredBrojAndStatusLike(ERazred.valueOf(azurirajRazredd.getRazredBroj()), 1) != null) {
		        return new ResponseEntity<RESTError>(new RESTError(2, "Oznaka za odeljenje vec postoji."), HttpStatus.NOT_ACCEPTABLE);
			}
			if (azurirajRazredd.getRazredBroj() != null && !azurirajRazredd.getRazredBroj().equals(" ") && !azurirajRazredd.getRazredBroj().equals("")) {
		
				razredService.izmeniRazred(razredd, azurirajRazredd);
			}
			return new ResponseEntity<>(razredd, HttpStatus.OK);
		} catch (NumberFormatException e) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Načine greška pri generisanju "+ e.getLocalizedMessage()), HttpStatus.NOT_ACCEPTABLE);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(1, "Došlo je do greške: "+ e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	// UKLONI ODELJENJE IZ RAZREDA 
	
	@JsonView(Views.admin.class)
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}/ukloni_odeljenje/{o_id}")
	public ResponseEntity<?> ukloniOdeljenjeIzRazreda(@PathVariable String id, @PathVariable String o_id) {;
		if (id == null || o_id == null) {
	        return new ResponseEntity<RESTError>(new RESTError(5, "Neki podaci su prazni."), HttpStatus.BAD_REQUEST);
	      }
		OdeljenjeRazredEntity or = new OdeljenjeRazredEntity();
		try {
			RazredEntity razredd = razredRepository.findByIdAndStatusLike(Integer.parseInt(id), 1);
			if (razredd==null) {
		        return new ResponseEntity<RESTError>(new RESTError(4, "Razred nije pronadjen."), HttpStatus.NOT_FOUND);
			} 
			OdeljenjeEntity odeljenje = odeljenjeRepository.findByIdAndStatusLike(Integer.parseInt(o_id), 1);
			if (odeljenje==null) {
		        return new ResponseEntity<RESTError>(new RESTError(4, "Odeljenje nije pronajdeno."), HttpStatus.NOT_FOUND);
			}
			or = razredService.ukloniOdeljenjeIzRazreda(razredd, odeljenje); 
			return new ResponseEntity<OdeljenjeRazredEntity>(or, HttpStatus.OK);
		} catch (NumberFormatException e) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Greška pri generisanju: "+ e.getLocalizedMessage()), HttpStatus.NOT_ACCEPTABLE);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(1, "Exception occurred: "+ e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

	@JsonView(Views.admin.class)
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}/dodaj_odeljenje/{o_id}/skolskaGodina/{skolskaGodina}")
	public ResponseEntity<?> dodajOdeljenjeRazredu(@PathVariable String id, @PathVariable String o_id, @PathVariable String skolskaGodina) {
		if (id == null || o_id == null || skolskaGodina == null) {
	        return new ResponseEntity<RESTError>(new RESTError(5, "Neki od podataka su prazni."), HttpStatus.BAD_REQUEST);
	      }
		OdeljenjeRazredEntity ore = new OdeljenjeRazredEntity();
		try {
			RazredEntity razredd = razredRepository.findByIdAndStatusLike(Integer.parseInt(id), 1);
			if (razredd==null) {
		        return new ResponseEntity<RESTError>(new RESTError(4, "Razred nije proandjen."), HttpStatus.NOT_FOUND);
			} 
				OdeljenjeEntity odeljenje = odeljenjeRepository.findByIdAndStatusLike(Integer.parseInt(o_id), 1);
			if (odeljenje==null) {
		        return new ResponseEntity<RESTError>(new RESTError(4, "Odeljenje nije pronadjeno."), HttpStatus.NOT_FOUND);
			}
			if (skolskaGodina != null && !skolskaGodina.equals(" ") && !skolskaGodina.equals("")) {
				ore = razredService.dodajOdeljenjeRazredu(razredd, odeljenje, skolskaGodina);
			}
			return new ResponseEntity<OdeljenjeRazredEntity>(ore, HttpStatus.OK);
		} catch (NumberFormatException e) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Napravljena je greska: "+ e.getLocalizedMessage()), HttpStatus.NOT_ACCEPTABLE);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(1, "Greska: "+ e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

}
