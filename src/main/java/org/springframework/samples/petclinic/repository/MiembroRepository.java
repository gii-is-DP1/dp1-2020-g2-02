package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Miembro;
import org.springframework.samples.petclinic.model.User;


public interface MiembroRepository extends  CrudRepository<Miembro, Integer>{
    Collection<Miembro> findAll();

	Miembro findByUser(User user);
}
