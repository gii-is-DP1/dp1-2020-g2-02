<<<<<<< HEAD
package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Prestamo;


public interface PrestamoRepository extends CrudRepository<Prestamo,Integer>{
		Collection<Prestamo> findAll();
	
}
=======
package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Prestamo;


public interface PrestamoRepository extends CrudRepository<Prestamo,Integer>{
		Collection<Prestamo> findAll();
	
}
>>>>>>> parent of af356ce... Añadido autores
