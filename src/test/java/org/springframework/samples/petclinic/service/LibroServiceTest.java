package org.springframework.samples.petclinic.service;

import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Genero;
import org.springframework.samples.petclinic.model.Libro;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class LibroServiceTest {

	@Autowired
	private LibroService libroService;

	@Test
	private void testGetGenerosLibro() {
		Collection<Genero> generos = libroService.getGenerosLibro(libroService.findById(1).get());
		assertTrue(generos.size()==2);
	}
	
	@Test
	public void testFindAll() {
		Iterable<Libro> libros = libroService.findAll();
		assertTrue(libros.iterator().next().getTitulo().equals("El adversario"));
	}
	
	@Test
	private void testFindById() {
		Libro libro = libroService.findById(1).get();
		assertTrue(libro.getTitulo().equals("El adversario")); 
	}
	
}
