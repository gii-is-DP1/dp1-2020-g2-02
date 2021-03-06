package org.springframework.samples.petclinic.service;

import static org.junit.Assert.assertTrue;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Miembro;
import org.springframework.samples.petclinic.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class MiembroServiceTest {

	@Autowired
	private MiembroService miembroService;
	
	@Autowired
	private UserService  userService;
	
	@Test
	public void testCountWithInitialData() {
		int count=miembroService.miembroCount();
		assertTrue(count==4);
	}
	
	@Test
	public void testFindAll() {
		Iterable<Miembro> miembros= miembroService.findAll();
		assertTrue(miembros.iterator().next().getNombre().equals("Jorge"));
	}
	
	@Test
	public void testFindById() {
		Miembro miembro = miembroService.findById(0).get();
		assertTrue(miembro.getNombre().equals("Jorge"));
	}
	
	
	@Test
	public void testFindByUser() {
		User user = userService.findUser("jorgon1").get();
		Miembro miembro = miembroService.findByUser(user);
		assertTrue(miembro.getUser().equals(user));
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
		miembro.setTelefono("661524384");

		User user = new User();
		user.setUsername("antlop1");
		user.setPassword("Pass1234");
		user.setEnabled(true);
		miembro.setUser(user);
		userService.saveUser(user);
		miembroService.save(miembro);
		
		int cuentaFinal = miembroService.miembroCount();
		assertTrue(cuentaFinal-cuentaInicial== 1);
	}
}
