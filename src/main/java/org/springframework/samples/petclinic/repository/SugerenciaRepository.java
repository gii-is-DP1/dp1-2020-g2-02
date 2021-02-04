package org.springframework.samples.petclinic.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Sugerencia;

public interface SugerenciaRepository extends CrudRepository<Sugerencia, Integer>{
	List<Sugerencia> findAllByOrderByTituloLibroAsc();
}
