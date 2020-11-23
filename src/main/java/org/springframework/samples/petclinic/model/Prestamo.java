<<<<<<< HEAD
package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name= "prestamos")
public class Prestamo extends BaseEntity{
	
	@Column(name = "fecha_prestamo")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate fechaPrestamo;
	
	@Column(name= "fecha_devolucion")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate fechaDevolucion;
	
	@ManyToOne
	@JoinColumn(name = "bibliotecario_id")
	private Bibliotecario bibliotecario;
	
	@ManyToOne
	@JoinColumn(name = "miembro_id")
	private Miembro miembro;
	
	@ManyToOne
	@JoinColumn( name = "ejemplar_id")
	private Ejemplar ejemplar;
}


=======
package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name= "prestamos")
public class Prestamo extends BaseEntity{
	
	@Column(name = "fecha_prestamo")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate fechaPrestamo;
	
	@Column(name= "fecha_devolucion")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate fechaDevolucion;
	
	@ManyToOne
	@JoinColumn(name = "bibliotecario_id")
	private Bibliotecario bibliotecario;
	
	@ManyToOne
	@JoinColumn(name = "miembro_id")
	private Miembro miembro;
	
	@ManyToOne
	@JoinColumn( name = "ejemplar_id")
	private Ejemplar ejemplar;
}


>>>>>>> parent of af356ce... Añadido autores
