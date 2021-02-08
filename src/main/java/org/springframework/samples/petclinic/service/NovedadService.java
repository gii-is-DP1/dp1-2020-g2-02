package org.springframework.samples.petclinic.service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Novedad;
import org.springframework.samples.petclinic.repository.NovedadRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NovedadService {
	@Autowired
	private NovedadRepository novedadRepo;
	
	@Transactional(readOnly = true)
	public int novedadCount() {
		return (int) novedadRepo.count();
	}
	
	@Transactional(readOnly = true)
	public Iterable<Novedad> findAll() {
		return novedadRepo.findAllByOrderByFechaPublicacionDesc();
	}

	@Transactional
	public void save(Novedad novedad) {
		novedadRepo.save(novedad);
	}

	@Transactional(readOnly = true)
	public Optional<Novedad> findById(int id) {
		return novedadRepo.findById(id);
	}
	
	@Transactional(readOnly = true)
	public Collection<Novedad> findNovedadesHoy() {
		return novedadRepo.findByFechaPublicacion(LocalDate.now());
	}

}