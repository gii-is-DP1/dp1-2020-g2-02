package org.springframework.samples.petclinic.service;



import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Genero;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@DataJpaTest(includeFilters= @ComponentScan.Filter(Service.class))
public class GeneroServiceTest {
	
	@Autowired
	private GeneroService generoservice;
	
	@Test
	public void testFindAll() {
		Collection<Genero> generos = generoservice.findAll();
		assertTrue(generos.size()==7);
	}

	@Test
	public void testFindById() {
		Genero genero = generoservice.findById(4).get();
		assertTrue(genero.getNombreGenero().equals("Aventura"));
	}

	@Test
	@Transactional
	public void testAddGenero() {
		Collection<Genero> generos = generoservice.findAll();
		Genero genero = new Genero();
		genero.setNombreGenero("Aventuras");
		generoservice.save(genero);
		assertTrue(generos.size()+1==generoservice.findAll().size());
	}
	
}
