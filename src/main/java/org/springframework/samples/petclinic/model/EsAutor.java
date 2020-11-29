package org.springframework.samples.petclinic.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "es_autor")
public class EsAutor extends BaseEntity{


	@ManyToOne       
	@JoinColumn(name = "autor_id")
	private Autor autor;
	

	@ManyToOne       
	@JoinColumn(name = "libro_id")
	private Autor libro;


	public Autor getAutor() {
		return autor;
	}


	public void setAutor(Autor autor) {
		this.autor = autor;
	}


	public Autor getLibro() {
		return libro;
	}


	public void setLibro(Autor libro) {
		this.libro = libro;
	}
}
