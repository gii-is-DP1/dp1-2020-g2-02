package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Bibliotecario;
import org.springframework.samples.petclinic.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class BibliotecarioServiceTests {
	@Autowired
	private BibliotecarioService bibliotecarioService;
	
	@Autowired
	private UserService  userService;
	
	
	@Test
	public void testCountWithInitialData() {
		int count=bibliotecarioService.bibliotecarioCount();
		assertTrue(count==2);
	}
	
	@Test
	public void testFindAll() {
		int cuentaInicial = bibliotecarioService.bibliotecarioCount();
		Collection<Bibliotecario> bibliotecarios = bibliotecarioService.findAll();
		assertThat(bibliotecarios.size()).isEqualTo(cuentaInicial);	
	}
	
	@Test
	public void testFindById() {
		Bibliotecario bibliotecario = bibliotecarioService.findById(0).get();
		String nombre = bibliotecario.getNombre();
		assertThat(nombre).isEqualTo("Fernando");
	}
	
	@Test
	public void testFindByUser() {
		User user = userService.findUser("ferror1").get();
		Bibliotecario bibliotecario = bibliotecarioService.findByUser(user);
		assertTrue(bibliotecario.getUser().equals(user));
	}
	
	@Test
	@Transactional
	public void testAddBibliotecario() {
		int cuentaInicial = bibliotecarioService.bibliotecarioCount();
		Bibliotecario bibliotecario = new Bibliotecario();
		bibliotecario.setId(cuentaInicial);
		bibliotecario.setNombre("Alejandro");;
		bibliotecario.setApellidos("Ruiz Costa");
		bibliotecario.setDni("49387456P");
		bibliotecario.setTelefono("650606444");
		bibliotecario.setEmail("ruco@gmail.com");
		
		User user = new User();
		user.setUsername("aleruco");
		user.setPassword("Pass1234");
		user.setEnabled(true);
		bibliotecario.setUser(user);
		userService.saveUser(user);
		bibliotecarioService.save(bibliotecario);
		Collection<Bibliotecario> bibliotecarios = bibliotecarioService.findAll();
		assertThat(bibliotecarios.size()).isEqualTo(cuentaInicial+1);
	}
	
	
	
	
}
