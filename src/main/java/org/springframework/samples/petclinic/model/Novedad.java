
package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name= "novedades")
public class Novedad extends BaseEntity{
	
	@Column(name = "titulo") 
	@NotEmpty
	private String titulo;
	
	@Column(name = "contenido")
	@NotEmpty
	private String contenido;
	
	@Column(name = "fecha_publicacion")        
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate fechaPublicacion;
	
	@ManyToOne
	@JoinColumn(name = "bibliotecario_id")
	private Bibliotecario bibliotecario;
}
