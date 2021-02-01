
package org.springframework.samples.petclinic.service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Disponibilidad;
import org.springframework.samples.petclinic.model.Ejemplar;
import org.springframework.samples.petclinic.model.Libro;
import org.springframework.samples.petclinic.model.Miembro;
import org.springframework.samples.petclinic.model.Prestamo;
import org.springframework.samples.petclinic.repository.LibroRepository;
import org.springframework.samples.petclinic.repository.PrestamoRepository;
import org.springframework.samples.petclinic.service.exceptions.LibroNoDisponibleException;
import org.springframework.samples.petclinic.service.exceptions.LibroNoExistenteException;
import org.springframework.samples.petclinic.service.exceptions.LibroYaEnPrestamoException;
import org.springframework.samples.petclinic.service.exceptions.LimitePrestamosException;
import org.springframework.samples.petclinic.service.exceptions.PrestamoConRetrasoException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class PrestamoService {

	@Autowired
	PrestamoRepository PrestamoRepo;

	@Autowired
	LibroRepository LibroRepo;
	
	@Autowired
	EjemplarService ejemplarService;
	
	@Transactional(readOnly = true)
	public Collection<Prestamo> findAll() {
		return PrestamoRepo.findAll();
	}

	@Transactional(readOnly = true)
	public Optional<Prestamo> findById(int id) {
		return PrestamoRepo.findById(id);
	}

	@Transactional
	public int prestamoCount() {
		return (int) PrestamoRepo.count();
	}

	@Transactional
	public void delete(Prestamo Prestamo) {
		PrestamoRepo.deleteById(Prestamo.getId());
	}

	@Transactional
	public void save(@Valid Prestamo Prestamo) {
		PrestamoRepo.save(Prestamo);
	}
	
	@Transactional(readOnly = true)
	public Collection<Prestamo> prestamosConFechaDevolucionTardia(LocalDate fecha){
		//
		return PrestamoRepo.prestamosConFechaDevolucionTardia(fecha);
	}
	
	public Prestamo realizarReserva(int libroId, Miembro miembro)  throws LibroNoExistenteException, LimitePrestamosException, LibroYaEnPrestamoException, LibroNoDisponibleException, PrestamoConRetrasoException{
		Optional<Libro> libro = LibroRepo.findById(libroId);
		if(!libro.isPresent()) {
			throw new LibroNoExistenteException();
		}
		//Comprueba si el usuario tiene ya 3 o más préstamos en proceso
		Collection<Prestamo> prestamos = PrestamoRepo.prestamosEnProceso(miembro);
		if(prestamos.size()>3) {
			throw new LimitePrestamosException();
		}
		//Comprueba si el usuario tiene ya en préstamo ese libro.
		if(prestamos.stream().anyMatch(x->x.getEjemplar().getLibro().equals(libro.get()))) {
			throw new LibroYaEnPrestamoException();
		}
		
		//Comprueba si el libro no tiene ejemplares disponibles
		Collection<Ejemplar> ejemplaresDisponibles = ejemplarService.findDisponibles(libro.get());
		if(ejemplaresDisponibles.isEmpty()) {
			throw new LibroNoDisponibleException();
		}
		
		//Comprueba si el usuario tiene algún préstamo con fecha de devolución tardía
		if(prestamos.stream().anyMatch(x->x.getFechaDevolucion().isBefore(LocalDate.now()))) {
			throw new PrestamoConRetrasoException();
		}
		
		//Realiza el préstamo
		Ejemplar ejemplar = ejemplaresDisponibles.iterator().next();
		ejemplar.setDisponibilidad(Disponibilidad.RESERVADO);
		Prestamo prestamo = new Prestamo();
		prestamo.setFechaPrestamo(LocalDate.now());
		prestamo.setFechaDevolucion(LocalDate.now().plusDays(16));
		prestamo.setFinalizado(false);
		prestamo.setEjemplar(ejemplar);
		prestamo.setMiembro(miembro);

		PrestamoRepo.save(prestamo);
		return prestamo;
	}

}
