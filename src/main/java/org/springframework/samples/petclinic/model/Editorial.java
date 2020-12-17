package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Entity
@Data
@Table(name = "editoriales")

public class Editorial extends BaseEntity {

	@Column(name = "nombre")
	@NotEmpty
	private String nombre;
	
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
	private String web;
}
