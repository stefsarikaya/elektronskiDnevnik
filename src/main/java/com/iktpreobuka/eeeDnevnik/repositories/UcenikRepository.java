package com.iktpreobuka.eeeDnevnik.repositories;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.iktpreobuka.eeeDnevnik.entities.KorisnikEntity;
import com.iktpreobuka.eeeDnevnik.entities.UcenikEntity;
import com.iktpreobuka.eeeDnevnik.entities.dto.UcenikDto;

@Repository
public interface UcenikRepository extends CrudRepository<UcenikEntity, Integer  > {

	public Optional<UcenikEntity> findById(Integer id);

	public UcenikEntity getById(Integer id);

	public UcenikEntity getByJmbg(String getJmbg);

	// vraća ucenike za roditelja
	@Query("select u from UcenikEntity u join u.roditelj ro where ro.id=:roditeljId and u.status=1")
	public List<UcenikEntity> findByRoditelj(@Param("roditeljId") Integer roditeljId);

	// NECE DA TI RADI, POPRAVI GA!!!!!!!!!!!
	// nalazi učenike prema razrednom starešini
	//@Query("select new com.iktpreobuka.eeeDnevnik.entities.dto.UcenikDto(u.ime, u.prezime, u.skolskiIdentifikacioniBroj, od.odeljenjeOznaka) from UcenikEntity u join u.odeljenja od join od.odeljenje o join o.razredi ra join ra.razred r join o.nastavnici na join na.razredniStaresina rt where rt.id=:nastavnik and od.status=1 and u.status=1 and ra.status=1 and o.status=1")
	//public List<UcenikDto> findByRazredniStaresina(@Param("nastavnik") Integer nastavnik);

	public void save(@Valid UcenikDto newUser);
	public void save(KorisnikEntity korisnik);
	

	//public void save(UcenikEntity korisnik); //ovo si dodao možda proradi

	public UcenikEntity findByIdAndStatusLike(Integer UcenikEntity, Integer i);

	// pronalazi studente prema razrednom starešini POPRAVI
	@Query("select u from UcenikEntity u join u.odeljenja o join o.odeljenje od join od.nastavnici n join n.razredniStaresina rs where rs.id=:nastavnik and rs.status=1 and n.status=1 and u.status=1 and o.status=1 and od.status=1")
	public List<UcenikEntity> findByrazredniStaresinaId(@Param("nastavnik") Integer nastavnik);
	public Iterable<UcenikEntity> findByStatusLike(Integer status);

	public UcenikEntity getBySkolskiIdentifikacioniBroj(String skolskiIdentifikacioniBroj);

	public Object getBySkolskiIdentifikacioniBrojAndStatusLike(String skolskiIdentifikacioniBroj, Integer status);

	public Object getByJmbgAndStatusLike(String Jmbg, Integer status);

	// pronalazi studente prema profesoru koji im predaje
	@Query("select u from UcenikEntity u join u.odeljenja o join o.odeljenje od join od.nastavnici_predmeti np join np.predajePredmet pp where pp.id=:nastavnik and u.status=1 and o.status=1 and od.status=1 and pp.status=1 and np.status=1")
	public List<UcenikEntity> findByPredajuciNastavnik(@Param("nastavnik") Integer nastavnik);

}
