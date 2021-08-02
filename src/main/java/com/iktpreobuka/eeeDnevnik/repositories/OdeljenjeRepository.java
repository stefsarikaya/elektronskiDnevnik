package com.iktpreobuka.eeeDnevnik.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.iktpreobuka.eeeDnevnik.entities.OdeljenjeEntity;
import com.iktpreobuka.eeeDnevnik.entities.dto.OdeljenjeRazredDto;

@Repository
public interface OdeljenjeRepository extends CrudRepository<OdeljenjeEntity, Integer>{
	
	public OdeljenjeEntity getById(Integer id);
	
	public Iterable<OdeljenjeEntity> findByStatusLike(Integer status);

	public OdeljenjeEntity findByIdAndStatusLike(Integer depatmentId, Integer status);

	public OdeljenjeEntity findByOdeljenjeOznakaAndGodinaUpisaAndStatusLike(String odeljenjeOznaka, String godinaUpisa, Integer status);

	public OdeljenjeEntity findByOdeljenjeOznakaAndStatusLike(String departmentLabel, Integer status);

	@Query("select new com.iktpreobuka.eeeDnevnik.entities.dto.OdeljenjeRazredDto(r.razredBroj, o.odeljenjeOznaka, o.status) from OdeljenjeEntity o join o.razredi ra join ra.razred r where o.status=:status and o.id=:id and ra.status=:status")
	public Iterable<OdeljenjeRazredDto> findWithRazred_odeljenjeByIdAndStatusLike(@Param("id") Integer id, @Param("status") Integer status);

}
