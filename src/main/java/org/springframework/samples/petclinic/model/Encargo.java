package org.springframework.samples.petclinic.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "encargos")
public class Encargo extends BaseEntity{
	
	@Column(name = "fecha_realizacion")
	@NotNull
	@NotEmpty
	private LocalDate fechaRealizacion;
	
	@Column(name = "fecha_entrega")
	@NotNull
	@NotEmpty
	private LocalDate fechaEntrega;
	
	@ManyToOne
	@JoinColumn(name = "proveedor_id")
	private Proveedor proveedor;
		
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "encargo")
	private List<Cantidad> cantidad;
	
//	@ManyToMany
//	@JoinTable()
//	
}
