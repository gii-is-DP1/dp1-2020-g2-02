package org.springframework.samples.petclinic.service;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.DatosDiarios;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class DatosDiariosServiceTests {
	
	@Autowired
	DatosDiariosService datosService;
	
	@Test
	public void testCountWithInitialData() {
		int cuentaInicial = datosService.DatosDiariosCount();
		assertTrue(cuentaInicial==1);
	}
	
	@Test
	public void testFindAllOrderByFecha() {
		int cuentaInicial = datosService.DatosDiariosCount();
		List<DatosDiarios> datosDiarios = datosService.findAllOrderByFecha();
		assertTrue(cuentaInicial == datosDiarios.size());
	}
	
	@Test
	public void testGuardarDatosDiarios() {
		int cuentaInicial = datosService.DatosDiariosCount();
		
		DatosDiarios datosDiarios = new DatosDiarios();
		datosDiarios.setId(2);
		datosDiarios.setFecha(LocalDate.of(2020, 3, 6));
		datosDiarios.setEncargos(5);
		datosDiarios.setNovedades(6);
		datosDiarios.setPrestamos(3);
		datosService.save(datosDiarios);
		
		int cuentaFinal = datosService.DatosDiariosCount();
		assertTrue(cuentaFinal == cuentaInicial + 1);
	}

}
