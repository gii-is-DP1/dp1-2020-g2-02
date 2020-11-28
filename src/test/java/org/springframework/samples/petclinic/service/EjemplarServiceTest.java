package org.springframework.samples.petclinic.service;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

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
	public void testCountWithInitialData() {
		int count=ejemplarService.ejemplarCount();
		assertTrue(count==3);
	}
	
	@Test
	@Transactional
	public void testAddEjemplar() {
		Ejemplar ejemplar = new Ejemplar();
		Libro libro = libroService.findById(1).get();
		ejemplar.setLibro(libro);
		ejemplar.setDisponibilidad(Disponibilidad.DISPONIBLE);
		ejemplar.setEstado("Bien");
		ejemplarService.save(ejemplar);
		int count=ejemplarService.ejemplarCount();
		assertTrue(count==4);
	}
	

}
