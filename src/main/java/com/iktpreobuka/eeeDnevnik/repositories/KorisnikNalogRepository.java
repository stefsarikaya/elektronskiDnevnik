package com.iktpreobuka.eeeDnevnik.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.iktpreobuka.eeeDnevnik.entities.AdminEntity;
import com.iktpreobuka.eeeDnevnik.entities.KorisnikEntity;
import com.iktpreobuka.eeeDnevnik.entities.KorisnikNalogEntity;
import com.iktpreobuka.eeeDnevnik.entities.NastavnikEntity;
import com.iktpreobuka.eeeDnevnik.entities.RoditeljEntity;
import com.iktpreobuka.eeeDnevnik.entities.UcenikEntity;
import com.iktpreobuka.eeeDnevnik.enumeration.EUloga;

@Repository
public interface KorisnikNalogRepository extends CrudRepository<KorisnikNalogEntity, Integer> {
	
	public KorisnikNalogEntity getById(Integer id);
	public KorisnikNalogEntity getByUsername(String username);
	public KorisnikNalogEntity getByKorisnik(KorisnikEntity user);
	
	@Query("select kn.korisnik from KorisnikNalogEntity kn where kn.username=:username and kn.status=:status")
	public KorisnikEntity findKorisnikByUsernameAndStatusLike(@Param("username") String username, @Param("status") Integer status);

	@Query("select kn.korisnik from KorisnikNalogEntity kn where kn.username=:username and kn.status=:status")
	public NastavnikEntity findNastavnikByUsernameAndStatusLike(@Param("username") String username, @Param("status") Integer status);
	
	public KorisnikNalogEntity findByKorisnikAndUlogaLikeAndStatusLike(KorisnikEntity korisnik, EUloga EUloga, Integer status);
	
	@Query("select kn from KorisnikNalogEntity kn join kn.korisnik ko where kn.id=:korisnikId and kn.uloga=:EUloga and kn.status=:status")
	public KorisnikNalogEntity findByKorisnikIdAndUlogaLikeAndStatusLike(@Param("korisnikId") Integer korisnikId, @Param("EUloga") EUloga EUloga, @Param("status") Integer status);
	
	public Iterable<KorisnikNalogEntity> findByUlogaLikeAndStatusLike(EUloga uloga, Integer status);
	public Iterable<KorisnikNalogEntity> findByStatusLike(Integer status);
	public KorisnikNalogEntity findByIdAndStatusLike(Integer id, Integer status);
	public KorisnikNalogEntity findByKorisnikAndUlogaAndStatusLike(KorisnikEntity korisnik, EUloga uloga, Integer status);
	public KorisnikNalogEntity findByKorisnikAndStatusLike(String ime, Integer Status);
	public KorisnikNalogEntity findByKorisnikAndUlogaLike(NastavnikEntity user, EUloga uloga);
	public KorisnikNalogEntity findByKorisnikAndUlogaLike(UcenikEntity user, EUloga uloga); 
	public KorisnikNalogEntity findByKorisnikAndUlogaLike(RoditeljEntity user, EUloga uloga);  
	public KorisnikNalogEntity findByKorisnikAndUlogaLike(AdminEntity korisnik, EUloga uloga);

}
