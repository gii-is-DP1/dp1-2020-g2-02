package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Cantidad;
import org.springframework.samples.petclinic.model.Encargo;
import org.springframework.samples.petclinic.model.Libro;
import org.springframework.samples.petclinic.service.exceptions.LimiteEjemplaresException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class EncargoServiceTest {
	
	@Autowired
	private EncargoService encargoService;
		
	@Autowired
	private ProveedorService proveedorService;
	
	@Autowired
	private CantidadService cantidadService;

	@Autowired
	private LibroService librosService;
	
	@Test
	public void testCountWithInitialData() {
		int count=encargoService.encargoCount();
		assertThat(count).isEqualTo(2);
	}
	
	@Test
	public void testFindAll() {
		Collection<Encargo> encargos = encargoService.findAll();
		assertThat(encargos.size()).isEqualTo(2);
	}
	
	@Test
	public void testFindById() {
		Encargo encargo = encargoService.findById(0).get();
		assertThat(encargo.getFechaRealizacion()).isEqualTo(LocalDate.now().minusDays(2));
	}
	
	@Test
	public void testFindEncargosHoy() {
		Collection<Encargo> encargos = encargoService.findEncargosHoy();
		for(Encargo encargo:encargos) {
			assertThat(encargo.getFechaRealizacion().isEqual(LocalDate.now()));
		}
	}
	
	@Test
	public void testPedidosUrgentes() {
		List<Encargo> encargos = encargoService.pedidosUrgentes();
		assertThat(encargos.size()).isEqualTo(0);
	}
	
	
	@Test
	@Transactional
	public void testAddEncargo() throws Exception {
		int cuentaInicial = encargoService.encargoCount();
		Encargo encargo = new Encargo();
		encargo.setId(cuentaInicial);
		encargo.setFechaEntrega(LocalDate.now().plusDays(1));
		encargo.setFechaRealizacion(LocalDate.now());
		encargo.setProveedor(proveedorService.findById(0).get());
		encargoService.save(encargo);
		int cuentaFinal = encargoService.encargoCount();
		assertThat(cuentaFinal).isEqualTo(cuentaInicial + 1);
	}
	
	@Test
	@Transactional
	public void testDemasiadosEjemplares() throws Exception {
		Libro libro = librosService.findById(1).get();
		
		Encargo encargo = new Encargo();
		encargo.setFechaEntrega(LocalDate.now().plusDays(1));
		encargo.setFechaRealizacion(LocalDate.now());
		encargo.setProveedor(proveedorService.findById(0).get());
		
		Cantidad cantidad = new Cantidad();
		cantidad.setLibro(libro);
		cantidad.setUnidades(11);
		cantidad.setPrecioUnitario(1.2);
		cantidad.setEncargo(encargo);
		cantidadService.save(cantidad);
		
		List<Cantidad> cantidades = new ArrayList<>();
		cantidades.add(cantidad);
		encargo.setCantidad(cantidades);
		
		Assertions.assertThrows(LimiteEjemplaresException.class,() ->{encargoService.save(encargo);;});

	}
	
	
	
}
