package com.iktpreobuka.eeeDnevnik.entities.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.eeeDnevnik.entities.KorisnikNalogEntity;
import com.iktpreobuka.eeeDnevnik.entities.RoditeljEntity;
import com.iktpreobuka.eeeDnevnik.security.Views;

public class NadjiRoditeljaDto {
	

	@JsonView(Views.ucenik.class)
	private RoditeljEntity korisnik;
	
	@JsonView(Views.ucenik.class)
	private KorisnikNalogEntity nalog;

	/**
	 * 
	 */
	public NadjiRoditeljaDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param korisnik
	 * @param nalog
	 */
	public NadjiRoditeljaDto(RoditeljEntity korisnik, KorisnikNalogEntity nalog) {
		super();
		this.korisnik = korisnik;
		this.nalog = nalog;
	}

	public RoditeljEntity getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(RoditeljEntity korisnik) {
		this.korisnik = korisnik;
	}

	public KorisnikNalogEntity getNalog() {
		return nalog;
	}

	public void setNalog(KorisnikNalogEntity nalog) {
		this.nalog = nalog;
	}
}
