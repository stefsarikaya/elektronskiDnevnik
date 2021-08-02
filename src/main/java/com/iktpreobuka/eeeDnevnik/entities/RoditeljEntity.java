package com.iktpreobuka.eeeDnevnik.entities;

import java.util.ArrayList;
import java.util.List;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.eeeDnevnik.enumeration.EPol;
import com.iktpreobuka.eeeDnevnik.enumeration.EUloga;
import com.iktpreobuka.eeeDnevnik.security.Views;

@Entity
@Table(name = "roditelj")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class RoditeljEntity extends KorisnikEntity{
	
	@JsonView(Views.ucenik.class)
	@JsonIgnore
	@OneToMany(mappedBy = "roditelj", fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	private List<UcenikEntity> ucenici = new ArrayList<>();

	@JsonView(Views.roditelj.class)
	@Column(name = "email", unique = true, length = 50)
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
	public RoditeljEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param ucenici
	 * @param email
	 * @param status
	 */
	public RoditeljEntity(List<UcenikEntity> ucenici, @NotNull(message = "Email mora biti unet") String email,
			@Max(1) @Min(-1) Integer status) {
		super();
		this.ucenici = ucenici;
		this.email = email;
		this.status = status;
	}

	public RoditeljEntity(
			@Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "Email nije validan") @NotNull(message = "Email mora biti unet") String email,
			@Max(1) @Min(-1) Integer status) {
		super();
		this.email = email;
		this.status = status;
	}
	public List<UcenikEntity> getUcenici() {
		return ucenici;
	}
	public void setUcenici(List<UcenikEntity> ucenici) {
		this.ucenici = ucenici;
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
	
	public void remove(RoditeljEntity roditelj) {
		
	}

}
