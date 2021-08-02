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
import com.iktpreobuka.eeeDnevnik.entities.dto.KorisnikNalogDto;
import com.iktpreobuka.eeeDnevnik.enumeration.EUloga;
import com.iktpreobuka.eeeDnevnik.repositories.KorisnikNalogRepository;
import com.iktpreobuka.eeeDnevnik.repositories.KorisnikRepository;
import com.iktpreobuka.eeeDnevnik.security.Views;
import com.iktpreobuka.eeeDnevnik.services.KorisnikNalogService;

@Controller
@RestController
@RequestMapping(value = "/project/nalog")
public class KorisnikNalogController {

	@Autowired
	private KorisnikNalogService korisnikNalogService;

	@Autowired
	private KorisnikNalogRepository korisnikNalogRepository;

	@Autowired
	private KorisnikRepository korisnikRepository;


	@JsonView(Views.admin.class)
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> addNew(@Valid @RequestBody KorisnikNalogDto noviKorisnikNalog) {
		//logger.info("################ /project/account/addNew započeto.");
		//logger.info("Započeto dodavanje novog korisnika: ");

		if (noviKorisnikNalog == null) {
			//logger.info("---------------- Novi korisnički nalog je prazan.");
			return new ResponseEntity<RESTError>(new RESTError(5, "Novi korisnički nalog je prazan"),
					HttpStatus.BAD_REQUEST);
		}
		if (noviKorisnikNalog.getUsername() == null || noviKorisnikNalog.getUloga() == null
				|| noviKorisnikNalog.getKorisnikId() == null) {
			//logger.info("---------------- Neki atributi su prazni.");
			return new ResponseEntity<RESTError>(new RESTError(5, "Neki atributi su prazni."), HttpStatus.BAD_REQUEST);
		}

		KorisnikNalogEntity nalog = new KorisnikNalogEntity();

		try {
			KorisnikEntity korisnik = new KorisnikEntity();

			EUloga uloga = null;
			if (noviKorisnikNalog.getUloga().equals("admin"))
				;
			uloga = EUloga.admin;
			if (noviKorisnikNalog.getUloga().equals("roditelj"))
				;
			uloga = EUloga.roditelj;
			if (noviKorisnikNalog.getUloga().equals("ucenik"))
				;
			uloga = EUloga.ucenik;
			if (noviKorisnikNalog.getUloga().equals("nastavnik"))
				;
			uloga = EUloga.nastavnik;

			if (noviKorisnikNalog.getUsername() != null
					&& korisnikNalogRepository.getByUsername(noviKorisnikNalog.getUsername()) != null) {
				//logger.info("---------------- Username vec postoji.");
				return new ResponseEntity<RESTError>(new RESTError(2, "Username vec postoji."),
						HttpStatus.NOT_ACCEPTABLE);
			}
			if (noviKorisnikNalog.getKorisnikId() != null) {
				korisnik = korisnikRepository.getById(Integer.parseInt(noviKorisnikNalog.getKorisnikId()));
				if (korisnik == null) {
					//logger.info("---------------- Korisnik nije pronadjen.");
					return new ResponseEntity<RESTError>(new RESTError(4, "Korisnik nije pronadjen."),
							HttpStatus.NOT_FOUND);
				}
			}
			if (noviKorisnikNalog.getUsername() != null && noviKorisnikNalog.getPassword() != null) {
				nalog = korisnikNalogService.dodajNoviNalog(korisnik, noviKorisnikNalog.getUsername(), uloga,
						noviKorisnikNalog.getPassword());
			}
			//logger.info("---------------- Završeno OK.");
			return new ResponseEntity<>(nalog, HttpStatus.OK);
		} catch (NumberFormatException e) {
			//logger.error("!!!!!!!!!!! Format greška napravljena: " + e.getMessage());
			return new ResponseEntity<RESTError>(new RESTError(2, "Greška u fromatu: " + e.getLocalizedMessage()),
					HttpStatus.NOT_ACCEPTABLE);
		} catch (Exception e) {
			//logger.error("!!!!!!!!!!!!!!!!!! Desila se greška: " + e.getMessage());
			return new ResponseEntity<RESTError>(new RESTError(2, "Desila se greška:: " + e.getLocalizedMessage()),
					HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	
}	