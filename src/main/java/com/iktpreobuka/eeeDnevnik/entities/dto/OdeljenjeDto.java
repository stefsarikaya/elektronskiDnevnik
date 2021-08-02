package com.iktpreobuka.eeeDnevnik.entities.dto;

import java.util.List;

import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.eeeDnevnik.security.Views;


public class OdeljenjeDto {

	@JsonView(Views.ucenik.class)
	private String odeljenjeOznaka;
	
	@JsonView(Views.ucenik.class)
	private String godinaUpisa;
	
	@JsonView(Views.ucenik.class)
	private String skolskaGodina;
	
	@JsonView(Views.ucenik.class)
	private List<String> studenti;
	
	@JsonView(Views.ucenik.class)
	private String razredniStaresina;
	
	@JsonView(Views.ucenik.class)
	private List<String> nastavnici_predmeti;
	
	@JsonView(Views.ucenik.class)
	private String odeljenje_razred;

	/**
	 * 
	 */
	public OdeljenjeDto() {
		super();
		// TODO Auto-generated constructor stub
	}


	/**
	 * @param odeljenjeOznaka
	 * @param godinaUpisa
	 * @param skolskaGodina
	 * @param studenti
	 * @param razredniStaresina
	 * @param nastavnici_predmeti
	 * @param odeljenje_razred
	 */
	public OdeljenjeDto(String odeljenjeOznaka, String godinaUpisa, String skolskaGodina, List<String> studenti,
			String razredniStaresina, List<String> nastavnici_predmeti, String odeljenje_razred) {
		super();
		this.odeljenjeOznaka = odeljenjeOznaka;
		this.godinaUpisa = godinaUpisa;
		this.skolskaGodina = skolskaGodina;
		this.studenti = studenti;
		this.razredniStaresina = razredniStaresina;
		this.nastavnici_predmeti = nastavnici_predmeti;
		this.odeljenje_razred = odeljenje_razred;
	}


	public String getOdeljenjeOznaka() {
		return odeljenjeOznaka;
	}

	public void setOdeljenjeOznaka(String odeljenjeOznaka) {
		this.odeljenjeOznaka = odeljenjeOznaka;
	}

	public String getGodinaUpisa() {
		return godinaUpisa;
	}

	public void setGodinaUpisa(String godinaUpisa) {
		this.godinaUpisa = godinaUpisa;
	}

	public String getSkolskaGodina() {
		return skolskaGodina;
	}

	public void setSkolskaGodina(String skolskaGodina) {
		this.skolskaGodina = skolskaGodina;
	}

	public List<String> getStudenti() {
		return studenti;
	}

	public void setStudenti(List<String> studenti) {
		this.studenti = studenti;
	}

	public String getRazredniStaresina() {
		return razredniStaresina;
	}

	public void setRazredniStaresina(String razredniStaresina) {
		this.razredniStaresina = razredniStaresina;
	}

	public List<String> getNastavnici_predmeti() {
		return nastavnici_predmeti;
	}

	public void setNastavnici_predmeti(List<String> nastavnici_predmeti) {
		this.nastavnici_predmeti = nastavnici_predmeti;
	}

	public String getOdeljenje_razred() {
		return odeljenje_razred;
	}

	public void setOdeljenje_razred(String odeljenje_razred) {
		this.odeljenje_razred = odeljenje_razred;
	}
	
}
