package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "editoriales")

public class Editorial extends BaseEntity {

	@Column(name = "nombre")
	@NotEmpty
	private String nombre;
	
	@Column(name = "nif")
	@NotEmpty
	@Pattern(regexp = "^[a-zA-Z]{1}\\d{7}[a-zA-Z0-9]{1}$")
	private String nif;
	
	@Column(name = "direccion")
	@NotEmpty
	private String direccion;
	
	@Column(name = "telefono")
	@NotEmpty
	@Digits(fraction = 0, integer = 10)
	private String telefono;

	@Column(name = "email")
	@NotEmpty
	@Email
	private String email;

	@Column(name = "web")
	@NotEmpty
	@Pattern(regexp="https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&//=]*)")
	private String web;
}
