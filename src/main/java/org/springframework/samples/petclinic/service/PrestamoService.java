
package org.springframework.samples.petclinic.service;

import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Prestamo;
import org.springframework.samples.petclinic.repository.PrestamoRepository;

public class PrestamoService {

	@Autowired
	PrestamoRepository PrestamoRepo;
	
	public Collection<Prestamo> findAll(){
		return PrestamoRepo.findAll();
	}
	
	public Optional<Prestamo> findById(int id){
		return PrestamoRepo.findById(id);
	}
	
	public void delete(Prestamo Prestamo) {
		PrestamoRepo.deleteById(Prestamo.getId());
	}
	
	public void save (@Valid Prestamo Prestamo) {
		PrestamoRepo.save(Prestamo);
	}
	
	
}
