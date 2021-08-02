/*
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
import com.iktpreobuka.eeeDnevnik.entities.NastavnikEntity;
import com.iktpreobuka.eeeDnevnik.entities.NastavnikPredmetOdeljenjeEntity;
import com.iktpreobuka.eeeDnevnik.entities.OcenaEntity;
import com.iktpreobuka.eeeDnevnik.entities.PredmetEntity;
import com.iktpreobuka.eeeDnevnik.entities.RoditeljEntity;
import com.iktpreobuka.eeeDnevnik.entities.UcenikEntity;
import com.iktpreobuka.eeeDnevnik.entities.dto.OcenaDto;
import com.iktpreobuka.eeeDnevnik.models.EmailObject;
import com.iktpreobuka.eeeDnevnik.repositories.KorisnikNalogRepository;
import com.iktpreobuka.eeeDnevnik.repositories.NastavnikPredmetOdeljenjeRepository;
import com.iktpreobuka.eeeDnevnik.repositories.NastavnikRepository;
import com.iktpreobuka.eeeDnevnik.repositories.OcenaRepository;
import com.iktpreobuka.eeeDnevnik.repositories.PredmetRepository;
import com.iktpreobuka.eeeDnevnik.repositories.RoditeljRepository;
import com.iktpreobuka.eeeDnevnik.repositories.UcenikRepository;
import com.iktpreobuka.eeeDnevnik.security.Views;
import com.iktpreobuka.eeeDnevnik.services.EmailService;
import com.iktpreobuka.eeeDnevnik.services.OcenaService;

@Controller
@RestController
@RequestMapping(value= "/project/ocena")
public class OcenaController {
	
	
	@Autowired
	private KorisnikNalogRepository korisnikNalogRepository;

	@Autowired
	private OcenaRepository ocenaRepository;

	@Autowired
	private UcenikRepository ucenikRepository;

	@Autowired
	private NastavnikRepository nastavnikRepository;

	@Autowired
	private RoditeljRepository roditeljRepository;

	@Autowired
	private PredmetRepository predmetRepository;

	@Autowired
	private OcenaService ocenaService;

	@Autowired
	private EmailService emailService;
	
	@Autowired
	private NastavnikPredmetOdeljenjeRepository nastavnikPredmetOdeljenjeRepository;
	
	

	
	
	@JsonView(Views.nastavnik.class)
	@RequestMapping(method = RequestMethod.POST, value = "/{id}")
	public ResponseEntity<?> dodajNovuOcenu(@Valid @RequestBody OcenaDto novaOcena, @PathVariable id) {
		if (novaOcena == null) {
	        return new ResponseEntity<RESTError>(new RESTError(5, "Nova ocena je prazna."), HttpStatus.BAD_REQUEST);
	      }
		if (novaOcena.getUcenik() == null && novaOcena.getPredmet() == null && novaOcena.getVrednostOcene() == null && novaOcena.getSemestar() == null) {
	        return new ResponseEntity<RESTError>(new RESTError(5, "Neki od podataka su prazni"), HttpStatus.BAD_REQUEST);
		}
		OcenaEntity ocena = new OcenaEntity();
		try {
			UcenikEntity ucenik = ucenikRepository.findByIdAndStatusLike(Integer.parseInt(novaOcena.getUcenik()), 1);
			if (ucenik==null || ucenik.getStatus()!=1) {
				return new ResponseEntity<RESTError>(new RESTError(4, "Ucenik nije pronadjen."), HttpStatus.NOT_FOUND);
			}
			PredmetEntity predmet = predmetRepository.findByIdAndStatusLike(Integer.parseInt(novaOcena.getPredmet()), 1);
				return new ResponseEntity<RESTError>(new RESTError(4, "Predmet nije p."), HttpStatus.NOT_FOUND);
			
			NastavnikEntity nastavnik = nastavnikRepository.findByIdAndStatusLike(id, 1);
			NastavnikPredmetOdeljenjeEntity nastavnikOdeljneja = nastavnikPredmetOdeljenjeRepository.getByPredajuciNastavnikAndPredajePredmetAndPredajeOdeljenjeAndPredajeRazred(nastavnik, predmet, ucenik);
			if (nastavnikOdeljneja==null) {
				return new ResponseEntity<RESTError>(new RESTError(3, "Greška."), HttpStatus.FORBIDDEN);
			}
			ocena = ocenaService.dodajNovuOcenu(nastavnik, ucenik, nastavnikOdeljneja, novaOcena);		
			if (ocena != null) {
				RoditeljEntity r = new RoditeljEntity();
					r=ucenik.getRoditelj();
					if (r.getStatus() == 1) {
						EmailObject email = new EmailObject(r.getEmail(), "Nova ocena ucenika " + ucenik.getIme() + " " + ucenik.getPrezime(), "Ucenik " + ucenik.getIme() + " " + ucenik.getPrezime() + " je dobio ocenu " + ocena.getVrednostOcene().toString() + " iz predmeta " + predmet.getImePredmet() + " kod profesora " + nastavnik.getIme() + " " + nastavnik.getPrezime() + ".");
						emailService.sendSimpleMessage(email);
					}
			}
			return new ResponseEntity<OcenaEntity>(ocena, HttpStatus.OK);
		} catch (NumberFormatException e) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Greška u formatu (brojčana) "+ e.getLocalizedMessage()), HttpStatus.NOT_ACCEPTABLE);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(1, "Došlo je do greške: "+ e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	*/
