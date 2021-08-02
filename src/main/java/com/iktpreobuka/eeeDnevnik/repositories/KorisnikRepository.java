package com.iktpreobuka.eeeDnevnik.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.iktpreobuka.eeeDnevnik.entities.KorisnikEntity;
@Repository
public interface KorisnikRepository extends CrudRepository<KorisnikEntity, Integer>{
	
	public KorisnikEntity getById(Integer id);

	public KorisnikEntity findByJmbg(String getjMBG);

	public KorisnikEntity getByJmbg(String jMBG);

}
