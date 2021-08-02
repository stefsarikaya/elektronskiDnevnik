package com.iktpreobuka.eeeDnevnik.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktpreobuka.eeeDnevnik.entities.AdminEntity;
import com.iktpreobuka.eeeDnevnik.entities.KorisnikEntity;
import com.iktpreobuka.eeeDnevnik.entities.dto.AdminDto;
import com.iktpreobuka.eeeDnevnik.enumeration.EPol;
import com.iktpreobuka.eeeDnevnik.enumeration.EUloga;
import com.iktpreobuka.eeeDnevnik.repositories.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Override
	public KorisnikEntity dodajNovogAdmin(AdminDto noviAdmin) throws Exception {
			if (noviAdmin.getIme() == null || noviAdmin.getPrezime() == null || noviAdmin.getPol() == null || noviAdmin.getJmbg() == null || noviAdmin.getEmail() == null || noviAdmin.getMobilniTelefon() == null) {
				throw new Exception("Neki podaci su prazni.");
			}
			
			AdminEntity korisnik = new AdminEntity();
				try {
					korisnik.setIme(noviAdmin.getIme());
					korisnik.setPrezime(noviAdmin.getPrezime());
					korisnik.setJmbg(noviAdmin.getJmbg());
					korisnik.setPol(EPol.valueOf(noviAdmin.getPol())); 
					korisnik.setMobilniTelefon(noviAdmin.getMobilniTelefon());
					korisnik.setEmail(noviAdmin.getEmail());
					korisnik.setUloga(EUloga.admin);
					korisnik.setAktivan();
					adminRepository.save(korisnik);
					return korisnik;
				} catch (Exception e) {
					throw new Exception("Dodavanje novog admina nije uspelo.");
				}
			} 

	@Override
	public void promeniAdmin(AdminEntity admin, AdminDto azurirajAdmin) throws Exception {
		if (azurirajAdmin.getIme() == null && azurirajAdmin.getPrezime() == null && azurirajAdmin.getEmail() == null && azurirajAdmin.getMobilniTelefon() == null && azurirajAdmin.getPol() == null && azurirajAdmin.getJmbg() == null)
			throw new Exception("All data is null.");
		try {
			if (azurirajAdmin.getIme() != null && !azurirajAdmin.getIme().equals(" ") && !azurirajAdmin.getIme().equals("") && !azurirajAdmin.getIme().equals(admin.getIme())) {
				admin.setIme(azurirajAdmin.getIme());
			}
			if (azurirajAdmin.getPrezime() != null && !azurirajAdmin.getPrezime().equals(admin.getPrezime()) && !azurirajAdmin.getPrezime().equals(" ") && !azurirajAdmin.getPrezime().equals("")) {
				admin.setPrezime(azurirajAdmin.getPrezime());
			}
			if (azurirajAdmin.getJmbg() != null && !azurirajAdmin.getJmbg().equals(admin.getJmbg()) && !azurirajAdmin.getJmbg().equals(" ") && !azurirajAdmin.getJmbg().equals("")) {
				admin.setJmbg(azurirajAdmin.getJmbg());
			}
			if (azurirajAdmin.getPol() != null && EPol.valueOf(azurirajAdmin.getPol()) != admin.getPol() && (EPol.valueOf(azurirajAdmin.getPol()) == EPol.zenski || EPol.valueOf(azurirajAdmin.getPol()) == EPol.muski)) {
				admin.setPol(EPol.valueOf(azurirajAdmin.getPol()));
			}
			if (azurirajAdmin.getMobilniTelefon() != null && !azurirajAdmin.getMobilniTelefon().equals(admin.getMobilniTelefon()) && !azurirajAdmin.getMobilniTelefon().equals(" ") && !azurirajAdmin.getMobilniTelefon().equals("")) {
				admin.setMobilniTelefon(azurirajAdmin.getMobilniTelefon());

			}
			if (azurirajAdmin.getEmail() != null && !azurirajAdmin.getEmail().equals(admin.getEmail()) && !azurirajAdmin.getEmail().equals(" ") && !azurirajAdmin.getEmail().equals("")) {
				admin.setEmail(azurirajAdmin.getEmail());
			}
			adminRepository.save(admin);
		} catch (Exception e) {
			throw new Exception("Promena nije uspela");
		}
	}
	
	@Override
	public void izbrisiAdmin(AdminEntity admin) throws Exception {
		try {
			admin.setNeaktivan();
			adminRepository.save(admin);
		} catch (Exception e) {
			throw new Exception("Brisanje nije uspelo");
		}
	}
	
	@Override
	public void povratiAdmin(AdminEntity admin) throws Exception {
		try {
			admin.setAktivan();
			adminRepository.save(admin);
		} catch (Exception e) {
			throw new Exception("Povratak naloga niej uspelo");
		}		
	}
	
	@Override
	public void arhivirajAdmin(AdminEntity admin) throws Exception {
		try {
			admin.setArhiviran();
			adminRepository.save(admin);
		} catch (Exception e) {
			throw new Exception("Arhviiranje naloga nije uspelo");
		}		
	}

}
