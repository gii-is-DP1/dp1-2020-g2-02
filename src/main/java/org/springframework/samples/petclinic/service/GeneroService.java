package org.springframework.samples.petclinic.service;

import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Genero;
import org.springframework.samples.petclinic.repository.GeneroRepository;
import org.springframework.stereotype.Service;

@Service
public class GeneroService {

	@Autowired
	GeneroRepository GeneroRepo;
	
	
	public Collection<Genero> findAll(){
		return GeneroRepo.findAll();
	}


	public Optional<Genero> findById(int id) {
		return GeneroRepo.findById(id);
	}

	public void save(@Valid Genero Genero) {
		GeneroRepo.save(Genero);

	}
	
}
