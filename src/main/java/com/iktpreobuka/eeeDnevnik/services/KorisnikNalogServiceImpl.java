package com.iktpreobuka.eeeDnevnik.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktpreobuka.eeeDnevnik.entities.KorisnikEntity;
import com.iktpreobuka.eeeDnevnik.entities.KorisnikNalogEntity;
import com.iktpreobuka.eeeDnevnik.entities.dto.AdminDto;
import com.iktpreobuka.eeeDnevnik.entities.dto.RoditeljDto;
import com.iktpreobuka.eeeDnevnik.enumeration.EUloga;
import com.iktpreobuka.eeeDnevnik.repositories.KorisnikNalogRepository;

@Service
public class KorisnikNalogServiceImpl implements KorisnikNalogService {
	
	@Autowired
	private KorisnikNalogRepository korisnikNalogRepository;
	
	@Override
	public KorisnikNalogEntity dodajNoviNalog(KorisnikEntity korisnik, String username, EUloga uloga, String password) throws Exception {
		if (username != null && korisnikNalogRepository.getByUsername(username) != null) {
			throw new Exception("Username već postoji.");
		}
		KorisnikNalogEntity nalog = new KorisnikNalogEntity();
		try {
			nalog.setUloga(uloga);
			nalog.setUsername(username);
			nalog.setPassword(password);
			nalog.setAktivan();
			nalog.setKorisnik(korisnik);
			korisnikNalogRepository.save(nalog);
		} catch (Exception e) {
			throw new Exception("Dodavanje novog korisničkog naloga nije uspelo.");
		}
		return nalog;
	}
	
	
	@Override
	public void promeniNalog(KorisnikNalogEntity nalog, AdminDto azurirajAdmin) throws Exception {
		if (azurirajAdmin.getUsername() != null
				&& korisnikNalogRepository.getByUsername(azurirajAdmin.getUsername()) != null) {
			throw new Exception("Username već postoji.");
		}
		if (azurirajAdmin.getUloga() != null && !azurirajAdmin.getUloga().equals("admin")) {
			throw new Exception("Uloga mora biti admin.");
		}
		try {
			if (azurirajAdmin.getUsername() != null && !azurirajAdmin.getUsername().equals(nalog.getUsername())
					&& !azurirajAdmin.getUsername().equals(" ") && !azurirajAdmin.getUsername().equals("")) {
				nalog.setUsername(azurirajAdmin.getUsername());
		
			}
			if (azurirajAdmin.getPassword() != null
					&& !(azurirajAdmin.getPassword()).equals(nalog.getPassword())
					&& !azurirajAdmin.getPassword().equals(" ") && !azurirajAdmin.getPassword().equals("")) {
				nalog.setPassword(azurirajAdmin.getPassword());
				
			}
				korisnikNalogRepository.save(nalog);
		} catch (Exception e) {
			throw new Exception("Promena naloga za adimna nije uspela.");
		}

	}
	
	@Override
	public void promeniNalog(KorisnikNalogEntity nalog, RoditeljDto azurirajRoditelj)
			throws Exception {
		if (azurirajRoditelj.getUsername() != null
				&& korisnikNalogRepository.getByUsername(azurirajRoditelj.getUsername()) != null) {
			throw new Exception("Username već postoji, žao nam je.");
		}
		if (azurirajRoditelj.getUloga() != null && !azurirajRoditelj.getUloga().equals("ROLE_PARENT")) {
			throw new Exception("Uloga mora biti roditelj.");
		}
		try {
			Integer i = 0;
			if (azurirajRoditelj.getUsername() != null && !azurirajRoditelj.getUsername().equals(nalog.getUsername())
					&& !azurirajRoditelj.getUsername().equals(" ") && !azurirajRoditelj.getUsername().equals("")) {
				nalog.setUsername(azurirajRoditelj.getUsername());
				i++;
			}
			if (azurirajRoditelj.getPassword() != null
					&& !(azurirajRoditelj.getPassword()).equals(nalog.getPassword())
					&& !azurirajRoditelj.getPassword().equals(" ") && !azurirajRoditelj.getPassword().equals("")) {
				nalog.setPassword(azurirajRoditelj.getPassword());
				i++;
			}
			if (i > 0) {
				korisnikNalogRepository.save(nalog);
			}
		} catch (Exception e) {
			throw new Exception("Promena korisničkog naloga za roditelja nije uspela");
		}
	}
	
	@Override
	public void promeniNalogUsername(KorisnikNalogEntity nalog, String username)
			throws Exception {
		if (username != null && korisnikNalogRepository.getByUsername(username) != null) {
			throw new Exception("Username already exists.");
		}
		try {
			if (username != null && !username.equals(nalog.getUsername()) && !username.equals(" ")
					&& !username.equals("")) {
				nalog.setUsername(username);
				korisnikNalogRepository.save(nalog);
			}
		} catch (Exception e) {
			throw new Exception("Promena username za korisnički nalog nije uspela");
		}
	}
	
	@Override
	public void promeniNalogUloga(KorisnikNalogEntity nalog, EUloga uloga)
			throws Exception {
		try {
			if (uloga != null && (uloga == EUloga.admin || uloga == EUloga.roditelj
					|| uloga == EUloga.nastavnik || uloga == EUloga.ucenik)) {
				nalog.setUloga(uloga);
				korisnikNalogRepository.save(nalog);
			}
		} catch (Exception e) {
			throw new Exception("Promena uloge za korisnički nalog nije uspela");
		}
	}
	
	@Override
	public void promeniNalogIUloga(KorisnikNalogEntity nalog, KorisnikEntity korisnik,
			EUloga uloga) throws Exception {
		try {
			if (korisnik != null && uloga != null) {
				nalog.setKorisnik(korisnik);
				nalog.setUloga(uloga);;
				korisnikNalogRepository.save(nalog);
			}
		} catch (Exception e) {
			throw new Exception("modifyAccountUserAndAccessRole failed on saving.");
		}
	}
	
	@Override
	public void promeniNalogKorisnik(KorisnikNalogEntity nalog, KorisnikEntity korisnik) throws Exception {
		try {
			if (korisnik != null) {
				nalog.setKorisnik(korisnik);
				korisnikNalogRepository.save(nalog);
			}
		} catch (Exception e) {
			throw new Exception("Promena korisničkog naloga nije uspela");
		}
	}
	
	@Override
	public void promeniNalogPassword(KorisnikNalogEntity nalog, String password)
			throws Exception {
		try {
			if (password != null && (password).equals(nalog.getPassword())
					&& !password.equals(" ") && !password.equals("")) {
				nalog.setPassword(password);
				korisnikNalogRepository.save(nalog);
			}
		} catch (Exception e) {
			throw new Exception("Promena lozinke za korisnički nalog nije uspela");
		}
	}
	
	@Override
	public void promeniNalog(KorisnikNalogEntity nalog, String username, String password)
			throws Exception {
		if (username != null && korisnikNalogRepository.getByUsername(username) != null) {
			throw new Exception("Username već postoji.");
		}
		if (username == null && password == null)
			throw new Exception("Username i password su prazni.");
		try {
			Integer i = 0;
			if (!username.equals(nalog.getUsername()) && username != null && !username.equals(" ")
					&& !username.equals("")) {
				nalog.setUsername(username);
				i++;
			}
			if (password.equals(nalog.getPassword()) && password != null
					&& !password.equals(" ") && !password.equals("")) {
				nalog.setPassword((password));
				i++;
			}
			if (i > 0) {
				korisnikNalogRepository.save(nalog);
			}
		} catch (Exception e) {
			throw new Exception("ModifyAccountUsername failed on saving.");
		}

	}
	
	@Override
	public void izbrisiNalog(KorisnikNalogEntity nalog) throws Exception {
		try {
			nalog.setNeaktivan();
			korisnikNalogRepository.save(nalog);
		} catch (Exception e) {
			throw new Exception("Brisanje naloga nije uspelo.");
		}
	}
	
	@Override
	public void povratiNalog(KorisnikNalogEntity nalog) throws Exception {
		try {
			nalog.setAktivan();
			korisnikNalogRepository.save(nalog);
		} catch (Exception e) {
			throw new Exception("Povratak naloga nije uspelo");
		}
	}
	
	public void arhivirajNalog(KorisnikNalogEntity nalog) throws Exception {
		try {
			nalog.setAktivan();
			korisnikNalogRepository.save(nalog);
		} catch (Exception e) {
			throw new Exception("ArchiveDeleteAccount failed on saving.");
		}
	}


}
