package org.springframework.samples.petclinic.service;

import static org.junit.Assert.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Autor;
import org.springframework.samples.petclinic.model.Libro;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class AutorServiceTest {

	@Autowired
	private AutorService autorService;


	@Test
	@Transactional
	public void testAddAutor() {
		int cuentaInicial = autorService.autorCount();
		Autor autor = new Autor();
		autor.setNombre("nombreTest");
		autor.setApellidos("apellidosTest");
		autor.setFecha_nac(LocalDate.now());
		autorService.save(autor);
		Collection<Autor> autores = autorService.findAll();
		assertThat(autores.size()).isEqualTo(cuentaInicial+1);
	}
	
	@Test
	public void testFindById() {
		String nombre = autorService.findById(1).get().getNombre();
		assertThat(nombre).isEqualTo("Emmanuel");
	}
	
	@Test
	public void testFindAll() {
		int cuentaInicial = autorService.autorCount();
		Collection<Autor> autores = autorService.findAll();
		assertThat(autores.size()).isEqualTo(cuentaInicial);
	}
	
	@Test
	public void testGetLibrosAutor() {
		Collection<Libro> libros = autorService.findById(1).get().getLibros();
		assertThat(libros.size()).isEqualTo(1);
	}
}
