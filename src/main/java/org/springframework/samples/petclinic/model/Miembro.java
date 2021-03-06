package org.springframework.samples.petclinic.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "Miembros")

public class Miembro extends BaseEntity {

		@Column(name = "nombre")
		@NotEmpty
		private String nombre;
		
		@Column(name = "apellidos")
		@NotEmpty
		private String apellidos;
		
		@Column(name = "dni")
		@NotEmpty
		@Pattern(regexp = "^[0-9]{8}[TRWAGMYFPDXBNJZSQVHLCKE]$", message="DNI no válido (Debe contener 8 dígitos y una letra de control)")
		private String dni;
		
		@Column(name = "telefono")
		@NotNull
		@Pattern(regexp = "^[0-9]{9}$", message="El número de teléfono debe contener 9 dígitos")
		private String telefono;
		
		@Column(name = "email")
		@NotEmpty
		@Email
		private String email;
		
		@OneToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "username", referencedColumnName = "username")
		private User user;

		
}

