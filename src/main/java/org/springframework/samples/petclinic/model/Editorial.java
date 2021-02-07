package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
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
	@Pattern(regexp = "^[0-9]{9}$", message="El número de teléfono debe contener 9 dígitos")
	private String telefono;

	@Column(name = "email")
	@NotEmpty
	@Email
	private String email;

	@Column(name = "web")
	@NotEmpty
	@Pattern(regexp="(www\\.)?([a-zA-Z0-9]+(-?[a-zA-Z0-9])*\\.)+[\\w]{2,}(\\/\\S*)?$")
	private String web;
}
