package org.springframework.samples.petclinic.service;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Autor;
import org.springframework.samples.petclinic.model.Genero;
import org.springframework.samples.petclinic.model.Libro;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class LibroServiceTest {

	@Autowired
	private LibroService libroService;

	@Test
	public void testGetGenerosLibro() {
		Collection<Genero> generos = libroService.findById(1).get().getGeneros();
		assertTrue(generos.size()==2);
	}
	
	@Test
	public void testGetAutoresLibro() {
		Collection<Autor> autores = libroService.findById(2).get().getAutores();
		assertTrue(autores.size()==4);
	}
	
	@Test
	public void testFindAll() {
		Collection<Libro> libros = libroService.findAll();
		assertTrue(libros.size()==4);
	}
	
	@Test
	public void testFindById() {
		Libro libro = libroService.findById(1).get();
		assertTrue(libro.getTitulo().equals("El adversario")); 
	}
	
	@Test
	@Transactional
	public void testAddLibro() {
		Libro libro = new Libro();
		libro.setISBN("1234567890");
		libro.setIdioma("Inglés");
		libro.setTitulo("TítuloPrueba");
		libro.setFecha_publicacion(LocalDate.now());
		libroService.save(libro);
		Collection<Libro> libros = libroService.findAll();
		assertTrue(libros.size()==5);
	}
	
	@Test
	public void testGetNotaMedia() {
		Libro l = libroService.findById(2).get();
		assertTrue(libroService.getNotaMedia(l)==4);
	}
	
}
