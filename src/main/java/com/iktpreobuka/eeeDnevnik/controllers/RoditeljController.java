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
import com.iktpreobuka.eeeDnevnik.entities.RoditeljEntity;
import com.iktpreobuka.eeeDnevnik.entities.UcenikEntity;
import com.iktpreobuka.eeeDnevnik.entities.dto.NadjiRoditeljaDto;
import com.iktpreobuka.eeeDnevnik.entities.dto.RoditeljDto;
import com.iktpreobuka.eeeDnevnik.enumeration.EUloga;
import com.iktpreobuka.eeeDnevnik.repositories.KorisnikNalogRepository;
import com.iktpreobuka.eeeDnevnik.repositories.KorisnikRepository;
import com.iktpreobuka.eeeDnevnik.repositories.RoditeljRepository;
import com.iktpreobuka.eeeDnevnik.repositories.UcenikRepository;
import com.iktpreobuka.eeeDnevnik.security.Views;
import com.iktpreobuka.eeeDnevnik.services.KorisnikNalogService;
import com.iktpreobuka.eeeDnevnik.services.RoditeljService;

@Controller
@RestController
@RequestMapping(value = "/project/roditelj")
public class RoditeljController {

	@Autowired
	private KorisnikNalogService korisnikNalogService;

	@Autowired
	private RoditeljService roditeljService;

	@Autowired
	private UcenikRepository ucenikRepository;

	@Autowired
	private RoditeljRepository roditeljRepository;

	@Autowired
	private KorisnikRepository korisnikRepository;

	@Autowired
	private KorisnikNalogRepository korisnikNalogRepository;

	@JsonView(Views.admin.class)
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> dodajNovogRoditelja(@Valid @RequestBody RoditeljDto noviRoditelj) {
		if (noviRoditelj == null) {
			return new ResponseEntity<RESTError>(new RESTError(5, "Novi roditelj je prazan."), HttpStatus.BAD_REQUEST);
		}
		if (noviRoditelj.getIme() == null || noviRoditelj.getPrezime() == null || noviRoditelj.getEmail() == null
				|| noviRoditelj.getPol() == null || noviRoditelj.getJmbg() == null) {
			return new ResponseEntity<RESTError>(new RESTError(5, "Neki od atributa su prazni."),
					HttpStatus.BAD_REQUEST);
		}
		KorisnikEntity korisnik = new RoditeljEntity();
		try {
			if (noviRoditelj.getJmbg() != null && roditeljRepository.getByJmbg(noviRoditelj.getJmbg()) != null) {
				return new ResponseEntity<RESTError>(new RESTError(2, "JMBG vec postoji."), HttpStatus.NOT_ACCEPTABLE);
			}
			if (noviRoditelj.getEmail() != null && roditeljRepository.getByEmail(noviRoditelj.getEmail()) != null) {
				return new ResponseEntity<RESTError>(new RESTError(2, "Email vec postoji."), HttpStatus.NOT_ACCEPTABLE);
			}
			if (noviRoditelj.getUloga() != null && !noviRoditelj.getUloga().equals("roditelj")) {
				return new ResponseEntity<RESTError>(new RESTError(2, "Uloga mora biti roditelj."),
						HttpStatus.NOT_ACCEPTABLE);
			}
			if (noviRoditelj.getUsername() != null
					&& korisnikNalogRepository.getByUsername(noviRoditelj.getUsername()) != null) {
				return new ResponseEntity<RESTError>(new RESTError(2, "Username vec postoji."),
						HttpStatus.NOT_ACCEPTABLE);
			}
			korisnik = roditeljService.dodajNovogRoditelja(noviRoditelj);
			if (noviRoditelj.getUsername() != null && noviRoditelj.getPassword() != null) {
				KorisnikNalogEntity nalog = korisnikNalogService.dodajNoviNalog(korisnik, noviRoditelj.getUsername(),
						EUloga.roditelj, noviRoditelj.getPassword());
				return new ResponseEntity<>(nalog, HttpStatus.OK);
			}
			return new ResponseEntity<>(korisnik, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(1, "Došlo je do greške: " + e.getLocalizedMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@JsonView(Views.admin.class)
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAll(RoditeljEntity roditeljEntity) {
		try {
			Iterable<RoditeljEntity> korisnici = roditeljRepository.findByStatusLike(1);
			return new ResponseEntity<Iterable<RoditeljEntity>>(korisnici, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(1, "Načinjena je greška" + e.getLocalizedMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@JsonView(Views.admin.class)
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> getById(@PathVariable Integer id) {
		try {
			RoditeljEntity korisnik = roditeljRepository.findByIdAndStatusLike(id, 1);
			return new ResponseEntity<RoditeljEntity>(korisnik, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(1, "Načinjena je greška: " + e.getLocalizedMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@JsonView(Views.admin.class)
	@RequestMapping(method = RequestMethod.GET, value = "/saNalogom")
	public ResponseEntity<?> getAllWithUserAccount(RoditeljEntity roditeljEntity) {
		try {
			Iterable<NadjiRoditeljaDto> korisnici = roditeljRepository.findByStatusWithKorisnikNalog(1,
					EUloga.roditelj);
			return new ResponseEntity<Iterable<NadjiRoditeljaDto>>(korisnici, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(1, "Pojavila se greška" + e.getLocalizedMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@JsonView(Views.admin.class)
	@RequestMapping(method = RequestMethod.GET, value = "/izbrisani")
	public ResponseEntity<?> getAllDeleted(RoditeljEntity roditeljEntity) {
		try {
			Iterable<RoditeljEntity> korisnici = roditeljRepository.findByStatusLike(0);
			return new ResponseEntity<Iterable<RoditeljEntity>>(korisnici, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(1, "Došlo je do greške " + e.getLocalizedMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@JsonView(Views.admin.class)
	@RequestMapping(method = RequestMethod.GET, value = "/arhivirani")
	public ResponseEntity<?> getAllArchived(RoditeljEntity roditeljEntity) {
		try {
			Iterable<RoditeljEntity> korisnici = roditeljRepository.findByStatusLike(-1);
			return new ResponseEntity<Iterable<RoditeljEntity>>(korisnici, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(1, "Došlo je do greške  " + e.getLocalizedMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@JsonView(Views.roditelj.class)
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<?> promeniRoditelja(@PathVariable Integer id, @Valid @RequestBody RoditeljDto azurirajRoditelja) {
		if (azurirajRoditelja == null) {
			return new ResponseEntity<RESTError>(new RESTError(5, "Podaci za roditelja su prazni."),
					HttpStatus.BAD_REQUEST);
		}
		RoditeljEntity korisnik = new RoditeljEntity();
		try {
			korisnik = roditeljRepository.findByIdAndStatusLike(id, 1); // traži mi aktivnog roditelja, prema idu koji
																		// sam uneo i njega zelim da menjam
			if (korisnik == null) {
				return new ResponseEntity<RESTError>(new RESTError(4, "Korisnik nije pronadjen."),
						HttpStatus.NOT_FOUND);
			}
			KorisnikNalogEntity nalog = korisnikNalogRepository.findByKorisnikAndUlogaLikeAndStatusLike(korisnik,
					EUloga.roditelj, 1);
			if (azurirajRoditelja.getJmbg() != null && azurirajRoditelja.getJmbg().equals(korisnik.getJmbg())) {
				return new ResponseEntity<RESTError>(new RESTError(2, "Isti je jmbg."), HttpStatus.NOT_ACCEPTABLE);
			}
			if (azurirajRoditelja.getEmail() != null && azurirajRoditelja.getEmail().equals(korisnik.getEmail())) {
				return new ResponseEntity<RESTError>(new RESTError(2, "Isti je mail."), HttpStatus.NOT_ACCEPTABLE);
			}
			if (azurirajRoditelja.getUsername() != null
					&& azurirajRoditelja.getUsername().equals(nalog.getUsername())) {
				return new ResponseEntity<RESTError>(new RESTError(2, "Isti je username."), HttpStatus.NOT_ACCEPTABLE);
			}
			if (azurirajRoditelja.getJmbg() != null
					&& korisnikRepository.getByJmbg(azurirajRoditelja.getJmbg()) != null) {
				return new ResponseEntity<RESTError>(new RESTError(2, "Jmbg vec postoji."), HttpStatus.NOT_ACCEPTABLE);
			}
			if (azurirajRoditelja.getEmail() != null
					&& roditeljRepository.getByEmail(azurirajRoditelja.getEmail()) != null) {

				return new ResponseEntity<RESTError>(new RESTError(2, "Email already exist."),
						HttpStatus.NOT_ACCEPTABLE);
			}
			if (azurirajRoditelja.getUloga() != null && !azurirajRoditelja.getUloga().equals("roditelj")) {
				return new ResponseEntity<RESTError>(new RESTError(2, "Uloga mora biti roditelj."),
						HttpStatus.NOT_ACCEPTABLE);
			}
			if (azurirajRoditelja.getUsername() != null
					&& korisnikNalogRepository.getByUsername(azurirajRoditelja.getUsername()) != null) {
				return new ResponseEntity<RESTError>(new RESTError(2, "Username already exists."),
						HttpStatus.NOT_ACCEPTABLE);
			}
			if (azurirajRoditelja.getIme() != null || azurirajRoditelja.getPrezime() != null
					|| azurirajRoditelja.getEmail() != null || azurirajRoditelja.getPol() != null
					|| azurirajRoditelja.getJmbg() != null) {
				roditeljService.promeniRoditelja(korisnik, azurirajRoditelja);
			}
			if (nalog != null) {
				if (azurirajRoditelja.getUsername() != null && !azurirajRoditelja.getUsername().equals("")
						&& !azurirajRoditelja.getUsername().equals(" ")
						&& korisnikNalogRepository.getByUsername(azurirajRoditelja.getUsername()) == null) {
					korisnikNalogService.promeniNalogUsername(nalog, azurirajRoditelja.getUsername());
				}
				if (azurirajRoditelja.getPassword() != null && !azurirajRoditelja.getPassword().equals("")
						&& !azurirajRoditelja.getPassword().equals(" ")) {
					korisnikNalogService.promeniNalogPassword(nalog, azurirajRoditelja.getPassword());
				}
				return new ResponseEntity<>(nalog, HttpStatus.OK);
			}
			return new ResponseEntity<>(korisnik, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(1, "Doslo je do greske: " + e.getLocalizedMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@JsonView(Views.admin.class)
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}/dete/{d_id}")
	public ResponseEntity<?> dodajDete(@PathVariable Integer id, @PathVariable Integer d_id) {
		RoditeljEntity korisnik = new RoditeljEntity();
		try {
			korisnik = roditeljRepository.findByIdAndStatusLike(id, 1);
			if (korisnik == null) {
				return new ResponseEntity<RESTError>(new RESTError(4, "Roditelj nije pronadjen."),
						HttpStatus.NOT_FOUND);
			}
			UcenikEntity ucenik = ucenikRepository.findByIdAndStatusLike(d_id, 1);
			if (ucenik == null) {
				return new ResponseEntity<RESTError>(new RESTError(4, "Ucenik nije pronadjen."), HttpStatus.NOT_FOUND);
			}
			if (korisnik.getUcenici().contains(ucenik)) {
				return new ResponseEntity<RESTError>(new RESTError(2, "Ucenik vec postoji."),
						HttpStatus.NOT_ACCEPTABLE);
			}
			roditeljService.dodajUcenikaRoditelju(korisnik, ucenik);
			return new ResponseEntity<KorisnikEntity>(korisnik, HttpStatus.OK);
		} catch (NumberFormatException e) {
			return new ResponseEntity<RESTError>(
					new RESTError(2, "Format nije zadovoljavajuci: " + e.getLocalizedMessage()),
					HttpStatus.NOT_ACCEPTABLE);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(1, "Doslo je do greske: " + e.getLocalizedMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

	@JsonView(Views.admin.class)
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}/ukloni/dete/{d_id}")
	public ResponseEntity<?> ukloniDete(@PathVariable Integer id, @PathVariable Integer d_id) {
		RoditeljEntity korisnik = new RoditeljEntity();
		try {
			korisnik = roditeljRepository.findByIdAndStatusLike(id, 1);
			if (korisnik == null) {
		        return new ResponseEntity<RESTError>(new RESTError(4, "Roditelj nije pronadjen."), HttpStatus.NOT_FOUND);
		      }
			UcenikEntity ucenik = ucenikRepository.findByIdAndStatusLike(d_id, 1);
			if (ucenik == null) {
		        return new ResponseEntity<RESTError>(new RESTError(4, "Ucenik nije pronadjen."), HttpStatus.NOT_FOUND);
		      }
			if (!korisnik.getUcenici().contains(ucenik)) {
				return new ResponseEntity<RESTError>(new RESTError(4, "Ucenik nije dete od roditelja sa unesenim ID-jem."), HttpStatus.NOT_FOUND);
			}
			roditeljService.ukloniUcenikaRoditelju(korisnik, ucenik);		
			return new ResponseEntity<KorisnikEntity>(korisnik, HttpStatus.OK);
		} catch (NumberFormatException e) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Greška u formatu pri genersianju: "+ e.getLocalizedMessage()), HttpStatus.NOT_ACCEPTABLE);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(1, "Nije doslo do greske: "+ e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
