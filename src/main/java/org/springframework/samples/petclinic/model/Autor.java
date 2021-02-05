package org.springframework.samples.petclinic.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "autores")

public class Autor extends BaseEntity {

	@Column(name="nombre")
	@NotEmpty
	private String nombre;

	@Column(name="apellidos")
	@NotEmpty
	private String apellidos;

	@Column(name = "fecha_nac")        
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate fecha_nac;


	@ManyToMany
	@JoinTable(
			  name = "es_autor", 
			  joinColumns = @JoinColumn(name = "autor_id"), 
			  inverseJoinColumns = @JoinColumn(name = "libro_id"))
	private List<Libro> libros;
}