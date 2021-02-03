
package org.springframework.samples.petclinic.repository;

import java.time.LocalDate;
import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Miembro;
import org.springframework.samples.petclinic.model.Prestamo;


public interface PrestamoRepository extends CrudRepository<Prestamo,Integer>{
		Collection<Prestamo> findAll();
		
		@Query("SELECT p FROM Prestamo p WHERE p.fechaDevolucion < ?1 AND p.finalizado=false")
		Collection<Prestamo> prestamosConFechaDevolucionTardia(LocalDate fecha);

		@Query("SELECT p FROM Prestamo p WHERE p.miembro=:miembro AND p.finalizado=false")
		Collection<Prestamo> prestamosEnProceso(Miembro miembro);
		
		@Query("SELECT p FROM Prestamo p WHERE p.fechaDevolucion<:fecha AND p.miembro=:miembro AND p.finalizado=false")
        Collection<Prestamo> prestamosEnProcesoFecha(Miembro miembro, LocalDate fecha);

		Collection<Prestamo> findByFechaPrestamo(LocalDate fecha);
	
}
