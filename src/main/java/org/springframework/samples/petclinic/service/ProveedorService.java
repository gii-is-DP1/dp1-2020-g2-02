package org.springframework.samples.petclinic.service;

import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Proveedor;
import org.springframework.samples.petclinic.repository.ProveedorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProveedorService {
	
	@Autowired
	ProveedorRepository ProveedorRepo;
	
	
	
	@Transactional(readOnly = true)
	public Collection<Proveedor> findAll(){
		return ProveedorRepo.findAll();
	}
	
	@Transactional
	public int proveedorCount() {
		return (int) ProveedorRepo.count();
	}
	
	@Transactional(readOnly = true)
	public Optional<Proveedor> findById(int id) {
		return ProveedorRepo.findById(id);
	}
	
	public void save(@Valid Proveedor proveedor) {
		ProveedorRepo.save(proveedor);
		
	}
	
	
	

}
