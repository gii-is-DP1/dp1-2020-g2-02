package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "cantidad")
public class Cantidad extends BaseEntity{

	@Column(name = "unidades")
	private Integer unidades;
	
	@Column(name = "precioUnitario")
	private Long precioUnitario;
}
