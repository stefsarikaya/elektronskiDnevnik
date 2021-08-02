package com.iktpreobuka.eeeDnevnik.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.iktpreobuka.eeeDnevnik.entities.NastavnikPredmetEntity;
@Repository
public interface NastavnikPredmetRepository extends CrudRepository<NastavnikPredmetEntity, Integer>{
	
	public NastavnikPredmetEntity getByNastavnikAndPredmet(Integer id, Integer id1);

}
