package org.springframework.samples.petclinic.service;

import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cantidad;
import org.springframework.samples.petclinic.repository.CantidadRepository;
import org.springframework.stereotype.Service;

@Service
public class CantidadService {

	@Autowired
	private CantidadRepository CantidadRepo;
	
	public Collection<Cantidad> findAll(){
		return CantidadRepo.findAll();
	}


	public Optional<Cantidad> findById(int id) {
		return CantidadRepo.findById(id);
	}

	public void save(@Valid Cantidad Cantidad) {
		CantidadRepo.save(Cantidad);

	}
}
