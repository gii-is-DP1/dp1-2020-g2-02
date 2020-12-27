package org.springframework.samples.petclinic.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Autor;
import org.springframework.samples.petclinic.model.EsAutor;
import org.springframework.samples.petclinic.model.Libro;
import org.springframework.samples.petclinic.repository.AutorRepository;
import org.springframework.stereotype.Service;

@Service
public class AutorService {

	@Autowired
	AutorRepository AutorRepo;
	
	@Autowired
	LibroService libroService;

	@Autowired
	private EsAutorService esAutorService;
	
	public Collection<Autor> findAll(){
		return AutorRepo.findAll();
	}


	public Optional<Autor> findById(int id) {
		return AutorRepo.findById(id);
	}


	public void delete(Autor Autor) {
		AutorRepo.deleteById(Autor.getId());

	}

	public void save(@Valid Autor Autor) {
		AutorRepo.save(Autor);

	}
	
	public Collection<Libro> getLibrosAutor(Autor autor){
		Collection<Libro> libros = new ArrayList<Libro>();
		Iterator<EsAutor> it = esAutorService.findAll().iterator();
		while(it.hasNext()) {
			EsAutor esAutor = it.next();
			if (esAutor.getAutor().getId() == autor.getId()) {
				libros.add(libroService.findById(esAutor.getLibro().getId()).get());
			}
		}
		return libros;
	}
}
