package org.springframework.samples.petclinic.service;

import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Puntuacion;
import org.springframework.samples.petclinic.service.exceptions.LibroNoPrestadoAnteriormenteException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters= @ComponentScan.Filter(Service.class))
public class PuntuacionServiceTest {

	@Autowired
	private PuntuacionService puntuacionservice;

	@Autowired
	private LibroService libroService;

	@Autowired
	private MiembroService miembroService;
	
	
	@Test
	public void testFindAll() {
		Iterable<Puntuacion> puntuaciones = puntuacionservice.findAll();
		assertTrue(puntuaciones.iterator().next().getPuntaje()>3);
	}
	
	@Test
	public void testFindById() {
		Puntuacion puntuacion = puntuacionservice.findById(2).get();
		assertTrue(puntuacion.getPuntaje().equals(2));
	}
	
	@Test
	@Transactional
	public void testAddPuntuacion() {
		Puntuacion p = new Puntuacion();
		p.setLibro(libroService.findById(3).get());
		p.setMiembro(miembroService.findById(3).get());
		p.setPuntaje(4);
		try {
			puntuacionservice.savePuntuacion(p);
		} catch (LibroNoPrestadoAnteriormenteException e) {
			e.printStackTrace();
		}
		Collection<Puntuacion> puntuaciones = puntuacionservice.findAll();
		assertTrue(puntuaciones.size()==5);
	}
	
	@Test
	@Transactional
	public void testAddPuntuacionLibroNoLeido() {
		Puntuacion p = new Puntuacion();
		p.setLibro(libroService.findById(3).get());
		p.setMiembro(miembroService.findById(0).get());
		p.setPuntaje(4);
		try {
			puntuacionservice.savePuntuacion(p);
		} catch (LibroNoPrestadoAnteriormenteException e) {
			e.printStackTrace();
		}
		Collection<Puntuacion> puntuaciones = puntuacionservice.findAll();
		assertTrue(puntuaciones.size()==4);
	}
}
