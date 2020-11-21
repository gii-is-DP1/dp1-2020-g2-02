package org.springframework.samples.petclinic.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "bibliotecarios")
public class Bibliotecario extends BaseEntity {
	
		private String nombre;
		private String apellidos;
		private String dni;
		private Integer telefono;
		private String email;
		
		@OneToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "username", referencedColumnName = "username")
		private User user;
		
		@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "bibliotecario")
		private Set<Novedad> novedades;
		
		/*@OneToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "username", referencedColumnName = "username")
		private User user;*/
}
