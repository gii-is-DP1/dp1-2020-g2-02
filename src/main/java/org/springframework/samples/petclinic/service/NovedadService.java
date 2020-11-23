package org.springframework.samples.petclinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Novedad;
import org.springframework.samples.petclinic.repository.NovedadRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NovedadService {
	@Autowired
	private NovedadRepository novedadRepo;
	
	@Transactional
	public int novedadCount() {
		return (int) novedadRepo.count();
	}
	
	@Transactional
	public Iterable<Novedad> findAll() {
		return novedadRepo.findAllByOrderByFechaPublicacionDesc();
	}

	public void save(Novedad novedad) {
		novedadRepo.save(novedad);
	}

}
