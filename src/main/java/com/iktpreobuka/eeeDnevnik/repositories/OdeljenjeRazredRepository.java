package com.iktpreobuka.eeeDnevnik.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.iktpreobuka.eeeDnevnik.entities.OdeljenjeEntity;
import com.iktpreobuka.eeeDnevnik.entities.OdeljenjeRazredEntity;
import com.iktpreobuka.eeeDnevnik.entities.RazredEntity;

@Repository
public interface OdeljenjeRazredRepository extends CrudRepository<OdeljenjeRazredEntity, Integer>{
	
	public OdeljenjeRazredEntity getByRazredAndOdeljenje(Integer id, Integer id1);

	@Query("select r from RazredEntity r join r.odeljenja od where od.odeljenje=:ode and r.status=1 and od.status=:status")
	public RazredEntity getRazredByOdeljenjeAndStatusLike(@Param("ode") OdeljenjeEntity ode, @Param("status") Integer status);

}
