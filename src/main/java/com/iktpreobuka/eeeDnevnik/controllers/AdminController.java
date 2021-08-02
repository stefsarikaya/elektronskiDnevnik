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
import com.iktpreobuka.eeeDnevnik.entities.AdminEntity;
import com.iktpreobuka.eeeDnevnik.entities.KorisnikEntity;
import com.iktpreobuka.eeeDnevnik.entities.KorisnikNalogEntity;
import com.iktpreobuka.eeeDnevnik.entities.dto.AdminDto;
import com.iktpreobuka.eeeDnevnik.enumeration.EUloga;
import com.iktpreobuka.eeeDnevnik.repositories.AdminRepository;
import com.iktpreobuka.eeeDnevnik.repositories.KorisnikNalogRepository;
import com.iktpreobuka.eeeDnevnik.repositories.KorisnikRepository;
import com.iktpreobuka.eeeDnevnik.security.Views;
import com.iktpreobuka.eeeDnevnik.services.AdminService;
import com.iktpreobuka.eeeDnevnik.services.KorisnikNalogService;



@Controller
@RestController
@RequestMapping(value = "/project/admin")
public class AdminController {
	
	@Autowired
	private KorisnikNalogService korisnikNalogService;

	@Autowired
	private AdminService adminService;

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private KorisnikNalogRepository korisnikNalogRepository;
	
	@Autowired
	private KorisnikRepository korisnikRepository;
	
	


	@JsonView(Views.admin.class)
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> addNewAdmin(@Valid @RequestBody AdminDto noviAdmin) {
		
		if (noviAdmin == null) {
			//logger.info("---------------- Novi Admin je prazan.");
			return new ResponseEntity<RESTError>(new RESTError(5, "Novi admin je prazan"), HttpStatus.BAD_REQUEST);
		}
		if (noviAdmin.getIme() == null || noviAdmin.getPrezime() == null || noviAdmin.getEmail() == null
				|| noviAdmin.getMobilniTelefon() == null || noviAdmin.getPol() == null
				|| noviAdmin.getJmbg() == null) {
		// logger.info("---------------- Neki od atributa su prazni.");
			return new ResponseEntity<RESTError>(new RESTError(5, "Neki od atributa su prazni."),
					HttpStatus.BAD_REQUEST);
		}
		KorisnikEntity korisnik = new AdminEntity();
		try {
			if (noviAdmin.getJmbg() != null && adminRepository.getByJmbg(noviAdmin.getJmbg()) != null
					&& korisnikRepository.getByJmbg(noviAdmin.getJmbg()) == null) {
				// logger.info("---------------- JMBG vec postoji.");
				return new ResponseEntity<RESTError>(new RESTError(2, "JMBG avec postoji."),
						HttpStatus.NOT_ACCEPTABLE);
			}
			if (noviAdmin.getEmail() != null && adminRepository.getByEmail(noviAdmin.getEmail()) != null) {
			//	logger.info("---------------- Email vec postoji.");
				return new ResponseEntity<RESTError>(new RESTError(2, "Email vec postoji."),
						HttpStatus.NOT_ACCEPTABLE);
			}
			if (noviAdmin.getUloga() != null && !noviAdmin.getUloga().equals("admin")) {
				// logger.info("---------------- Uloga mora biti admin.");
				return new ResponseEntity<RESTError>(new RESTError(2, "Uloga mora biti admin."),
						HttpStatus.NOT_ACCEPTABLE);
			}
			if (noviAdmin.getUsername() != null && korisnikNalogRepository.getByUsername(noviAdmin.getUsername()) != null) {
				// logger.info("---------------- Username vec postoji.");
				return new ResponseEntity<RESTError>(new RESTError(2, "Username already exist."),
						HttpStatus.NOT_ACCEPTABLE);
			}
			korisnik = adminService.dodajNovogAdmin(noviAdmin);
			// logger.info("Admin kreiran.");
			if (noviAdmin.getUsername() != null && noviAdmin.getPassword() != null) {
				KorisnikNalogEntity nalog = korisnikNalogService.dodajNoviNalog(korisnik, noviAdmin.getUsername(),
						EUloga.admin, noviAdmin.getPassword());
				// logger.info("---------------- Sve je u redu.");
				return new ResponseEntity<>(nalog, HttpStatus.OK);
			}
			// logger.info("---------------- Završeno OK.");
			return new ResponseEntity<>(korisnik, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(1, "Desila se greška: " + e.getLocalizedMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@JsonView(Views.admin.class)
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAll(AdminEntity adminEntity) {
		//logger.info("????????? /project/admin/getAll zapoceto.");
		// logger.info("Logovani user: " + principal.getName());
		try {
			Iterable<AdminEntity> korisnici = adminRepository.findByStatusLike(1);
			// logger.info("+++++++++++ Završeno sve OK.");
			return new ResponseEntity<Iterable<AdminEntity>>(korisnici, HttpStatus.OK);
		} catch (Exception e) {
	//		logger.error("---------------- Pronadjena greška u kodu: " + e.getMessage());
			return new ResponseEntity<RESTError>(new RESTError(1, "Nacinjena je greska: " + e.getLocalizedMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@JsonView(Views.admin.class)
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> getById(@PathVariable Integer id) {
		//logger.info("################ /project/admin/getById started.");
		//logger.info("Logovani novi admin! ");
		try {
			AdminEntity korisnik = adminRepository.findByIdAndStatusLike(id, 1);
			// logger.info("!!!!!!!!! Sve je u redu.");
			return new ResponseEntity<>(korisnik, HttpStatus.OK);
		} catch (Exception e) {
			// logger.error("................ GREŠKA!!!!!: " + e.getMessage());
			return new ResponseEntity<RESTError>(new RESTError(1, "Greška u generisanju: " + e.getLocalizedMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

	@JsonView(Views.admin.class)
	@RequestMapping(method = RequestMethod.GET, value = "/obrisani")
	public ResponseEntity<?> getAllDeleted(AdminEntity adminEntity) {
		//logger.info("!!!!!!!!!!!!!!!!!!!!! /project/admin/obrisani/getAllDeleted započeto.");
		// logger.info("Ulogovan je novi admin ");
		try {
			Iterable<AdminEntity> korisnici = adminRepository.findByStatusLike(0);
			// logger.info("---------------- Završeno OK.");
			return new ResponseEntity<Iterable<AdminEntity>>(korisnici, HttpStatus.OK);
		} catch (Exception e) {
			// logger.error("++++++++++++++++++++++++ Napravljena greška: " + e.getMessage());
			return new ResponseEntity<RESTError>(new RESTError(1, "Napravljena greška: " + e.getLocalizedMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@JsonView(Views.admin.class)
	@RequestMapping(method = RequestMethod.GET, value = "/arhivirani")
	public ResponseEntity<?> getAllArchived(AdminEntity adminEntity) {
		// logger.info("################ /project/admin/arhivirani/getAllArchived započeto.");
		// logger.info("Logged username: " + principal.getName());
		try {
			Iterable<AdminEntity> korisnici = adminRepository.findByStatusLike(-1);
			//	logger.info("---------------- Sve je OK.");
			return new ResponseEntity<Iterable<AdminEntity>>(korisnici, HttpStatus.OK);
		} catch (Exception e) {
			// logger.error("++++++++++++++++ Nacinjena greska: " + e.getMessage());
			return new ResponseEntity<RESTError>(new RESTError(1, "Nacinjena greska: " + e.getLocalizedMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
