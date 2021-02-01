package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Proveedor;

public interface ProveedorRepository extends  CrudRepository<Proveedor, Integer>{
	
    Collection<Proveedor> findAll();
    
}
