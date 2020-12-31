package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "libros")

public class Libro extends BaseEntity {

	@Column(name = "ISBN")
	@NotEmpty
	@Digits(fraction=0, integer=12)
	private String ISBN;
	
	@Column(name = "titulo")
	@NotEmpty    
	private String titulo;
	
	@Column(name = "idioma")
	@NotEmpty 
	private String idioma;
	
	@Column(name = "fecha_publicacion")        
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate fecha_publicacion;
	
	@ManyToOne
	@JoinColumn(name = "editorial_id")
	private Editorial editorial;

}
