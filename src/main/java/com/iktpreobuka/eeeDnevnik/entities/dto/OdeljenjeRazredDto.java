package com.iktpreobuka.eeeDnevnik.entities.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.eeeDnevnik.enumeration.ERazred;
import com.iktpreobuka.eeeDnevnik.security.Views;



public class OdeljenjeRazredDto {
	
	@JsonView(Views.ucenik.class)
	@NotNull (message = "Broj razreda mora biti unet")
	private ERazred razredBroj;
	
	@JsonView(Views.ucenik.class)
	@NotNull (message = "Oznaka odeljenja mora biti uneta.")
	private String odeljenjeOznaka;
	

	@JsonView(Views.admin.class)
	@Max(1)
    @Min(-1)
	private Integer status;


	/**
	 * 
	 */
	public OdeljenjeRazredDto() {
		super();
		// TODO Auto-generated constructor stub
	}


	/**
	 * @param razredBroj
	 * @param odeljenjeOznaka
	 * @param status
	 */
	public OdeljenjeRazredDto(@NotNull(message = "Broj razreda mora biti unet") ERazred razredBroj,
			@NotNull(message = "Oznaka odeljenja mora biti uneta.") String odeljenjeOznaka,
			@Max(1) @Min(-1) Integer status) {
		super();
		this.razredBroj = razredBroj;
		this.odeljenjeOznaka = odeljenjeOznaka;
		this.status = status;
	}


	public ERazred getRazredBroj() {
		return razredBroj;
	}


	public void setRazredBroj(ERazred razredBroj) {
		this.razredBroj = razredBroj;
	}


	public String getOdeljenjeOznaka() {
		return odeljenjeOznaka;
	}


	public void setOdeljenjeOznaka(String odeljenjeOznaka) {
		this.odeljenjeOznaka = odeljenjeOznaka;
	}


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}
}
