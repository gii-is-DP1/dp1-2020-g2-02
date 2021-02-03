package org.springframework.samples.petclinic.service;

import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Libro;
import org.springframework.samples.petclinic.model.Puntuacion;
import org.springframework.samples.petclinic.repository.LibroRepository;
import org.springframework.stereotype.Service;

@Service
public class LibroService {

	@Autowired
	LibroRepository LibroRepo;
	

	
	@Autowired
 	PuntuacionService puntuacionService;

	
	public Collection<Libro> findAll(){
		return LibroRepo.findAll();
	}


	public Optional<Libro> findById(int id) {
		return LibroRepo.findById(id);
	}


	public void delete(Libro Libro) {
		LibroRepo.deleteById(Libro.getId());

	}

	public void save(@Valid Libro Libro) {
		LibroRepo.save(Libro);

	}
	
	
	public Double getNotaMedia(Libro libro) {
 		Collection<Puntuacion> puntuaciones = puntuacionService.findAll();
 		Iterator<Puntuacion> it = puntuaciones.iterator();
 		Double media = 0.0;
 		int n = 0;

 		while (it.hasNext()) {
 			Puntuacion puntuacion = it.next();
 			if (puntuacion.getLibro()==libro) {
 				media += puntuacion.getPuntaje();
 				n++;
 			}
 		}
 		return media/n;
 	}
	
}
