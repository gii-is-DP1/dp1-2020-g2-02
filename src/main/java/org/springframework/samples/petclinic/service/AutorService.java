package org.springframework.samples.petclinic.service;

import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Autor;
import org.springframework.samples.petclinic.repository.AutorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	public void save(@Valid Autor Autor) {
		AutorRepo.save(Autor);

	}
	
	@Transactional
	public int autorCount() {
		return (int) AutorRepo.count();
	}
	
	public String getNombreCompleto(Autor autor) {
		return autor.getNombre() + " " + autor.getApellidos();
	}
}
