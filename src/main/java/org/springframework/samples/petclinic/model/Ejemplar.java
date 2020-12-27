package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "ejemplares")

public class Ejemplar extends BaseEntity {

	@ManyToOne
	@JoinColumn(name = "libro_id")
	private Libro libro;
	
	@Column(name = "disponibilidad")
	@Enumerated(EnumType.STRING)
	private Disponibilidad disponibilidad;
	

	@Column(name = "estado")
	@NotEmpty
	private String estado;
	
	
	

}
