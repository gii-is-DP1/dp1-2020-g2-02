package org.springframework.samples.petclinic.util;

import java.time.LocalDate;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.DatosDiarios;
import org.springframework.samples.petclinic.service.DatosDiariosService;
import org.springframework.samples.petclinic.service.EncargoService;
import org.springframework.samples.petclinic.service.NovedadService;
import org.springframework.samples.petclinic.service.PrestamoService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CalculoDiario {
	@Autowired
	DatosDiariosService datosService;
	
	@Autowired
	NovedadService novedadService;
	
	@Autowired
	EncargoService encargoService;
	
	@Autowired
	PrestamoService prestamoService;
	
	@Scheduled(cron = "0 59 23 * * ?", zone = "Europe/Madrid")
	public void calcularDatosDiarios() {
		DatosDiarios datos = new DatosDiarios();
		
		datos.setNovedades(novedadService.findNovedadesHoy().size());
		datos.setEncargos(encargoService.findEncargosHoy().size());
		datos.setPrestamos(prestamoService.findPrestamosHoy().size());
		datos.setFecha(LocalDate.now());
		
		datosService.save(datos);
	}
	
	 @PostConstruct
	 private void init() {
		 //Inicializa la tabla al arrancar la aplicación
		 calcularDatosDiarios();
	 }
}
