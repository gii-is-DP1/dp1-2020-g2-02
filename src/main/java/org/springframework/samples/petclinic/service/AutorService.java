package org.springframework.samples.petclinic.service;

import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Autor;
import org.springframework.samples.petclinic.repository.AutorRepository;
import org.springframework.stereotype.Service;

@Service
public class AutorService {

	@Autowired
	AutorRepository AutorRepo;
	
	@Autowired
	LibroService libroService;
	
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
	

}
