package org.springframework.samples.petclinic.service;



import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Genero;
import org.springframework.stereotype.Service;



@DataJpaTest(includeFilters= @ComponentScan.Filter(Service.class))
public class GeneroServiceTest {
	
	@Autowired
	private GeneroService generoservice;
	
	public void testFindAll() {
		Iterable<Genero> generos = generoservice.findAll();
		assertTrue(generos.iterator().next().getGenero().equals("Fantasía"));
	}
	
	public void testFindById() {
		Genero genero = generoservice.findById(2).get();
		assertTrue(genero.getGenero().equals("Fantasía"));
	}
	
	public void testAddGenero() {
		Genero genero = new Genero();
		genero.setId(3);
		genero.setGenero("Aventuras");
		generoservice.save(genero);
		Collection<Genero> generos = generoservice.findAll();
		assertTrue(generos.size()==4);
	}


	
	
	
}
