package com.iktpreobuka.eeeDnevnik.repositories;

import java.util.Date;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.iktpreobuka.eeeDnevnik.entities.OdeljenjeEntity;
import com.iktpreobuka.eeeDnevnik.entities.UcenikEntity;
import com.iktpreobuka.eeeDnevnik.entities.UcenikOdeljenjeEntity;

@Repository
public interface UcenikOdeljenjeRepository extends CrudRepository<UcenikOdeljenjeEntity, Integer>{
	
	public UcenikOdeljenjeEntity getByUcenikAndOdeljenje(Integer id, Integer id1);

	public Object findByUcenikAndOdeljenjeAndDatumPrebacivanja(UcenikEntity korisnik, OdeljenjeEntity odeljenje, Date datumPrebacivanja);

}
