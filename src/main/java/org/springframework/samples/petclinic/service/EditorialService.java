package org.springframework.samples.petclinic.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Editorial;
import org.springframework.samples.petclinic.model.Libro;
import org.springframework.samples.petclinic.repository.EditorialRepository;
import org.springframework.stereotype.Service;

@Service
public class EditorialService {

	@Autowired
	EditorialRepository EditorialRepo;

	@Autowired
	LibroService libroService;
	
	public Collection<Editorial> findAll(){
		return EditorialRepo.findAll();
	}


	public Optional<Editorial> findById(int id) {
		return EditorialRepo.findById(id);
	}


	public void delete(Editorial Editorial) {
		EditorialRepo.deleteById(Editorial.getId());

	}

	public void save(@Valid Editorial Editorial) {
		EditorialRepo.save(Editorial);

	}
	
	public Collection<Libro> getLibrosEditorial(Editorial editorial){
		Collection<Libro> libros = new ArrayList<Libro>();
		Iterator<Libro> it = libroService.findAll().iterator();
		while(it.hasNext()) {
			Libro libro = it.next();
			if (libro.getEditorial().equals(editorial))
				libros.add(libro);
		}
		return libros;
	}
}
