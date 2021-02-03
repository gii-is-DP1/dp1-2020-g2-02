package org.springframework.samples.petclinic.service;

import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Cantidad;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class CantidadServiceTest {

	@Autowired
	private CantidadService cantidadService;
	
	@Autowired
	private EncargoService encargoService;

	@Autowired
	private LibroService libroService;
	
	@Test
	public void testFindAll() {
		Collection<Cantidad> cantidades = cantidadService.findAll();
		assertTrue(cantidades.size()==3);
	}
	
	@Test
	public void testById() {
		Cantidad cantidad = cantidadService.findById(0).get();
		assertTrue(cantidad.getUnidades()==5);
	}
	
	@Test
	@Transactional
	public void testAddCantidad() {
		int cuentaInicial = cantidadService.findAll().size();
		Cantidad cantidad = new Cantidad();
		cantidad.setId(3);
		cantidad.setUnidades(5);
		cantidad.setPrecioUnitario(10.0);
		cantidad.setEncargo(encargoService.findById(0).get());
		cantidad.setLibro(libroService.findById(1).get());
		cantidadService.save(cantidad);
		assertTrue(cuentaInicial + 1 == cantidadService.findAll().size());
	}
}
