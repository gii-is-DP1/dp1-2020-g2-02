package org.springframework.samples.petclinic.service;

import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Encargo;
import org.springframework.samples.petclinic.repository.EncargoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EncargoService {

	@Autowired
	EncargoRepository encargoRepo;

//	@Autowired
//	LibroService libroService;

//	@Autowired
//	ProveedorService proveedorService;

//	@Autowired
//	CantidadService cantidadService;
	@Transactional
	public int encargoCount() {
		return (int) encargoRepo.count();
	}

	@Transactional
	public Collection<Encargo> findAll() {
		return encargoRepo.findAll();
	}

	@Transactional
	public Optional<Encargo> findById(int id) {
		return encargoRepo.findById(id);
	}

	@Transactional
	public void delete(Encargo encargo) {
		encargoRepo.deleteById(encargo.getId());

	}

	@Transactional
	public void save(@Valid Encargo encargo) {
		encargoRepo.save(encargo);
	}
}
