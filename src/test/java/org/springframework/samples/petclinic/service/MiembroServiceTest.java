package org.springframework.samples.petclinic.service;

import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Authorities;
import org.springframework.samples.petclinic.model.Miembro;
import org.springframework.samples.petclinic.model.User;
import org.springframework.transaction.annotation.Transactional;

public class MiembroServiceTest {

	@Autowired
	private MiembroService miembroService;
	
	@Autowired
	private UserService  userService;
	
	@Autowired
	private AuthoritiesService authoritiesService;
	@Test
	public void testCountWithInitialData() {
		int count=miembroService.miembroCount();
		assertTrue(count==2);
	}
	
	@Test
	public void testFindAll() {
		Iterable<Miembro> miembros= miembroService.findAll();
		assertTrue(miembros.iterator().next().getNombre().equals("Raúl"));
	}
	
	@Test
	public void testFindById() {
		Miembro miembro = miembroService.findById(0).get();
		assertTrue(miembro.getNombre().equals("Jorge"));
	}
	
	@Test
	public void testDelete(@Valid Miembro miembro) {
		int cuentaInicial = miembroService.miembroCount();
		miembroService.delete(miembro);
		int cuentaFinal = miembroService.miembroCount();
		assertTrue(cuentaInicial-cuentaFinal == 1);
	}
	
	@Test
	@Transactional
	public void testAddMiembro() {
		int cuentaInicial = miembroService.miembroCount();
		Miembro miembro = new Miembro();
		miembro.setApellidos("López Gutiérrez");
		miembro.setDni("49093968P");
		miembro.setEmail("antlop@gmail.com");
		miembro.setId(cuentaInicial);
		miembro.setNombre("Antonio");
		miembro.setTelefono(661524384);
		
		int cuentaInicialAuthorities = authoritiesService.authorityCount();
		User user = new User();
		user.setUsername("antlop1");
		user.setPassword("Pass1234");
		
		Authorities autoridad = new Authorities();
		autoridad.setAuthority("miembro");
		autoridad.setId(cuentaInicialAuthorities);
		autoridad.setUser(user);
		Set<Authorities> autoridadesSet  = new HashSet<Authorities>();
		autoridadesSet.add(autoridad);
		authoritiesService.saveAuthorities(autoridad);
		
		user.setAuthorities(autoridadesSet);
		userService.saveUser(user);
		
		
		miembro.setUser(user);
		miembroService.save(miembro);
		
		int cuentaFinal = miembroService.miembroCount();
		assertTrue(cuentaFinal-cuentaInicial== 1);
	}
}
