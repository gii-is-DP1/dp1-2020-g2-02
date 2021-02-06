package org.springframework.samples.petclinic.service;


 import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Puntuacion;
import org.springframework.samples.petclinic.repository.PuntuacionRepository;
import org.springframework.samples.petclinic.service.exceptions.LibroNoPrestadoAnteriormenteException;
import org.springframework.stereotype.Service;

 @Service
 public class PuntuacionService {

 	@Autowired
 	PuntuacionRepository PuntuacionRepo;
 	
 	@Autowired
 	PrestamoService prestamoService;
 	
 	

 	public Collection<Puntuacion> findAll(){
 		return PuntuacionRepo.findAll();
 	}


 	public Optional<Puntuacion> findById(int id) {
 		return PuntuacionRepo.findById(id);
 	}

 	public void save(@Valid Puntuacion Puntuacion) {
 		PuntuacionRepo.save(Puntuacion);

 	}
 	
 	
 	public void savePuntuacion(Puntuacion puntuacion) throws LibroNoPrestadoAnteriormenteException {
 		if(prestamoService.findAll().stream().noneMatch(x->x.getMiembro().getId().equals(puntuacion.getMiembro().getId()) && x.getEjemplar().getLibro().getId().equals(puntuacion.getLibro().getId())))
 			throw new LibroNoPrestadoAnteriormenteException();
 		PuntuacionRepo.save(puntuacion);
 	}
 }
