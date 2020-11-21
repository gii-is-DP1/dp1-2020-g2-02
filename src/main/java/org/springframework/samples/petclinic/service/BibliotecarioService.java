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
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthoritiesService authoritiesService;
	
	public Collection<Bibliotecario> findAll(){
		return BibliotecarioRepo.findAll();
	}


	public Optional<Bibliotecario> findById(int id) {
		return BibliotecarioRepo.findById(id);
	}


	public void delete(Bibliotecario Bibliotecario) {
		BibliotecarioRepo.deleteById(Bibliotecario.getId());

	}

	public void save(@Valid Bibliotecario bibliotecario) {
		BibliotecarioRepo.save(bibliotecario);
		
		userService.saveUser(bibliotecario.getUser());
		
		authoritiesService.saveAuthorities(bibliotecario.getUser().getUsername(), "bibliotecario");

	}
	
}
