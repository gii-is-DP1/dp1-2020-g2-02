package org.springframework.samples.petclinic.repository;

import java.time.LocalDate;
import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Novedad;

public interface NovedadRepository extends CrudRepository<Novedad,Integer>{
	Collection<Novedad> findAllByOrderByFechaPublicacionDesc();
	
	Collection<Novedad> findByFechaPublicacion(LocalDate fecha);
}