package org.springframework.samples.petclinic.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Sugerencia;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@DataJpaTest(includeFilters= @ComponentScan.Filter(Service.class))
public class SugerenciaServiceTest {
	
	@Autowired
	private SugerenciaService sugerenciaService;
	
	
	@Test
	public void testFindAll() {
		Collection<Sugerencia> generos = sugerenciaService.findAllOrderByTituloLibro();
		assertTrue(generos.size()==3);
	}
	
	
	@Test
	public void testFindById() {
		Sugerencia sugerencia = sugerenciaService.findById(0).get();
		assertTrue(sugerencia.getTituloLibro().equals("Abdel"));
	}
	
	
	@Test
	@Transactional
	public void testAddGenero() {
		Collection<Sugerencia> sugerencias = sugerenciaService.findAllOrderByTituloLibro();
		Sugerencia sugerencia = new Sugerencia();
		sugerencia.setNombreAutor("Brandon Sanderson");
		sugerenciaService.save(sugerencia);
		assertTrue(sugerencias.size()+1==sugerenciaService.findAllOrderByTituloLibro().size());
	}

}
