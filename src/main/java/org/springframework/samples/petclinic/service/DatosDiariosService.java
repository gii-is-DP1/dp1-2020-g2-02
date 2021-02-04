package org.springframework.samples.petclinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.DatosDiarios;
import org.springframework.samples.petclinic.repository.DatosDiariosRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DatosDiariosService {
	
	@Autowired
	private DatosDiariosRepository datosRepo;
	
	@Transactional(readOnly = true)
	public Iterable<DatosDiarios> findAll() {
		return datosRepo.findAll();
	}

	@Transactional
	public void save(DatosDiarios datos) {
		datosRepo.save(datos);
	}
}