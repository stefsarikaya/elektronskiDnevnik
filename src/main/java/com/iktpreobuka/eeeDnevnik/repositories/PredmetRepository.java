package com.iktpreobuka.eeeDnevnik.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.iktpreobuka.eeeDnevnik.entities.NastavnikPredmetOdeljenjeEntity;
import com.iktpreobuka.eeeDnevnik.entities.PredmetEntity;
import com.iktpreobuka.eeeDnevnik.entities.dto.PredmetNastavnikDto;
import com.iktpreobuka.eeeDnevnik.entities.dto.UcenikPredmetDto;

@Repository
public interface PredmetRepository extends CrudRepository<PredmetEntity, Integer>{
	
	public PredmetEntity getById(Integer id);
	
	public Iterable<PredmetEntity> findByStatusLike(Integer status);

	// nalazi predmet prema nastavniku
	 @Query("select p from PredmetEntity p join p.nastavnici n where n.nastavnik.id=:nastavnik and p.status=1 and n.status=1")
	 public Iterable<PredmetEntity> findByNastavnik(@Param("nastavnik") Integer teacher);

	
	//nalazi predmete prema uceniku !!! RELLEVEANTTT!!!!!!!!!!!!
	 @Query("select pre from UcenikEntity u join u.odeljenja od join od.odeljenje o join o.razredi ra join ra.razred r join r.predmeti pr join pr.predmet pre where u.id=:ucenik and u.status=1 and od.status=1 and o.status=1 and pr.status=1 and ra.status=1 and r.status=1 and pre.status=1")
	 public Iterable<PredmetEntity> findByUcenik(@Param("ucenik") Integer student);
	
	 
	@Query("select npo from NastavnikPredmetOdeljenjeEntity npo join npo.predajeOdeljenje po join po.studenti s where s.ucenik.id=:ucenik")
	public Iterable<NastavnikPredmetOdeljenjeEntity> findByNastavnikWithRazredAndOdeljenje(@Param("ucenik") Integer student);
	
	@Query("select new com.iktpreobuka.eeeDnevnik.entities.dto.PredmetNastavnikDto(naod.predajuciNastavnik, pre) from UcenikEntity u join u.odeljenja od join od.odeljenje o join o.razredi ra join ra.razred r join r.predmeti pr join pr.predmet pre join pre.nastavnici_odeljenja naod where u.id=:ucenik and u.status=1 and o.status=1 and od.status=1 and ra.status=1 and r.status=1 and pr.status=1 and pre.status=1 and naod.status=1")
	public Iterable<PredmetNastavnikDto> findPredmetAndNastavnikByUcenik(@Param("ucenik") Integer ucenik);
	
	@Query("select new com.iktpreobuka.eeeDnevnik.entities.dto.UcenikPredmetDto(u, p) from RoditeljEntity r join r.ucenici u join u.odeljenja od join od.odeljenje o join o.razredi ra join ra.razred r join r.predmeti pr join pr.predmet p where r.id=:roditelj and u.status=1 and o.status=1 and ra.status=1 and pr.status=1 and p.status=1")
	public List<UcenikPredmetDto> findByRoditelj(@Param("roditelj") Integer roditelj);

	public PredmetEntity findByIdAndStatusLike(Integer p_id, Integer status);

	@Query("select pr from NastavnikEntity n join n.odeljenja od join od.glavnoOdeljenje go join go.razredi ra join ra.razred r join r.predmeti pre join pre.predmet pr where n.id=:nastavnik and n.status=1 and ra.status=1 and r.status=1 and pre.status=1 and pr.status=1 and od.status=1 and go.status=1")
	public Iterable<PredmetEntity> findByRazredniStaresina(@Param("nastavnik") Integer nastavnik);

	public PredmetEntity findByImePredmet(String predmetIme);



}
