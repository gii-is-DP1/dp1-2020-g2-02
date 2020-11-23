<<<<<<< HEAD
package org.springframework.samples.petclinic.service;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Bibliotecario;
import org.springframework.samples.petclinic.model.Novedad;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class NovedadServiceTest {
	@Autowired
	private NovedadService novedadService;
	
	@Autowired
	private BibliotecarioService bibliotecarioService;
	@Test
	public void testCountWithInitialData() {
		int count=novedadService.novedadCount();
		assertTrue(count==2);
	}
	@Test
	@Transactional
	public void testFindAll() {
		Iterable<Novedad> novedades = novedadService.findAll();
		assertTrue(novedades.iterator().next().getTitulo().equals("Nuevos ejemplares"));
	}
	
	@Test
	public void testAddNovedad() {
		Novedad novedad = new Novedad();
		novedad.setTitulo("Test");
		novedad.setContenido("Novedad de test");
		novedad.setFechaPublicacion(LocalDate.now());
		Bibliotecario bibliotecario = bibliotecarioService.findById(1).get();
		novedad.setBibliotecario(bibliotecario);
		novedadService.save(novedad);
		int count=novedadService.novedadCount();
		assertTrue(count==3);
	}
}
=======
package org.springframework.samples.petclinic.service;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Bibliotecario;
import org.springframework.samples.petclinic.model.Novedad;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class NovedadServiceTest {
	@Autowired
	private NovedadService novedadService;
	
	@Autowired
	private BibliotecarioService bibliotecarioService;
	@Test
	public void testCountWithInitialData() {
		int count=novedadService.novedadCount();
		assertTrue(count==2);
	}
	@Test
	@Transactional
	public void testFindAll() {
		Iterable<Novedad> novedades = novedadService.findAll();
		assertTrue(novedades.iterator().next().getTitulo().equals("Nuevos ejemplares"));
	}
	
	@Test
	public void testAddNovedad() {
		Novedad novedad = new Novedad();
		novedad.setTitulo("Test");
		novedad.setContenido("Novedad de test");
		novedad.setFechaPublicacion(LocalDate.now());
		Bibliotecario bibliotecario = bibliotecarioService.findById(1).get();
		novedad.setBibliotecario(bibliotecario);
		novedadService.save(novedad);
		int count=novedadService.novedadCount();
		assertTrue(count==3);
	}
}
>>>>>>> parent of af356ce... Añadido autores
