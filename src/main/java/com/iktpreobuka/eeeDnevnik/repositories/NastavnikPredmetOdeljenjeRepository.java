package com.iktpreobuka.eeeDnevnik.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.iktpreobuka.eeeDnevnik.entities.NastavnikEntity;
import com.iktpreobuka.eeeDnevnik.entities.NastavnikPredmetOdeljenjeEntity;
import com.iktpreobuka.eeeDnevnik.entities.PredmetEntity;
import com.iktpreobuka.eeeDnevnik.entities.UcenikEntity;

@Repository
public interface NastavnikPredmetOdeljenjeRepository extends CrudRepository<NastavnikPredmetOdeljenjeEntity, Integer> {

	@Query("select npo from NastavnikPredmetOdeljenjeEntity npo join npo.predajeOdeljenje po join po.studenti s where npo.predajuciNastavnik=:nastavnik and npo.predajePredmet=:predmet and s.ucenik=:ucenik and po.status=1 and npo.status=1 and s.status=1")
	public NastavnikPredmetOdeljenjeEntity getByPredajuciNastavnikAndPredajePredmetAndPredajeOdeljenje(@Param("nastavnik") NastavnikEntity nastavnik, @Param("predmet") PredmetEntity predmet, @Param("ucenik") UcenikEntity ucenik);

	@Query("select npo from NastavnikPredmetOdeljenjeEntity npo join npo.predajeOdeljenje po join po.studenti s join po.razredi ra where npo.predajuciNastavnik=:nastavnik and npo.predajePredmet=:predmet and s.ucenik=:ucenik and po.status=1 and npo.status=1 and s.status=1 and ra.status=1")
	public NastavnikPredmetOdeljenjeEntity getByPredajuciNastavnikAndPredajePredmetAndPredajeOdeljenjeAndPredajeRazred(@Param("nastavnik") NastavnikEntity nastavnik, @Param("predmet") PredmetEntity predmet, @Param("ucenik") UcenikEntity ucenik);

	public Iterable<NastavnikPredmetOdeljenjeEntity> findByStatusLike(Integer status);
}
