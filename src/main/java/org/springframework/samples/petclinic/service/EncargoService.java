package org.springframework.samples.petclinic.service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cantidad;
import org.springframework.samples.petclinic.model.Ejemplar;
import org.springframework.samples.petclinic.model.Encargo;
import org.springframework.samples.petclinic.repository.EncargoRepository;
import org.springframework.samples.petclinic.service.exceptions.LimiteEjemplaresException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EncargoService {

	@Autowired
	EncargoRepository encargoRepo;

//	@Autowired
//	LibroService libroService;

//	@Autowired
//	ProveedorService proveedorService;

//	@Autowired
//	CantidadService cantidadService;
	@Transactional
	public int encargoCount() {
		return (int) encargoRepo.count();
	}

	@Transactional
	public Collection<Encargo> findAll() {
		return encargoRepo.findAll();
	}

	@Transactional
	public Optional<Encargo> findById(int id) {
		return encargoRepo.findById(id);
	}

	@Transactional
	public void delete(Encargo encargo) {
		encargoRepo.deleteById(encargo.getId());

	}

	@Transactional
	public void save(@Valid Encargo encargo) throws LimiteEjemplaresException {
		List<Cantidad> cantidades = encargo.getCantidad();
		if(cantidades!=null && !cantidades.isEmpty()) {
		for (int i = 0; i < cantidades.size(); i++) {
			Cantidad cantidad = cantidades.get(i);
			Integer unidadesEncargo = cantidad.getUnidades();
			List<Ejemplar> ejemplares = cantidad.getLibro().getEjemplar();
			int unidadesEjemplares = ejemplares.size();
			if (unidadesEncargo + unidadesEjemplares > 10) {
				throw new LimiteEjemplaresException();
			}
		}}
		encargoRepo.save(encargo);
	}

	public List<Encargo> pedidosUrgentes() {
		return encargoRepo.pedidosEntreFechas(LocalDate.now(),LocalDate.now().plusDays(2));
	}
}
