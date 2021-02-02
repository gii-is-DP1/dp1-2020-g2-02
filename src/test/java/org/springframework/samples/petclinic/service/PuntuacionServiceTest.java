package org.springframework.samples.petclinic.service;

import static org.junit.Assert.assertTrue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Puntuacion;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters= @ComponentScan.Filter(Service.class))
public class PuntuacionServiceTest {

	@Autowired
	private PuntuacionService puntuacionservice;
	
	
	public void testFindAll() {
		Iterable<Puntuacion> puntuaciones = puntuacionservice.findAll();
		assertTrue(puntuaciones.iterator().next().getPuntuacion()>3);
	}
	
	public void testFindById() {
		Puntuacion puntuacion = puntuacionservice.findById(2).get();
		assertTrue(puntuacion.getPuntuacion().equals(2));
	}
}
