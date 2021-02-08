package org.springframework.samples.petclinic.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "bibliotecarios", uniqueConstraints={@UniqueConstraint(columnNames={"dni"})})
public class Bibliotecario extends BaseEntity {
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

		@JsonIgnore
		@OneToMany(cascade = CascadeType.ALL, mappedBy = "bibliotecario")
		private Set<Novedad> novedades;

}
