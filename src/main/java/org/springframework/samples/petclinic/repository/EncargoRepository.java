package org.springframework.samples.petclinic.repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Encargo;

public interface EncargoRepository extends CrudRepository<Encargo, Integer>{
    Collection<Encargo> findAll();

    @Query("SELECT e FROM Encargo e WHERE e.fechaEntrega >= ?1 AND e.fechaEntrega <= ?2")
	List<Encargo> pedidosEntreFechas(LocalDate fechaInicial, LocalDate fechaFinal);
}
