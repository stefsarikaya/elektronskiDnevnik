package com.iktpreobuka.eeeDnevnik.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.iktpreobuka.eeeDnevnik.entities.RazredniStaresinaOdeljenjeEntity;
@Repository
public interface RazredniStaresinaOdeljenjeRepository extends CrudRepository<RazredniStaresinaOdeljenjeEntity, Integer>{
	
	public Optional<RazredniStaresinaOdeljenjeEntity> findById(Integer id);
	public RazredniStaresinaOdeljenjeEntity getById(Integer id);
	

}
