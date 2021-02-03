package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "cantidad")
public class Cantidad extends BaseEntity{

	@Column(name = "unidades")
	@Min(1)
	private Integer unidades;
	
	@Column(name = "precio_unitario")
	private Double precioUnitario;
	
	@ManyToOne
	@JoinColumn(name = "encargo_id")
	private Encargo encargo;
	
	@ManyToOne
	@JoinColumn(name = "libro_id")
	private Libro libro;
	
}
