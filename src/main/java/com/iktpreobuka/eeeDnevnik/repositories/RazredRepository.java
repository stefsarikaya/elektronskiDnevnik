package com.iktpreobuka.eeeDnevnik.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.iktpreobuka.eeeDnevnik.entities.PredmetEntity;
import com.iktpreobuka.eeeDnevnik.entities.RazredEntity;
import com.iktpreobuka.eeeDnevnik.enumeration.ERazred;

@Repository
public interface RazredRepository extends CrudRepository<RazredEntity, Integer>  {
	
public RazredEntity getById(Integer id);
	
	public Iterable<RazredEntity> findByStatusLike(Integer status);
	
	public RazredEntity findByIdAndStatusLike(Integer id, Integer status);
	
	@Query("select p.predmet from RazredEntity r join r.predmeti p where p.razred=:razred and p.status=1")
	public Iterable<PredmetEntity> findPredmetiByRazred(@Param("razred") RazredEntity razred);

	public RazredEntity getByRazredBroj(ERazred razredBroj);

	public RazredEntity findByRazredBrojAndStatusLike(ERazred valueOf, Integer status);
	
	

}
