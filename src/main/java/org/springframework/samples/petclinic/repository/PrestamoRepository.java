
package org.springframework.samples.petclinic.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Libro;
import org.springframework.samples.petclinic.model.Miembro;
import org.springframework.samples.petclinic.model.Prestamo;


public interface PrestamoRepository extends CrudRepository<Prestamo,Integer>{
		Collection<Prestamo> findAll();

		@Query("SELECT MAX(p) FROM Prestamo p WHERE p.miembro=:miembro AND p.ejemplar.libro=:libro AND p.finalizado=false")
		Optional<Prestamo> prestamosDeLibroEnProceso(Miembro miembro, Libro libro);
	
}
