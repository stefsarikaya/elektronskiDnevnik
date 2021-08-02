package com.iktpreobuka.eeeDnevnik.services;

import com.iktpreobuka.eeeDnevnik.entities.AdminEntity;
import com.iktpreobuka.eeeDnevnik.entities.KorisnikEntity;
import com.iktpreobuka.eeeDnevnik.entities.dto.AdminDto;

public interface AdminService {
	
	public KorisnikEntity dodajNovogAdmin(AdminDto newAdmin) throws Exception;

	public void promeniAdmin( AdminEntity admin, AdminDto newAdmin) throws Exception;

	public void izbrisiAdmin(AdminEntity admin) throws Exception;

	public void povratiAdmin( AdminEntity admin) throws Exception;

	public void arhivirajAdmin( AdminEntity admin) throws Exception;

}
