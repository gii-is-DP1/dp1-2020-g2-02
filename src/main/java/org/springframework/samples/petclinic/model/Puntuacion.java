package org.springframework.samples.petclinic.model;


 import javax.persistence.Column;
 import javax.persistence.Entity;
 import javax.persistence.JoinColumn;
 import javax.persistence.ManyToOne;
 import javax.persistence.Table;
 import javax.validation.constraints.Max;
 import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Getter;
 import lombok.Setter;

 @Entity
 @Getter
 @Setter
 @Table(name = "puntuaciones")
 public class Puntuacion extends BaseEntity{

 	@Column(name = "puntaje")
 	@Min(1)
 	@Max(5)
 	@NotNull
 	private Integer puntaje;

 	@ManyToOne
 	@JoinColumn(name = "miembro_id")
 	private Miembro miembro;

 	@ManyToOne
 	@JoinColumn(name = "libro_id")
 	private Libro libro;
 }
