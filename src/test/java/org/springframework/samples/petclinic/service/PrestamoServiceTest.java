package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.Collection;

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
import org.springframework.samples.petclinic.service.exceptions.LimitePrestamosException;
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
		assertTrue(count == 7);
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
		assertThat(count).isEqualTo(initialCount+1);
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
	
	@Test
	@Transactional
	public void testPrestamoLimite() throws Exception {
		User user = userService.findUser("alecai1").get();
		Miembro miembro = miembroService.findByUser(user);
		
		Assertions.assertThrows(LimitePrestamosException.class,() ->{prestamoService.realizarReserva(4, miembro);});
	}
	
	@Test
    @Transactional
    public void testPrestamosMiembrosUrgente() throws Exception {
        User user = userService.findUser("ivasan1").get();
        Miembro miembro = miembroService.findByUser(user);
        
        Collection<Prestamo> prestamosUrgentes = prestamoService.prestamosMiembrosUrgentes(miembro);
        
        assertThat(prestamosUrgentes.size()).isEqualTo(1);
    }
    
    @Test
    @Transactional
    public void testHistorialPrestamosMiembro() throws Exception {
        User user = userService.findUser("raulla1").get();
        Miembro miembro = miembroService.findByUser(user);
        
        Collection<Prestamo> prestamosMiembro = prestamoService.historialPrestamos(miembro);
        
        assertThat(prestamosMiembro.size()).isEqualTo(2);
    }
    
    @Test
    @Transactional
    public void testPrestamosFechaDevolucionTardia() throws Exception {
        Collection<Prestamo> prestamosRetrasados = prestamoService.prestamosConFechaDevolucionTardia(LocalDate.now());
        
        assertThat(prestamosRetrasados.size()).isEqualTo(1);
    }
    
    @Test
    @Transactional
    public void testPrestamosHoy() throws Exception {
        Collection<Prestamo> prestamosHoy = prestamoService.findPrestamosHoy();
        
        assertThat(prestamosHoy.size()).isEqualTo(2);
    }
}
