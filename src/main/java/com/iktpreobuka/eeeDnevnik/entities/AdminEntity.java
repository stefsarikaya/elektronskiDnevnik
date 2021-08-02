package com.iktpreobuka.eeeDnevnik.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.eeeDnevnik.security.Views;

@Entity
@Table(name = "admin")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class AdminEntity extends KorisnikEntity{
	
	@JsonView(Views.admin.class)
	@Column(name = "mobilni_telefon")
	@NotNull(message = "Broj mobilnog telefona mora biti unet")
	private String mobilniTelefon;

	@JsonView(Views.nastavnik.class)
	@Column(name = "e_mail", unique = true, length = 50)
	@NotNull(message = "Email mora biti unet")
	private String email;

	@JsonView(Views.admin.class)
	@Max(1)
    @Min(-1)
    @Column(name = "status", nullable = false)
	private Integer status;
	
	private static final Integer NEAKTIVAN = 0;
	private static final Integer AKTIVAN = 1;
	private static final Integer ARHIVIRAN = -1;
	
	public AdminEntity() {
		super();
		// TODO Auto-generated constructor stub
	}



	/**
	 * @param mobilniTelefon
	 * @param email
	 * @param status
	 */
	public AdminEntity(@NotNull(message = "Broj mobilnog telefona mora biti unet") String mobilniTelefon,
			@NotNull(message = "Email mora biti unet") String email, @Max(1) @Min(-1) Integer status) {
		super();
		this.mobilniTelefon = mobilniTelefon;
		this.email = email;
		this.status = status;
	}



	public String getMobilniTelefon() {
		return mobilniTelefon;
	}

	public void setMobilniTelefon(String mobilniTelefon) {
		this.mobilniTelefon = mobilniTelefon;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public static Integer getNeaktivan() {
		return NEAKTIVAN;
	}

	public static Integer getAktivan() {
		return AKTIVAN;
	}

	public static Integer getArhiviran() {
		return ARHIVIRAN;
	}
	
	public void setNeaktivan() {
		this.status = getNeaktivan();
	}

	public void setAktivan() {
		this.status = getAktivan();
	}

	public void setArhiviran() {
		this.status = getArhiviran();
	}
	
}
