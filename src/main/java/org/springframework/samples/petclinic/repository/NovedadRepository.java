package org.springframework.samples.petclinic.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Novedad;

public interface NovedadRepository extends CrudRepository<Novedad,Integer>{

}
