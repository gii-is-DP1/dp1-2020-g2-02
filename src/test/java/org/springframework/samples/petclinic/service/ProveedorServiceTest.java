package org.springframework.samples.petclinic.service;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Proveedor;
import org.springframework.samples.petclinic.model.Proveedor;
import org.springframework.samples.petclinic.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class ProveedorServiceTest {
	@Autowired
	private ProveedorService proveedorService;
	
	@Test
	public void testCountWithInitialData() {
		int count=proveedorService.proveedorCount();
		assertTrue(count==2);
	}
	
	@Test
	public void testFindAll() {
		Iterable<Proveedor> proveedors = proveedorService.findAll();
		assertTrue(proveedors.iterator().next().getNombre().equals("Francisco"));
	}
	
	@Test
	public void testFindById() {
		Proveedor proveedor = proveedorService.findById(0).get();
		assertTrue(proveedor.getNombre().equals("Francisco"));
	}
	
	@Test
	@Transactional
	public void testAddProveedor() {
		int cuentaInicial = proveedorService.proveedorCount();
		Proveedor proveedor = new Proveedor();
		proveedor.setId(cuentaInicial);
		proveedor.setNombre("Alejandro");;
		proveedor.setNif("49387456P");
		proveedor.setDireccion("C/Betis Nº12, Sevilla");
		proveedor.setTelefono(650606444);
		proveedor.setEmail("ruco@gmail.com");
		proveedorService.save(proveedor);

		int count=proveedorService.proveedorCount();
		assertTrue(count==3);
	}

}
