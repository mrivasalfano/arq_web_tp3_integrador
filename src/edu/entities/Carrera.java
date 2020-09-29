package edu.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Entidad que representa una carrera universitaria
 * @author teamBolivar
 * @version v1.0
 * @since   2020-09-21 
 */
@Entity
public class Carrera {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column
	private String nombre;
	@OneToMany(mappedBy  = "carrera")
	private List<Matricula> estudiantes;
	
	
	public Carrera() {
		super();
		this.estudiantes = new ArrayList<Matricula>();
	}

	
	/**
	 * Otro constructor de la clase Carrera
	 * @param nombre define a la carrera
	 */
	public Carrera(String nombre) {
		this();
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}
	
	/**
	 * Agrega una matricula a la lista de estudiantes
	 * @param matricula
	 */
	public void addEstudiante(Matricula matricula) {
		this.estudiantes.add(matricula);
	}
	
	@Override
	public String toString() {
		return "Carrera [id=" + id + ", nombre=" + nombre + "]";
	}
	
}
