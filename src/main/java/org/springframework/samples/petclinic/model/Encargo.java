package org.springframework.samples.petclinic.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "encargos")
public class Encargo extends BaseEntity{
	@Column(name = "fechaRealizacion")
	private LocalDate fechaRealizacion;
	
	@Column(name = "fechaEntrega")
	private LocalDate fechaEntrega;
	
//	@ManyToOne
//	@JoinColumn(name = 'proveedor_id')
//	private Proveedor Proveedor;
	
	@ManyToMany
	@JoinTable(name = "es_encargo",
			joinColumns = @JoinColumn(name = "encargo_id"), 
			  inverseJoinColumns = @JoinColumn(name = "libro_id"))
	private List<Libro> libros;
}
