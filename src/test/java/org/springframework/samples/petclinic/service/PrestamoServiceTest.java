package org.springframework.samples.petclinic.service;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Bibliotecario;
import org.springframework.samples.petclinic.model.Ejemplar;
import org.springframework.samples.petclinic.model.Miembro;
import org.springframework.samples.petclinic.model.Prestamo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class PrestamoServiceTest {
	@Autowired
	private PrestamoService prestamoService;

	@Autowired
	private BibliotecarioService bibliotecarioService;

	@Autowired
	private MiembroService miembroService;

	@Autowired
	private EjemplarService ejemplarService;

	@Test
	public void testCountWithInitialData() {
		int count = prestamoService.prestamoCount();
		assertTrue(count == 2);
	}

	@Test
	public void testFindAll() {
		Iterable<Prestamo> prestamos = prestamoService.findAll();
		assertTrue(prestamos.iterator().next().getEjemplar().equals(1));
	}

	@Test
	public void testFindById() {
		Prestamo prestamo = prestamoService.findById(0).get();
		assertTrue(prestamo.getEjemplar().equals(1));
	}

	@Test
	@Transactional
	public void testAddPrestamo() {
		Prestamo prestamo = new Prestamo();
		prestamo.setFechaPrestamo(LocalDate.now());
		prestamo.setFechaDevolucion(LocalDate.now().plusWeeks(2));
		Bibliotecario bibliotecario = bibliotecarioService.findById(1).get();
		prestamo.setBibliotecario(bibliotecario);
		Miembro miembro = miembroService.findById(1).get();
		prestamo.setMiembro(miembro);
		Ejemplar ejemplar = ejemplarService.findById(1).get();
		prestamo.setEjemplar(ejemplar);
		prestamo.setBibliotecario(bibliotecario);
		prestamoService.save(prestamo);
		int count = prestamoService.prestamoCount();
		assertTrue(count == 3);
	}

}
