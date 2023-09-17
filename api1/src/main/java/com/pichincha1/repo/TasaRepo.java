package com.pichincha1.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pichincha1.model.Tasa;

@Repository
public interface TasaRepo extends CrudRepository<Tasa, Integer> {

	@Query(value="SELECT max(id) as id, created_by, created_date, last_modified_by, last_modified_date, moneda1, moneda2, tipo_cambio FROM tasa t WHERE t.moneda1=?1 and t.moneda2=?2", nativeQuery=true)
	Tasa findTasa(String moneda1, String moneda2);
	
	List<Tasa> findAll();
	<S extends Tasa> S save(Tasa tasa);
	void deleteById(Integer id);
	Optional<Tasa> findById(Integer id);
	
}