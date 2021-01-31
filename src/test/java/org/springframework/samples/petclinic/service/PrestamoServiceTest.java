package org.springframework.samples.petclinic.service;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Miembro;
import org.springframework.samples.petclinic.model.Prestamo;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.service.exceptions.LibroNoDisponibleException;
import org.springframework.samples.petclinic.service.exceptions.LibroNoExistenteException;
import org.springframework.samples.petclinic.service.exceptions.LibroYaEnPrestamoException;
import org.springframework.samples.petclinic.service.exceptions.PrestamoConRetrasoException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class PrestamoServiceTest {
	@Autowired
	private PrestamoService prestamoService;

	@Autowired
	private MiembroService miembroService;
	
	@Autowired
	private UserService userService;
	
	@Test
	public void testCountWithInitialData() {
		int count = prestamoService.prestamoCount();
		assertTrue(count == 4);
	}

	@Test
	public void testFindAll() {
		Iterable<Prestamo> prestamos = prestamoService.findAll();
		assertTrue(prestamos.iterator().next().getMiembro().getApellidos().equals("Llamas Costa"));
	}

	@Test
	public void testFindById() {
		Prestamo prestamo = prestamoService.findById(0).get();
		assertTrue(prestamo.getEjemplar().getId().equals(1));
	}

	@Test
	@Transactional
	public void testRealizarReserva() throws Exception {
		int initialCount = prestamoService.prestamoCount();
		
		User user = userService.findUser("raulla1").get();
		Miembro miembro = miembroService.findByUser(user);
		
		prestamoService.realizarReserva(1, miembro);
		
		int count = prestamoService.prestamoCount();
		assertTrue(count == initialCount + 1);
	}
	
	@Test
	@Transactional
	public void testLibroNoExistente() throws Exception {
		User user = userService.findUser("raulla1").get();
		Miembro miembro = miembroService.findByUser(user);
		
		Assertions.assertThrows(LibroNoExistenteException.class,() ->{prestamoService.realizarReserva(99, miembro);});
	}
	
	@Test
	@Transactional
	public void testLibroNoDisponible() throws Exception {
		User user = userService.findUser("jorgon1").get();
		Miembro miembro = miembroService.findByUser(user);
		
		Assertions.assertThrows(LibroNoDisponibleException.class,() ->{prestamoService.realizarReserva(2, miembro);});
	}
	
	@Test
	@Transactional
	public void testPrestamoExistente() throws Exception {
		User user = userService.findUser("jorgon1").get();
		Miembro miembro = miembroService.findByUser(user);
		
		Assertions.assertThrows(LibroYaEnPrestamoException.class,() ->{prestamoService.realizarReserva(1, miembro);});
	}
	
	@Test
	@Transactional
	public void testPrestamoConRetraso() throws Exception {
		User user = userService.findUser("ivasan1").get();
		Miembro miembro = miembroService.findByUser(user);
		
		Assertions.assertThrows(PrestamoConRetrasoException.class,() ->{prestamoService.realizarReserva(1, miembro);});
	}
}
