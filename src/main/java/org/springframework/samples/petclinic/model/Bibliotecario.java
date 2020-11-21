package org.springframework.samples.petclinic.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
@Entity
@Table(name = "bibliotecarios")
public class Bibliotecario extends BaseEntity {
		@Column(name = "nombre")
		@NotEmpty
		private String nombre;
		
		@Column(name = "apellidos")
		@NotEmpty
		private String apellidos;
		
		@Column(name = "dni")
		@NotEmpty
		@Pattern(regexp = "^[0-9]{8}[TRWAGMYFPDXBNJZSQVHLCKE]$")
		private String dni;
		
		@Column(name = "telefono")
		@NotEmpty
		@Digits(fraction = 0, integer = 10)
		private Integer telefono;
		
		@Column(name = "email")
		@NotEmpty
		@Email
		private String email;

		@Column(name = "pass")
		@NotEmpty
		@Pattern(regexp ="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,32}$") //Contraseña con minúsculas, mayúsculas, números y entre 8 y 32 caracteres
		private String pass;

		
		@OneToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "username", referencedColumnName = "username")
		private User user;
		
		@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "bibliotecario")
		private Set<Novedad> novedades;
		
		/*@OneToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "username", referencedColumnName = "username")
		private User user;*/
}
