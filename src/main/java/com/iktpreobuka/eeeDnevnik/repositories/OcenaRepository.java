package com.iktpreobuka.eeeDnevnik.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.iktpreobuka.eeeDnevnik.entities.OcenaEntity;
import com.iktpreobuka.eeeDnevnik.entities.UcenikEntity;

@Repository
public interface OcenaRepository extends CrudRepository<OcenaEntity, Integer>{
	
	public List<OcenaEntity> findByUcenikAndStatusLike(UcenikEntity ucenikID, Integer status);
	
	public OcenaEntity findByUcenikAndIdAndStatusLike(UcenikEntity korisnik, Integer ocenaInteger, Integer statusInteger);

	public Iterable<OcenaEntity> findByStatusLike(Integer status);
	
	public OcenaEntity findByIdAndStatusLike(Integer id, Integer status);

}
