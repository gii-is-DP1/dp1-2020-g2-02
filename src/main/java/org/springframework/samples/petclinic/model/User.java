package org.springframework.samples.petclinic.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User{
	@Id
	String username;
	
	@NotEmpty
	@Pattern(regexp ="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,32}$") //Contraseña con minúsculas, mayúsculas, números y entre 8 y 32 caracteres
	String password;
	
	boolean enabled;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private Set<Authorities> authorities;
}
