package org.springframework.samples.petclinic.service;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Bibliotecario;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class BibliotecarioServiceTest {
	@Autowired
	private BibliotecarioService bibliotecarioService;
	
	@Test
	public void testCountWithInitialData() {
		int count=bibliotecarioService.bibliotecarioCount();
		assertTrue(count==2);
	}
	
	@Test
	public void testFindAll() {
		Iterable<Bibliotecario> bibliotecarios = bibliotecarioService.findAll();
		assertTrue(bibliotecarios.iterator().next().getNombre().equals("Fernando"));	
	}
	
	@Test
	public void testFindById() {
		Bibliotecario bibliotecario = bibliotecarioService.findById(0).get();
		assertTrue(bibliotecario.getNombre().equals("Fernando"));
	}
	
	@Test
	@Transactional
	public void testAddBibliotecario() {
		Bibliotecario bibliotecario = new Bibliotecario();
		bibliotecario.setNombre("Alejandro");;
		bibliotecario.setApellidos("Ruiz Costa");
		bibliotecario.setDni("49387456P");
		bibliotecario.setTelefono(650606444);
		bibliotecario.setEmail("ruco@gmail.com");
		bibliotecarioService.save(bibliotecario);
		int count=bibliotecarioService.bibliotecarioCount();
		assertTrue(count==3);
	}
	
	
	
}
