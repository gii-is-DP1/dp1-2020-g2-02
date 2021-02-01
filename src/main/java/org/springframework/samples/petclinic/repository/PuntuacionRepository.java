package org.springframework.samples.petclinic.repository;

 import java.util.Collection;

 import org.springframework.data.repository.CrudRepository;
 import org.springframework.samples.petclinic.model.Puntuacion;

 public interface PuntuacionRepository extends  CrudRepository<Puntuacion, Integer>{
	 
     Collection<Puntuacion> findAll();
 }