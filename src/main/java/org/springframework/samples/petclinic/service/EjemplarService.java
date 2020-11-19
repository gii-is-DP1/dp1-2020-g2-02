package org.springframework.samples.petclinic.service;

import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Ejemplar;
import org.springframework.samples.petclinic.repository.EjemplarRepository;
import org.springframework.stereotype.Service;

@Service
public class EjemplarService {
	@Autowired
	EjemplarRepository EjemplarRepo;
	
	public Collection<Ejemplar> findAll(){
		return EjemplarRepo.findAll();
	}


	public Optional<Ejemplar> findById(int id) {
		return EjemplarRepo.findById(id);
	}


	public void delete(Ejemplar Ejemplar) {
		EjemplarRepo.deleteById(Ejemplar.getId());

	}

	public void save(@Valid Ejemplar Ejemplar) {
		EjemplarRepo.save(Ejemplar);

	}
}
