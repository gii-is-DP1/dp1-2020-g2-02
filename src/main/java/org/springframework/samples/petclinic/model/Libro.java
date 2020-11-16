package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Data
@Table(name = "libros")

public class Libro extends BaseEntity {

	@Column(name = "ISBN")     
	private Integer ISBN;
	
	@Column(name = "titulo")     
	private String titulo;
	
	@Column(name = "idioma")     
	private String idioma;
	
	@Column(name = "fecha_publicacion")        
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate fecha_publicacion;
}
