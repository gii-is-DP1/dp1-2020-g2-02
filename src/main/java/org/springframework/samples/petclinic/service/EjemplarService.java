package org.springframework.samples.petclinic.service;

import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Disponibilidad;
import org.springframework.samples.petclinic.model.Ejemplar;
import org.springframework.samples.petclinic.model.Libro;
import org.springframework.samples.petclinic.repository.EjemplarRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EjemplarService {
	@Autowired
	EjemplarRepository EjemplarRepo;
	
	@Transactional(readOnly=true)
	public Collection<Ejemplar> findAll(){
		return EjemplarRepo.findAll();
	}

	@Transactional(readOnly=true)
	public Optional<Ejemplar> findById(int id) {
		return EjemplarRepo.findById(id);
	}
	
	@Transactional
	public int ejemplarCount() {
		return (int) EjemplarRepo.count();
	}

	@Transactional
	public void delete(Ejemplar Ejemplar) {
		EjemplarRepo.deleteById(Ejemplar.getId());

	}
	
	@Transactional
	public void save(@Valid Ejemplar Ejemplar) {
		EjemplarRepo.save(Ejemplar);

	}

	public Collection<Ejemplar> findDisponibles(Libro libro) {
		// TODO Auto-generated method stub
		return EjemplarRepo.findAllByLibroAndDisponibilidad(libro,Disponibilidad.DISPONIBLE);
	}

}
