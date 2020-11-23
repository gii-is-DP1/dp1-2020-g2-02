package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Bibliotecario;
import org.springframework.samples.petclinic.model.User;


public interface BibliotecarioRepository extends  CrudRepository<Bibliotecario, Integer>{
    Collection<Bibliotecario> findAll();

	Bibliotecario findByUser(User user);
}
