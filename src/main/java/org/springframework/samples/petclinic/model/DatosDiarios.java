package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "datos")
public class DatosDiarios extends BaseEntity{
	
	private LocalDate fecha;
	
	private Integer encargos;
	
	private Integer prestamos;
	
	private Integer novedades;
}
