
package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Bibliotecario;
import org.springframework.samples.petclinic.model.Novedad;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class NovedadServiceTest {
	@Autowired
	private NovedadService novedadService;
	
	@Autowired
	private BibliotecarioService bibliotecarioService;
	@Test
	public void testCountWithInitialData() {
		int count=novedadService.novedadCount();
		assertThat(count).isEqualTo(2);
	}
	@Test
	public void testFindAll() {
		Iterable<Novedad> novedades = novedadService.findAll();
		assertThat(novedades.iterator().next().getTitulo()).isEqualTo("Nuevos ejemplares");
	}
	
	@Test
	public void testFindById() {
		Novedad novedad = novedadService.findById(0).get();
		assertThat(novedad.getTitulo()).isEqualTo("Nuevos ejemplares");
	}
	
	@Test
	public void testFindNovedadesHoy() {
		Collection<Novedad> novedades = novedadService.findNovedadesHoy();
		assertThat(novedades.size()).isEqualTo(1);
	}
	
	@Test
	@Transactional
	public void testAddNovedad() {
		Novedad novedad = new Novedad();
		novedad.setTitulo("Test");
		novedad.setContenido("Novedad de test");
		novedad.setFechaPublicacion(LocalDate.now());
		Bibliotecario bibliotecario = bibliotecarioService.findById(1).get();
		novedad.setBibliotecario(bibliotecario);
		novedadService.save(novedad);
		int count=novedadService.novedadCount();
		assertThat(count).isEqualTo(3);
	}
	
	
}
