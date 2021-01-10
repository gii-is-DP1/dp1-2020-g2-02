package org.springframework.samples.petclinic.service;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Encargo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class EncargoServiceTest {
	
	@Autowired
	private EncargoService encargoService;
		
	@Autowired
	private ProveedorService proveedorService;
	
	@Test
	public void testCountWithInitialData() {
		int count=encargoService.encargoCount();
		assertTrue(count==2);
	}
	
	@Test
	public void testFindAll() {
		Iterable<Encargo> encargos = encargoService.findAll();
		assertTrue(encargos.iterator().next().getFechaRealizacion().equals(LocalDate.of(2020, 11, 12)));
	}
	
	@Test
	public void testFindById() {
		Encargo encargo = encargoService.findById(0).get();
		assertTrue(encargo.getFechaRealizacion().equals(LocalDate.of(2020, 11, 12)));
	}
	
	@Test
	@Transactional
	public void testAddEncargo() {
		int cuentaInicial = encargoService.encargoCount();
		Encargo encargo = new Encargo();
		encargo.setId(cuentaInicial);
		encargo.setFechaEntrega(LocalDate.of(2020, 12, 31));
		encargo.setFechaRealizacion(LocalDate.of(2021, 1, 6));
		encargo.setProveedor(proveedorService.findById(0).get());
		encargoService.save(encargo);
		int cuentaFinal = encargoService.encargoCount();
		assertTrue(cuentaFinal == cuentaInicial + 1);
	}
}
