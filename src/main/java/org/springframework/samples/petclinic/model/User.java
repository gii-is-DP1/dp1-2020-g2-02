package org.springframework.samples.petclinic.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User{
	@Id
	@Size(min = 3,message = "El nombre de usuario debe contener al menos 3 caracteres")
	String username;
	
	@NotEmpty
	@Pattern(regexp ="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,32}$", message="La contraseña debe contener minúsculas, mayúsculas, números y debe estar entre 8 y 32 caracteres") //Contraseña con minúsculas, mayúsculas, números y entre 8 y 32 caracteres
	String password;
	
	boolean enabled;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private Set<Authorities> authorities;
}
