package org.springframework.samples.petclinic.service;

import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Bibliotecario;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.repository.BibliotecarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
	
	@Transactional
	public int bibliotecarioCount() {
		return (int) BibliotecarioRepo.count();
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

	public Bibliotecario findByUser(User user) {
		// TODO Auto-generated method stub
		return BibliotecarioRepo.findByUser(user);
	}
	
}
