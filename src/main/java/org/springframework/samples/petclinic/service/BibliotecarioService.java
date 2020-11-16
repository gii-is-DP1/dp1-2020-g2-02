package org.springframework.samples.petclinic.service;

import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Bibliotecario;

import org.springframework.samples.petclinic.repository.BibliotecarioRepository;
import org.springframework.stereotype.Service;
@Service
public class BibliotecarioService {
	
	@Autowired
	BibliotecarioRepository BibliotecarioRepo;
	
	public Collection<Bibliotecario> findAll(){
		return BibliotecarioRepo.findAll();
	}


	public Optional<Bibliotecario> findById(int id) {
		return BibliotecarioRepo.findById(id);
	}


	public void delete(Bibliotecario Bibliotecario) {
		BibliotecarioRepo.deleteById(Bibliotecario.getId());

	}

	public void save(@Valid Bibliotecario Bibliotecario) {
		BibliotecarioRepo.save(Bibliotecario);

	}
	
}
