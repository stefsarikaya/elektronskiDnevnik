package com.iktpreobuka.eeeDnevnik.services;

import com.iktpreobuka.eeeDnevnik.entities.KorisnikEntity;
import com.iktpreobuka.eeeDnevnik.entities.KorisnikNalogEntity;
import com.iktpreobuka.eeeDnevnik.entities.dto.AdminDto;
import com.iktpreobuka.eeeDnevnik.entities.dto.RoditeljDto;
import com.iktpreobuka.eeeDnevnik.enumeration.EUloga;

public interface KorisnikNalogService {
	
	public KorisnikNalogEntity dodajNoviNalog(KorisnikEntity korisnik, String username, EUloga uloga, String password) throws Exception;
	public void promeniNalog(KorisnikNalogEntity nalog, AdminDto promeniAdmin) throws Exception;
	public void promeniNalog(KorisnikNalogEntity nalog, RoditeljDto promeniRoditelja) throws Exception;
	public void promeniNalogUsername(KorisnikNalogEntity nalog, String username) throws Exception;
	public void promeniNalogUloga(KorisnikNalogEntity nalog, EUloga uloga) throws Exception;
	public void promeniNalogIUloga(KorisnikNalogEntity nalog, KorisnikEntity korisnik, EUloga role) throws Exception;
	public void promeniNalogKorisnik(KorisnikNalogEntity nalog, KorisnikEntity korisnik) throws Exception;
	public void promeniNalogPassword(KorisnikNalogEntity nalog, String password) throws Exception;
	public void promeniNalog(KorisnikNalogEntity nalog, String username, String password) throws Exception;
	public void izbrisiNalog(KorisnikNalogEntity nalog) throws Exception;
	public void povratiNalog(KorisnikNalogEntity nalog) throws Exception;
	public void arhivirajNalog(KorisnikNalogEntity nalog) throws Exception;

}
