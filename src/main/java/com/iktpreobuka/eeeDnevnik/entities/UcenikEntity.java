package com.iktpreobuka.eeeDnevnik.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.eeeDnevnik.enumeration.EPol;
import com.iktpreobuka.eeeDnevnik.enumeration.EUloga;
import com.iktpreobuka.eeeDnevnik.security.Views;

@Entity
@Table(name = "ucenik")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class UcenikEntity extends KorisnikEntity{
	
	@JsonIgnore
	@JsonView(Views.ucenik.class)
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "roditelj")
	@NotNull(message = "Roditelj mora biti dodat")
	private RoditeljEntity roditelj;
	
	@JsonView(Views.ucenik.class)
	@JsonIgnore
	@OneToMany(mappedBy = "ucenik", fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	private List<OcenaEntity> ocene = new ArrayList<>();

	@JsonIgnore
	@JsonView(Views.ucenik.class)
	@OneToMany(mappedBy = "ucenik", fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH }, orphanRemoval = true)
	private List<UcenikOdeljenjeEntity> odeljenja = new ArrayList<>();

	@JsonView(Views.ucenik.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@Column(name = "datum_upisa")
	@NotNull(message = "Datum upisa mora biti unet")
	private Date datumUpisa;

	@JsonView(Views.ucenik.class)
	@Column(name = "identifikacioni_broj", unique = true, length = 50)
	@Pattern(regexp = "^[0-9]{8,8}$", message = "Skolski identifikacioni broj nije validan, mora biti 8 brojeva u nizu.")
	@NotNull(message = "Skolski identifikacioni broj mora biti unet.")
	private String skolskiIdentifikacioniBroj;
	
	@JsonView(Views.admin.class)
	@Max(1)
    @Min(-1)
    @Column(name = "status", nullable = false)
	private Integer status;
	
	private static final Integer NEAKTIVAN = 0;
	private static final Integer AKTIVAN = 1;
	private static final Integer ARHIVIRAN = -1;
	
	public UcenikEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * @param roditelj
	 * @param ocene
	 * @param odeljenja
	 * @param datumUpisa
	 * @param skolskiIdentifikacioniBroj
	 * @param status
	 */
	public UcenikEntity(@NotNull(message = "Roditelj mora biti dodat") RoditeljEntity roditelj, List<OcenaEntity> ocene,
			List<UcenikOdeljenjeEntity> odeljenja, @NotNull(message = "Datum upisa mora biti unet") Date datumUpisa,
			@Pattern(regexp = "^[0-9]{8,8}$", message = "Skolski identifikacioni broj nije validan, mora biti 8 brojeva u nizu.") @NotNull(message = "Skolski identifikacioni broj mora biti unet.") String skolskiIdentifikacioniBroj,
			@Max(1) @Min(-1) Integer status) {
		super();
		this.roditelj = roditelj;
		this.ocene = ocene;
		this.odeljenja = odeljenja;
		this.datumUpisa = datumUpisa;
		this.skolskiIdentifikacioniBroj = skolskiIdentifikacioniBroj;
		this.status = status;
	}
	
	
	/**
	 * @param datumUpisa
	 * @param skolskiIdentifikacioniBroj
	 * @param status
	 */
	public UcenikEntity(
			@Pattern(regexp = "^([0][1-9]|[1|2][0-9]|[3][0|1])[./-]([0][1-9]|[1][0-2])[./-]([1-2][0-9]{3})$", message = "Datum upisa nije validan, mora biti u formatu: dd-MM-yyyy format.") @NotNull(message = "Datum upisa mora biti unet") Date datumUpisa,
			@Pattern(regexp = "^[0-9]{8,8}$", message = "Skolski identifikacioni broj nije validan, mora biti 8 brojeva u nizu.") @NotNull(message = "Skolski identifikacioni broj mora biti unet.") String skolskiIdentifikacioniBroj,
			@Max(1) @Min(-1) Integer status) {
		super();
		this.datumUpisa = datumUpisa;
		this.skolskiIdentifikacioniBroj = skolskiIdentifikacioniBroj;
		this.status = status;
	}
	
	public RoditeljEntity getRoditelj() {
		return roditelj;
	}
	public void setRoditelj(RoditeljEntity roditelj) {
		this.roditelj = roditelj;
	}
	public List<OcenaEntity> getOcene() {
		return ocene;
	}
	public void setOcene(List<OcenaEntity> ocene) {
		this.ocene = ocene;
	}
	public List<UcenikOdeljenjeEntity> getOdeljenja() {
		return odeljenja;
	}
	public void setOdeljenja(List<UcenikOdeljenjeEntity> odeljenja) {
		this.odeljenja = odeljenja;
	}
	public Date getDatumUpisa() {
		return datumUpisa;
	}
	public void setDatumUpisa(Date datumUpisa) {
		this.datumUpisa = datumUpisa;
	}
	public String getSkolskiIdentifikacioniBroj() {
		return skolskiIdentifikacioniBroj;
	}
	public void setSkolskiIdentifikacioniBroj(String skolskiIdentifikacioniBroj) {
		this.skolskiIdentifikacioniBroj = skolskiIdentifikacioniBroj;
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
