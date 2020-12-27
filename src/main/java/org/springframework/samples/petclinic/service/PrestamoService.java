
package org.springframework.samples.petclinic.service;

import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Libro;
import org.springframework.samples.petclinic.model.Miembro;
import org.springframework.samples.petclinic.model.Prestamo;
import org.springframework.samples.petclinic.repository.PrestamoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class PrestamoService {

	@Autowired
	PrestamoRepository PrestamoRepo;

	@Transactional(readOnly = true)
	public Collection<Prestamo> findAll() {
		return PrestamoRepo.findAll();
	}

	@Transactional(readOnly = true)
	public Optional<Prestamo> findById(int id) {
		return PrestamoRepo.findById(id);
	}

	@Transactional
	public int prestamoCount() {
		return (int) PrestamoRepo.count();
	}

	@Transactional
	public void delete(Prestamo Prestamo) {
		PrestamoRepo.deleteById(Prestamo.getId());
	}

	@Transactional
	public void save(@Valid Prestamo Prestamo) {
		PrestamoRepo.save(Prestamo);
	}
	
	@Transactional(readOnly = true)
	public Optional<Prestamo> prestamosDeLibroEnProceso(Miembro miembro, Libro libro) {
		//
		return PrestamoRepo.prestamosDeLibroEnProceso(miembro,libro);
	}

}
