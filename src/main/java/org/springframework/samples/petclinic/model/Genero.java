package org.springframework.samples.petclinic.model;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "generos")
public class Genero extends BaseEntity {


	@ManyToMany
    @JoinTable(
              name = "pertenece_a", 
              joinColumns = @JoinColumn(name = "genero_id"), 
              inverseJoinColumns = @JoinColumn(name = "libro_id"))   
	
    @JoinColumn(name = "libro_id")
    private List<Libro> libros;
	
	@Column(name = "nombre_genero")
	@NotEmpty    
	private String nombreGenero;

}
