package com.iktpreobuka.eeeDnevnik.repositories;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.iktpreobuka.eeeDnevnik.entities.KorisnikEntity;
import com.iktpreobuka.eeeDnevnik.entities.NastavnikEntity;
import com.iktpreobuka.eeeDnevnik.entities.dto.NastavnikDto;


@Repository
public interface NastavnikRepository extends CrudRepository<NastavnikEntity, Integer>{
	
	public NastavnikEntity getById(Integer id);

	public Optional<NastavnikEntity> findById(Integer id);

	public NastavnikEntity findByIdAndStatusLike(Integer id, Integer status);

	public NastavnikEntity getByJmbg(String Jmbg);

	public void save(@Valid NastavnikDto noviKorisnik);

	public void save(@Valid KorisnikEntity user);

	public Iterable<NastavnikEntity> findByStatusLike(Integer status);

	public NastavnikEntity getByJmbgAndStatusLike(String Jmbg, Integer status);

	public NastavnikEntity getByIdAndStatusLike(Integer userId, Integer status);



}
