package org.springframework.samples.petclinic.service;

import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Miembro;

import org.springframework.samples.petclinic.repository.MiembroRepository;
import org.springframework.stereotype.Service;
@Service
public class MiembroService {
	
	@Autowired
	MiembroRepository MiembroRepo;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthoritiesService authoritiesService;
	
	public Collection<Miembro> findAll(){
		return MiembroRepo.findAll();
	}


	public Optional<Miembro> findById(int id) {
		return MiembroRepo.findById(id);
	}


	public void delete(Miembro Miembro) {
		MiembroRepo.deleteById(Miembro.getId());

	}

	public void save(@Valid Miembro miembro) {
		MiembroRepo.save(miembro);
		
		userService.saveUser(miembro.getUser());
		
		authoritiesService.saveAuthorities(miembro.getUser().getUsername(), "miembro");

	}
	
}
