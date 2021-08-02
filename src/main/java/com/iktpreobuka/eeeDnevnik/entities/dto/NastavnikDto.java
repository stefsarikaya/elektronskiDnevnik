package com.iktpreobuka.eeeDnevnik.entities.dto;

import java.util.List;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.eeeDnevnik.security.Views;

public class NastavnikDto {
	
	@JsonView(Views.nastavnik.class)
	@Pattern(regexp = "^[A-Za-z]{2,}$", message = "Ime nije validno, mora sadržati samo slova i to minimum dva slova")
	private String ime;
	
	@JsonView(Views.nastavnik.class)
	@Pattern(regexp = "^[A-Za-z]{2,}$", message = "Prezme nije validno, mora sadržati samo slova i to minimum dva slova")
	private String prezime;
	
	@JsonView(Views.admin.class)
	@Pattern(regexp = "^[0-9]{13,13}$", message = "JMBG nije validan, može sadržati samo broejve i mora biti 13 brojeva dužine.")
	private String jmbg;
	
	@JsonView(Views.admin.class)
	@Pattern(regexp = "^(muski|zenski)$", message = "Pol nije ispravan")
	private String pol;
	
	@JsonView(Views.nastavnik.class)
	private String diploma;
	
	@JsonView(Views.nastavnik.class)
	@Pattern(regexp = "^([1-2][0-9]{3})[./-]([0][1-9]|[1][0-2])[./-]([0][1-9]|[1|2][0-9]|[3][0|1])$", message="Datum zapsolenja nije validan, treba da bude u formatu yyyy-MM-dd format.")
	private String datumZaposlenja;
	
	@JsonView(Views.nastavnik.class)
	private List<String> predmeti;
	
	@JsonView(Views.nastavnik.class)
	@Pattern(regexp = "^[0-9]+$", message="Odeljenje Id nije ispravno uneto, moze da sadrzi samo brojeve it o najmanje jedan broj.")
	private String glavnoOdeljenje;
	
	@JsonView(Views.nastavnik.class)
	private String predajeOdeljenje;
	
	
	//ti imaš u kojem odeljenu predaje, i kojim razredima predaje. ali pri unosu u Postmanu ne moraju
	// svi atributi biti popunjeni jer nemas @NotNull, jel tako??
	@JsonView(Views.nastavnik.class)
	private List<String> predajeOdeljenjima;
	
	@JsonView(Views.nastavnik.class)
	private String predajePredmet;
	
	@JsonView(Views.nastavnik.class)
	@Pattern(regexp = "^([2-9][0-9])[0-9]{2}\\-([2-9][0-9])[0-9]{2}$", message="Skolska godina niej lepo uenta, mroa biti u formatu YYYY-YYYY.")
	private String skolskaGodina;

	
	@JsonView(Views.admin.class)
	@Size(min = 5, max = 20, message = "Username mora biti između {min} i {max} karaktera dužine.")
	private String username;
	
	@JsonView(Views.admin.class)
	@Pattern(regexp = "^(admin|nastavnik|roditelj|ucenik)$", message = "Uloga nije ispravno uneta.")
	private String uloga;
	
	@Size(min = 5, message = "Password mora biti {min} karaktera dužine ili više.")
	@Pattern(regexp = "^[A-Za-z0-9]*$", message = "Password nije ispravan, mora sadržati slova i brojeve.")
	private String password;

	/**
	 * 
	 */
	public NastavnikDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param ime
	 * @param prezime
	 * @param jmbg
	 * @param pol
	 * @param diploma
	 * @param datumZaposlenja
	 * @param predmeti
	 * @param glavnoOdeljenje
	 * @param predajeOdeljenje
	 * @param predajeOdeljenjima
	 * @param predajePredmet
	 * @param skolskaGodina
	 * @param username
	 * @param uloga
	 * @param password
	 */
	public NastavnikDto(
			@Pattern(regexp = "^[A-Za-z]{2,}$", message = "Ime nije validno, mora sadržati samo slova i to minimum dva slova") String ime,
			@Pattern(regexp = "^[A-Za-z]{2,}$", message = "Prezme nije validno, mora sadržati samo slova i to minimum dva slova") String prezime,
			@Pattern(regexp = "^[0-9]{13,13}$", message = "JMBG nije validan, može sadržati samo broejve i mora biti 13 brojeva dužine.") String jmbg,
			@Pattern(regexp = "^(muski|zenski)$", message = "Pol nije ispravan") String pol, String diploma,
			@Pattern(regexp = "^([1-2][0-9]{3})[./-]([0][1-9]|[1][0-2])[./-]([0][1-9]|[1|2][0-9]|[3][0|1])$", message = "Datum zapsolenja nije validan, treba da bdue u formatu yyyy-MM-dd format.") String datumZaposlenja,
			List<String> predmeti,
			@Pattern(regexp = "^[0-9]+$", message = "Odeljenje Id nije ispravno uneto, moze da sadrzi samo broejve it o najmanje jedan broj.") String glavnoOdeljenje,
			String predajeOdeljenje, List<String> predajeOdeljenjima, String predajePredmet,
			@Pattern(regexp = "^([2-9][0-9])[0-9]{2}\\-([2-9][0-9])[0-9]{2}$", message = "Skolska godina niej lepo uenta, mroa biti u formatu YYYY-YYYY.") String skolskaGodina,
			@Size(min = 5, max = 20, message = "Username mora biti između {min} i {max} karaktera dužine.") String username,
			@Pattern(regexp = "^(admin|nastavnik|roditelj|ucenik)$", message = "Uloga nije ispravno uneta.") String uloga,
			@Size(min = 5, message = "Password mora biti {min} karaktera dužine ili više.") @Pattern(regexp = "^[A-Za-z0-9]*$", message = "Password nije ispravan, mora sadržati slova i brojeve.") String password) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.jmbg = jmbg;
		this.pol = pol;
		this.diploma = diploma;
		this.datumZaposlenja = datumZaposlenja;
		this.predmeti = predmeti;
		this.glavnoOdeljenje = glavnoOdeljenje;
		this.predajeOdeljenje = predajeOdeljenje;
		this.predajeOdeljenjima = predajeOdeljenjima;
		this.predajePredmet = predajePredmet;
		this.skolskaGodina = skolskaGodina;
		this.username = username;
		this.uloga = uloga;
		this.password = password;
	}
	
	

	/**
	 * @param ime
	 * @param prezime
	 * @param jmbg
	 * @param pol
	 * @param diploma
	 * @param datumZaposlenja
	 * @param username
	 * @param uloga
	 * @param password
	 */
	public NastavnikDto(
			@Pattern(regexp = "^[A-Za-z]{2,}$", message = "Ime nije validno, mora sadržati samo slova i to minimum dva slova") String ime,
			@Pattern(regexp = "^[A-Za-z]{2,}$", message = "Prezme nije validno, mora sadržati samo slova i to minimum dva slova") String prezime,
			@Pattern(regexp = "^[0-9]{13,13}$", message = "JMBG nije validan, može sadržati samo broejve i mora biti 13 brojeva dužine.") String jmbg,
			@Pattern(regexp = "^(muski|zenski)$", message = "Pol nije ispravan") String pol, String diploma,
			@Pattern(regexp = "^([1-2][0-9]{3})[./-]([0][1-9]|[1][0-2])[./-]([0][1-9]|[1|2][0-9]|[3][0|1])$", message = "Datum zapsolenja nije validan, treba da bude u formatu yyyy-MM-dd format.") String datumZaposlenja,
			@Size(min = 5, max = 20, message = "Username mora biti između {min} i {max} karaktera dužine.") String username,
			@Pattern(regexp = "^(admin|nastavnik|roditelj|ucenik)$", message = "Uloga nije ispravno uneta.") String uloga,
			@Size(min = 5, message = "Password mora biti {min} karaktera dužine ili više.") @Pattern(regexp = "^[A-Za-z0-9]*$", message = "Password nije ispravan, mora sadržati slova i brojeve.") String password) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.jmbg = jmbg;
		this.pol = pol;
		this.diploma = diploma;
		this.datumZaposlenja = datumZaposlenja;
		this.username = username;
		this.uloga = uloga;
		this.password = password;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getJmbg() {
		return jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}

	public String getPol() {
		return pol;
	}

	public void setPol(String pol) {
		this.pol = pol;
	}

	public String getDiploma() {
		return diploma;
	}

	public void setDiploma(String diploma) {
		this.diploma = diploma;
	}

	public String getDatumZaposlenja() {
		return datumZaposlenja;
	}

	public void setDatumZaposlenja(String datumZaposlenja) {
		this.datumZaposlenja = datumZaposlenja;
	}

	public List<String> getPredmeti() {
		return predmeti;
	}

	public void setPredmeti(List<String> predmeti) {
		this.predmeti = predmeti;
	}

	public String getGlavnoOdeljenje() {
		return glavnoOdeljenje;
	}

	public void setGlavnoOdeljenje(String glavnoOdeljenje) {
		this.glavnoOdeljenje = glavnoOdeljenje;
	}

	public String getPredajeOdeljenje() {
		return predajeOdeljenje;
	}

	public void setPredajeOdeljenje(String predajeOdeljenje) {
		this.predajeOdeljenje = predajeOdeljenje;
	}

	public List<String> getPredajeOdeljenjima() {
		return predajeOdeljenjima;
	}

	public void setPredajeOdeljenjima(List<String> predajeOdeljenjima) {
		this.predajeOdeljenjima = predajeOdeljenjima;
	}

	public String getPredajePredmet() {
		return predajePredmet;
	}

	public void setPredajePredmet(String predajePredmet) {
		this.predajePredmet = predajePredmet;
	}

	public String getSkolskaGodina() {
		return skolskaGodina;
	}

	public void setSkolskaGodina(String skolskaGodina) {
		this.skolskaGodina = skolskaGodina;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUloga() {
		return uloga;
	}

	public void setUloga(String uloga) {
		this.uloga = uloga;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
