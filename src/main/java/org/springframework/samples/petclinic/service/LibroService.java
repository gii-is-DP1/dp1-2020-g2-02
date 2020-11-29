package org.springframework.samples.petclinic.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Genero;
import org.springframework.samples.petclinic.model.Libro;
import org.springframework.samples.petclinic.repository.LibroRepository;
import org.springframework.stereotype.Service;

@Service
public class LibroService {

	@Autowired
	LibroRepository LibroRepo;

	@Autowired
	private GeneroService generoService;
	
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
	
	public Collection<Genero> getGenerosLibro(Libro libro){
		Collection<Genero> generos = new ArrayList<Genero>();
		Iterator<Genero> it = generoService.findAll().iterator();
		while (it.hasNext()) {
			Genero genero = it.next();
			
			if (genero.getLibro().getId() == libro.getId()) {
				generos.add(genero);
			}
		}
		System.out.println("AQUI " + generos.size());
		return generos;
	}
	
}
