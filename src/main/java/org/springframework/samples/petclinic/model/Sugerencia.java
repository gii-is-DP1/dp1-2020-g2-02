package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "sugerencias")
public class Sugerencia extends BaseEntity{
	
	@Column(name="titulo_libro")
	private String tituloLibro;
	
	@Column(name="nombre_autor")
	private String nombreAutor;
	
	@ManyToOne
	@JoinColumn(name = "miembro_id")
	private Miembro miembro;

}
