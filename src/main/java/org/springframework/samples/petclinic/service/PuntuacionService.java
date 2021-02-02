package org.springframework.samples.petclinic.service;


 import java.util.Collection;
 import java.util.Optional;

 import javax.validation.Valid;

 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.samples.petclinic.model.Puntuacion;
 import org.springframework.samples.petclinic.repository.PuntuacionRepository;
 import org.springframework.stereotype.Service;

 @Service
 public class PuntuacionService {

 	@Autowired
 	private PuntuacionRepository PuntuacionRepo;

 	public Collection<Puntuacion> findAll(){
 		return PuntuacionRepo.findAll();
 	}


 	public Optional<Puntuacion> findById(int id) {
 		return PuntuacionRepo.findById(id);
 	}


 	public void delete(Puntuacion Puntuacion) {
 		PuntuacionRepo.deleteById(Puntuacion.getId());

 	}

 	public void save(@Valid Puntuacion Puntuacion) {
 		PuntuacionRepo.save(Puntuacion);

 	}
 }
