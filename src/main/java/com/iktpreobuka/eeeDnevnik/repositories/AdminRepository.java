package com.iktpreobuka.eeeDnevnik.repositories;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.iktpreobuka.eeeDnevnik.entities.AdminEntity;
import com.iktpreobuka.eeeDnevnik.entities.KorisnikEntity;
import com.iktpreobuka.eeeDnevnik.enumeration.EUloga;

@Repository
public interface AdminRepository extends CrudRepository<AdminEntity, Integer>{
	
public Optional<AdminEntity> findById(Integer id);
	
	public AdminEntity getById(Integer id);
	
	
	public AdminEntity getByEmail(String email);
	
	public AdminEntity findByIdAndStatusLike(Integer id, Integer status);
	
	public AdminEntity getByJmbg (String getjMBG);
	
	public Iterable<AdminEntity> findByStatusLike(Integer i);



	public void save(@Valid KorisnikEntity user);
	public Object getByEmailAndStatusLike(String email, Integer status);
	public Object getByJmbgAndStatusLike(String Jmbg, Integer status);
	public Object getByJmbgAndUlogaAndStatusLike(String Jmbg, EUloga uloga, Integer status);


	public AdminEntity getByIdAndStatusLike(Integer userId, Integer status);

}
