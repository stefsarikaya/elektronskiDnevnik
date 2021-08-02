package com.iktpreobuka.eeeDnevnik.controllers;

import java.util.ArrayList;
import java.util.List;

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
import com.iktpreobuka.eeeDnevnik.entities.PredmetEntity;
import com.iktpreobuka.eeeDnevnik.entities.RazredPredmetEntity;
import com.iktpreobuka.eeeDnevnik.entities.dto.PredmetDto;
import com.iktpreobuka.eeeDnevnik.repositories.KorisnikNalogRepository;
import com.iktpreobuka.eeeDnevnik.repositories.NastavnikRepository;
import com.iktpreobuka.eeeDnevnik.repositories.OdeljenjeRepository;
import com.iktpreobuka.eeeDnevnik.repositories.PredmetRepository;
import com.iktpreobuka.eeeDnevnik.repositories.RazredRepository;
import com.iktpreobuka.eeeDnevnik.security.Views;
import com.iktpreobuka.eeeDnevnik.services.PredmetService;

@Controller
@RestController
@RequestMapping(value= "/project/predmeti")
public class PredmetController {
	
	@Autowired
	private PredmetRepository predmetRepository;
	
	@Autowired
	private RazredRepository razredRepository;
	
	@Autowired
	private NastavnikRepository nastavnikRepository;
	
	@Autowired
	private OdeljenjeRepository odeljenjeRepository;
		
	@Autowired
	private KorisnikNalogRepository korisnikNalogRepository;

	@Autowired
	private PredmetService predmetService;
	

	@JsonView(Views.admin.class)
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> dodajNoviPredmet(@Valid @RequestBody PredmetDto noviPredmet) {
		if (noviPredmet == null) {
	        return new ResponseEntity<RESTError>(new RESTError(5, "Novi predmet je prazan."), HttpStatus.BAD_REQUEST);
	      }
		if (noviPredmet.getNedeljniBrojCasova() == null || noviPredmet.getNedeljniBrojCasova()<1 ) {
	        return new ResponseEntity<RESTError>(new RESTError(5, "nedeljniBrojCasova nije ispravno unet."), HttpStatus.BAD_REQUEST);
		}
		if (noviPredmet.getImePredmet() == null || noviPredmet.getImePredmet().equals("") || noviPredmet.getImePredmet().equals(" ")) {
	        return new ResponseEntity<RESTError>(new RESTError(5, "Ime predmeta je prazno"), HttpStatus.BAD_REQUEST);			
		}
		PredmetEntity predmet = new PredmetEntity();
		try {
			if (noviPredmet.getImePredmet() != null && !noviPredmet.getImePredmet().equals("") && !noviPredmet.getImePredmet().equals(" ") && noviPredmet.getNedeljniBrojCasova() != null) {
				if (predmetRepository.findByImePredmet(noviPredmet.getImePredmet()) != null) {
			        return new ResponseEntity<RESTError>(new RESTError(5, "To ime za predmet vec postoji."), HttpStatus.BAD_REQUEST);
				}
				predmet = predmetService.dodajNoviPredmet(noviPredmet);
			}
			return new ResponseEntity<>(predmet, HttpStatus.OK);
		} catch (NumberFormatException e) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Greška pri generisanju: "+ e.getLocalizedMessage()), HttpStatus.NOT_ACCEPTABLE);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(1, "Došlo je do greške: "+ e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	


	@JsonView(Views.admin.class)
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<?> promeni(@PathVariable String id, @Valid @RequestBody PredmetDto azurirajPredmet) {
		if (azurirajPredmet == null) {
	        return new ResponseEntity<RESTError>(new RESTError(5, "Predmet(atributi) je prazan"), HttpStatus.BAD_REQUEST);
	      }
		if (azurirajPredmet.getNedeljniBrojCasova() != null && azurirajPredmet.getNedeljniBrojCasova()<1 ) {
	        return new ResponseEntity<RESTError>(new RESTError(5, "Broj časova u nedelji mora biti makar jedan"), HttpStatus.BAD_REQUEST);
		}
		try {
			PredmetEntity predmet = predmetRepository.findById(Integer.parseInt(id)).orElse(null);
			if (predmet==null || predmet.getStatus()!=1) {
				return new ResponseEntity<RESTError>(new RESTError(4, "Predmet nije pronadjen."), HttpStatus.NOT_FOUND);
			}
			if (azurirajPredmet.getImePredmet() != null && azurirajPredmet.getImePredmet().equals(predmet.getImePredmet()) ) {

			    return new ResponseEntity<RESTError>(new RESTError(5, "Opet ste uneli isto ime za predmet."), HttpStatus.BAD_REQUEST);
			}
			if (predmetRepository.findByImePredmet(azurirajPredmet.getImePredmet()) != null) {
			    return new ResponseEntity<RESTError>(new RESTError(5, "Ime za predmet već postoji"), HttpStatus.BAD_REQUEST);
			}
			if (azurirajPredmet.getImePredmet() != null || azurirajPredmet.getNedeljniBrojCasova() != null) {
				predmetService.promeniPredmet(predmet, azurirajPredmet);
			}
			return new ResponseEntity<>(predmet, HttpStatus.OK);
		} catch (NumberFormatException e) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Generisala se greška: "+ e.getLocalizedMessage()), HttpStatus.NOT_ACCEPTABLE);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(1, "Napravljena je greška: "+ e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	

	@JsonView(Views.admin.class)
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAll(PredmetEntity predmetEntity) {
		try {
			Iterable<PredmetEntity> predmeti= predmetRepository.findByStatusLike(1);
			for (PredmetEntity p : predmeti) {
				List<RazredPredmetEntity> razredii = new ArrayList<RazredPredmetEntity>();
				for (RazredPredmetEntity rpe : p.getRazredi()) {
					if (rpe.getStatus()==1) {
						razredii.add(rpe);
					}
				}
				p.setRazredi(razredii);
			}
			return new ResponseEntity<Iterable<PredmetEntity>>(predmeti, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(1, "Došlo je do greške: "+ e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@JsonView(Views.admin.class)
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> getById(@PathVariable Integer id) {;
		try {
			PredmetEntity predmeti= predmetRepository.findByIdAndStatusLike(id, 1);
			return new ResponseEntity<PredmetEntity>(predmeti, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(1, "Načinejna je greška: "+ e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}	
	
	
	
	// PRORADILOO HVALA ISUSUUU
	
	@JsonView(Views.admin.class)
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}/dodajRazred")
	public ResponseEntity<?> dodajRazredPredmetu(@PathVariable Integer id, @Valid @RequestBody PredmetDto azurirajPredmet) {
		if (azurirajPredmet == null) {
	        return new ResponseEntity<RESTError>(new RESTError(5, "Neki od podataka su prazni."), HttpStatus.BAD_REQUEST);
	      }
		if (azurirajPredmet.getImePredmet() == null || azurirajPredmet.getNedeljniBrojCasova() == null) {
	        return new ResponseEntity<RESTError>(new RESTError(2, "Ažuriranje nema prihvatljive atribute"), HttpStatus.NOT_ACCEPTABLE);
		}
		for (String e : azurirajPredmet.getRazredi()) {
			if (e ==null || e.equals("") || e.equals(" ")) {
		        return new ResponseEntity<RESTError>(new RESTError(5, "Novi razred je prazan."), HttpStatus.BAD_REQUEST);
			}
		}
		if (azurirajPredmet.getPlanNastave() ==null || azurirajPredmet.getPlanNastave().equals("") || azurirajPredmet.getPlanNastave().equals(" ")) {
		       return new ResponseEntity<RESTError>(new RESTError(5, "Plan nastave je prazan."), HttpStatus.BAD_REQUEST);
		}
		List<RazredPredmetEntity> rp = new ArrayList<RazredPredmetEntity>();
		try {
			for (String ee : azurirajPredmet.getRazredi()) {
				if (razredRepository.findByIdAndStatusLike(Integer.parseInt(ee), 1) == null ) {
			        return new ResponseEntity<RESTError>(new RESTError(4, "Razred nije pronadjen."), HttpStatus.NOT_FOUND);
				}
			}
			PredmetEntity predmet = predmetRepository.findByIdAndStatusLike(id, 1);
			if (predmet == null) {
		        return new ResponseEntity<RESTError>(new RESTError(4, "Predmet nije pronadjen."), HttpStatus.NOT_FOUND);
		      }
	// DODAO RAZRED PREDMETU POSLE PROVERAA		
			
			if (azurirajPredmet.getRazredi() != null && azurirajPredmet.getPlanNastave() != null && azurirajPredmet.getRazredi() != null) {
				rp = predmetService.dodajRazredPredmetu(azurirajPredmet.getRazredi(), predmet, azurirajPredmet.getPlanNastave());
			} else {
		        return new ResponseEntity<RESTError>(new RESTError(5, "Neki od podataka su prazni."), HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<>(rp, HttpStatus.OK);
		} catch (NumberFormatException e) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Greška pri generisanju: "+ e.getLocalizedMessage()), HttpStatus.NOT_ACCEPTABLE);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(1, "Načinjena je greška: "+ e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
