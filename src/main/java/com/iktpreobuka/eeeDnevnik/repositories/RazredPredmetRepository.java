package com.iktpreobuka.eeeDnevnik.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.iktpreobuka.eeeDnevnik.entities.RazredPredmetEntity;
@Repository
public interface RazredPredmetRepository extends CrudRepository<RazredPredmetEntity, Integer>{
	
	public RazredPredmetEntity getByRazredAndPredmet(Integer id1, Integer id2);

}
