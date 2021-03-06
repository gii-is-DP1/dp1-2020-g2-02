package org.springframework.samples.petclinic.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "libros")

public class Libro extends BaseEntity {

	@Column(name = "ISBN")
	@NotEmpty
	@Pattern(regexp = "^[0-9]{10}|^(97)[89]{1}[0-9]{10}$",message="El ISBN debe contener 10 o 13 dígitos. Si contiene 13 dígitos debe empezar por 978 o 979.")
	private String ISBN;
	
	@Column(name = "titulo")
	@NotEmpty    
	private String titulo;
	
	@Column(name = "idioma")
	@NotEmpty 
	private String idioma;
	
	@Column(name = "fecha_publicacion")        
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Past
	private LocalDate fecha_publicacion;
	
	@ManyToOne
	@JoinColumn(name = "editorial_id")
	private Editorial editorial;
	
	@ManyToOne
	@JoinColumn(name = "genero_id")
	private Genero genero;

	@ManyToMany
	@JoinTable(
	  name = "es_autor", 
	  joinColumns = @JoinColumn(name = "libro_id"), 
	  inverseJoinColumns = @JoinColumn(name = "autor_id"))
	private List<Autor> autores;
	
	@ManyToMany
	@JsonIgnore
    @JoinTable(
      name = "pertenece_a", 
      joinColumns = @JoinColumn(name = "libro_id"), 
      inverseJoinColumns = @JoinColumn(name = "genero_id"))
    private List<Genero> generos;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "libro")
	private List<Cantidad> cantidad;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "libro", fetch = FetchType.EAGER)
	private List<Ejemplar> ejemplar;	
	
}
