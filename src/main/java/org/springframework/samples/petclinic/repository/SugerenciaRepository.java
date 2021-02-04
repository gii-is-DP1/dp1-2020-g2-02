package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Sugerencia;

public interface SugerenciaRepository extends CrudRepository<Sugerencia, Integer>{
	@Query("SELECT s FROM Sugerencia s ORDER BY s.tituloLibro")
	Collection<Sugerencia> findAllOrderByTituloLibro();
}
