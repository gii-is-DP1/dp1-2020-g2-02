package org.springframework.samples.petclinic.service;

import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.EsAutor;
import org.springframework.samples.petclinic.repository.EsAutorRepository;
import org.springframework.stereotype.Service;

@Service
public class EsAutorService {

	@Autowired
	EsAutorRepository EsAutorRepo;
	
	
	public Collection<EsAutor> findAll(){
		return EsAutorRepo.findAll();
	}


	public Optional<EsAutor> findById(int id) {
		return EsAutorRepo.findById(id);
	}


	public void delete(EsAutor EsAutor) {
		EsAutorRepo.deleteById(EsAutor.getId());

	}

	public void save(@Valid EsAutor EsAutor) {
		EsAutorRepo.save(EsAutor);

	}
}
