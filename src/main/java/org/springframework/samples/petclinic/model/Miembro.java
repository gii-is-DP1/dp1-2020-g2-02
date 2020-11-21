package org.springframework.samples.petclinic.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
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
		
		@OneToOne(cascade = CascadeType.ALL)
		@JoinColumn(name = "pass", referencedColumnName = "pass")
		private String pass;

		
}

