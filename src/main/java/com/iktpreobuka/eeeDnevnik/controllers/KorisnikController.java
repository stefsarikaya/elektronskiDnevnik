package com.iktpreobuka.eeeDnevnik.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.eeeDnevnik.controllers.util.RESTError;
import com.iktpreobuka.eeeDnevnik.entities.KorisnikEntity;
import com.iktpreobuka.eeeDnevnik.repositories.KorisnikRepository;
import com.iktpreobuka.eeeDnevnik.security.Views;


public class KorisnikController {
	
	@Autowired
	private KorisnikRepository korisnikRepository;




	@JsonView(Views.admin.class)
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAll(Principal principal) {
		//logger.info("!!!!!!!!!!!!!!!!!!!!!!!!!!! /project/users/getAll započeto.");
		//logger.info("Logovao se admin.");
		try {
			Iterable<KorisnikEntity> korisnici = korisnikRepository.findAll();
			//logger.info("All users (active, deleted, archived).");
			//logger.info("++++++++++++++++ Završeno sve ok.");
			return new ResponseEntity<Iterable<KorisnikEntity>>(korisnici, HttpStatus.OK);
		} catch(Exception e) {
			//logger.error("++++++++++++++++ Poruka o grešci:" + e.getMessage());
			return new ResponseEntity<RESTError>(new RESTError(1, "Napravljena greška: "+ e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}		
	
	
}
