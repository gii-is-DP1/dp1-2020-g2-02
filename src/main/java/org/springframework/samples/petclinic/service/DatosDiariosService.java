package org.springframework.samples.petclinic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.DatosDiarios;
import org.springframework.samples.petclinic.repository.DatosDiariosRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DatosDiariosService {
	
	@Autowired
	private DatosDiariosRepository datosRepo;
	
	@Transactional
	public void save(DatosDiarios datos) {
		datosRepo.save(datos);
	}
	
	@Transactional(readOnly = true)
	public List<DatosDiarios> findAllOrderByFecha() {
		return datosRepo.findAllByOrderByFechaDesc();
	}
}