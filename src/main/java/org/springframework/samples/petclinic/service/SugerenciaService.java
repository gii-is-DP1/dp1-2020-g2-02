package org.springframework.samples.petclinic.service;

import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Sugerencia;
import org.springframework.samples.petclinic.repository.SugerenciaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SugerenciaService {

	@Autowired
	SugerenciaRepository sugerenciaRepository;
	
	@Transactional
	public int sugerenciaCount() {
		return (int) sugerenciaRepository.count();
	}
	
	@Transactional
	public Collection<Sugerencia> findAllOrderByTituloLibro() {
		return sugerenciaRepository.findAllOrderByTituloLibro();
	}
	
	@Transactional
	public Optional<Sugerencia> findById(int id) {
		return sugerenciaRepository.findById(id);
	}

	@Transactional
	public void delete(Sugerencia sugerencia) {
		sugerenciaRepository.deleteById(sugerencia.getId());
	}
	
	@Transactional
	public void save(@Valid Sugerencia sugerencia) {
		sugerenciaRepository.save(sugerencia);
	}
}
