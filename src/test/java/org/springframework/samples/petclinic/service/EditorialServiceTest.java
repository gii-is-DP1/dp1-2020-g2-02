package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Editorial;
import org.springframework.samples.petclinic.model.Libro;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class EditorialServiceTest {

	@Autowired
	private EditorialService editorialService;
	
	@Test
	public void testFindById() {
		String nombre = editorialService.findById(1).get().getNombre();
		assertTrue(nombre.equals("Norma"));
	}
	
	@Test
	public void testFindAll() {
		Collection<Editorial> editoriales = editorialService.findAll();
		assertTrue(editoriales.size()==2);
	}
	
	@Test
	public void getLibrosEditorial() {
		Collection<Libro> libros = editorialService.getLibrosEditorial(editorialService.findById(1).get());
		assertTrue(libros.size()==2);
	}
	
	@Test
	@Transactional
	public void testAddEditorial() {
		int cuentaInicial = editorialService.findAll().size();
		Editorial editorial = new Editorial();
		editorial.setNombre("prueba");
		editorial.setDireccion("prueba");
		editorial.setEmail("a@a.com");
		editorial.setNif("A1234567A");
		editorial.setTelefono("123123123");
		editorial.setWeb("www.a.com");
		editorialService.save(editorial);
		assertThat(editorialService.findAll().size()).isEqualTo(cuentaInicial+1);
	}
}
