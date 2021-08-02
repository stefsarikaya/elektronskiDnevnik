package com.iktpreobuka.eeeDnevnik.repositories;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.iktpreobuka.eeeDnevnik.entities.KorisnikEntity;
import com.iktpreobuka.eeeDnevnik.entities.RoditeljEntity;
import com.iktpreobuka.eeeDnevnik.entities.dto.NadjiRoditeljaDto;
import com.iktpreobuka.eeeDnevnik.entities.dto.RoditeljDto;
import com.iktpreobuka.eeeDnevnik.enumeration.EUloga;

@Repository
public interface RoditeljRepository extends CrudRepository<RoditeljEntity, Integer> {

	public Optional<RoditeljEntity> findById(Integer id);

	public RoditeljEntity getById(Integer id);

	public RoditeljEntity getByJmbg(String Jmbg);

	public void save(@Valid RoditeljDto noviKorisnik);

	public void save(KorisnikEntity korisnik);

	public RoditeljEntity findByIdAndStatusLike(Integer id, Integer i);

	public Iterable<RoditeljEntity> findByStatusLike(Integer status);

	public Object getByEmail(String email);

	public Object getByJmbgAndStatusLike(String jMBG, Integer status);

	public Object getByEmailAndStatusLike(String email, Integer status);

	@Query("select new com.iktpreobuka.eeeDnevnik.entities.dto.NadjiRoditeljaDto(r, na) from RoditeljEntity r join r.nalozi na where na.uloga=:uloga and r.status=:status and na.status=1")
	public Iterable<NadjiRoditeljaDto> findByStatusWithKorisnikNalog(@Param("status") Integer status,
			@Param("uloga") EUloga uloga);

}
