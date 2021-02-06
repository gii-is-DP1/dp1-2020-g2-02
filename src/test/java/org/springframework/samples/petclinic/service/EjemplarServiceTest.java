package org.springframework.samples.petclinic.service;

import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Disponibilidad;
import org.springframework.samples.petclinic.model.Ejemplar;
import org.springframework.samples.petclinic.model.Libro;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class EjemplarServiceTest {
	
	@Autowired
	private EjemplarService ejemplarService;
	
	@Autowired
	private LibroService libroService;
	
	
	@Test
	public void testFindAll() {
		Collection<Ejemplar> ejemplares = ejemplarService.findAll();
		assertTrue(ejemplares.size()==9);
	}
	
	@Test
	public void testFindDisponibles() {
		Collection<Ejemplar> ejemplaresDisponibles = ejemplarService.findDisponibles(libroService.findById(1).get());
		assertTrue(ejemplaresDisponibles.size() == 2);
	}
	
	@Test
	public void testFindById() {
		Ejemplar ejemplar = ejemplarService.findById(2).get();
		assertTrue(ejemplar.getEstado().equals("Cubierta doblada."));
	}
	
	@Test
	public void testFindDisponibles() {		
		int disponibles = 0;
		Collection<Libro> libros = libroService.findAll();
		for(Libro libro:libros) {
			Collection<Ejemplar> ejemplares = ejemplarService.findDisponibles(libro);
			int count = ejemplares.size();
			disponibles = disponibles+count;
		}
		assertTrue(disponibles==2);
		
	}
	
	@Test
	public void testCountWithInitialData() {
		int count=ejemplarService.ejemplarCount();
		assertTrue(count==9);
	}
	
	@Test
	@Transactional
	public void testAddEjemplar() {
		int countinicial=ejemplarService.ejemplarCount();
		Ejemplar ejemplar = new Ejemplar();
		Libro libro = libroService.findById(1).get();
		ejemplar.setLibro(libro);
		ejemplar.setDisponibilidad(Disponibilidad.DISPONIBLE);
		ejemplar.setEstado("Bien");
		ejemplarService.save(ejemplar);
		int count=ejemplarService.ejemplarCount();
		assertTrue(count==countinicial+1);
	}
	

}
