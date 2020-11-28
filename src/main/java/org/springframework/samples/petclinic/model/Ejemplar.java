package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "ejemplares")

public class Ejemplar extends BaseEntity {

	@ManyToOne
	@JoinColumn(name = "libro_id")
	private Libro libro;
	
	@Column(name = "disponibilidad")
	@Enumerated(EnumType.STRING)
	private Disponibilidad disponibilidad;
	

	@Column(name = "estado")
	private String estado;
	
	
	

}
