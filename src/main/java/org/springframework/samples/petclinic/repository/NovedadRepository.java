<<<<<<< HEAD
package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Novedad;

public interface NovedadRepository extends CrudRepository<Novedad,Integer>{
	Collection<Novedad> findAll();
}
=======
package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Novedad;

public interface NovedadRepository extends CrudRepository<Novedad,Integer>{
	Collection<Novedad> findAll();
}
>>>>>>> parent of af356ce... Añadido autores
