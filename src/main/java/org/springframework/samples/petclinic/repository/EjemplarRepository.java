package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Disponibilidad;
import org.springframework.samples.petclinic.model.Editorial;
import org.springframework.samples.petclinic.model.Ejemplar;
import org.springframework.samples.petclinic.model.Libro;

public interface EjemplarRepository extends CrudRepository<Ejemplar, Integer> {

	Collection<Ejemplar> findAll();

	Collection<Ejemplar> findAllByLibroAndDisponibilidad(Libro libro, Disponibilidad disponibilidad);


}
