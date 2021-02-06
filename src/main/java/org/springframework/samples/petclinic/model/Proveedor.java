package org.springframework.samples.petclinic.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.NumberFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "proveedores")
public class Proveedor extends BaseEntity{
	
	@Column(name = "nombre")
	@NotEmpty
	private String nombre;
	
	@Column(name = "nif")
	@NotEmpty
	@Pattern(regexp = "^[a-zA-Z]{1}\\d{7}[a-zA-Z0-9]{1}$", message="NIF no valido (Debe contener una letra, 7 dígitos y un código de control)")
	private String nif;
	
	@Column(name = "direccion")
	@NotEmpty
	private String direccion;
	
	@Column(name = "telefono")
	@NotNull
	@Pattern(regexp = "^[0-9]{9}$", message="El número de teléfono debe contener 9 dígitos")
	private String telefono;
	
	@Column(name = "email")
	@NotEmpty
	@Email
	private String email;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "proveedor")
	private List<Encargo> encargos;
	
	
}
