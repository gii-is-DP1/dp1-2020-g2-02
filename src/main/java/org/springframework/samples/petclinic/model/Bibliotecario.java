package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "bibliotecarios")

public class Bibliotecario extends BaseEntity {
	
		@Column(length=1024)     // Needed in some environments for strings longer than 255 characters
		private String nombre;
		private String apellidos;
		private String dni;
		private Integer telefono;
		private String email;
		private String pass;
		
}
