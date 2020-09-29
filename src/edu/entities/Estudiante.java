package edu.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Entidad que representa a un estudiante de la universidad
 * @author teamBolivar
 * @version v1.0
 * @since   2020-09-21
 */
@Entity
public class Estudiante {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column
	private String nombres;
	@Column
	private String apellido;
	@Column
	private int edad;
	@Column
	private String genero;
	@Column
	private int dni;
	@Column
	private String ciudad;
	@Column
	private int nroLibretaUni;
	@OneToMany(mappedBy  = "estudiante")
	private List<Matricula> carreras; 
	
	
	public Estudiante() {
		super();
		this.carreras = new ArrayList<Matricula>();
	}
	
	/**
	 * Otro constructor de la clase Estudiante detallando al mismo
	 * @param nombres
	 * @param apellido
	 * @param edad
	 * @param genero
	 * @param dni
	 * @param ciudad
	 * @param nroLibretaUni
	 */
	public Estudiante(String nombres, String apellido, int edad, String genero, int dni, String ciudad,
			int nroLibretaUni) {
		this();
		this.nombres = nombres;
		this.apellido = apellido;
		this.edad = edad;
		this.genero = genero;
		this.dni = dni;
		this.ciudad = ciudad;
		this.nroLibretaUni = nroLibretaUni;
		
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public int getId() {
		return id;
	}

	public int getDni() {
		return dni;
	}

	public int getNroLibretaUni() {
		return nroLibretaUni;
	}

	/**
	 * Agrega una matricula a la lista de carreras
	 * @param matricula
	 */
	public void addCarrera(Matricula matricula) {
		this.carreras.add(matricula);
	}

	@Override
	public String toString() {
		return "Estudiante [id=" + id + ", nombres=" + nombres + ", apellido=" + apellido + ", edad=" + edad
				+ ", genero=" + genero + ", dni=" + dni + ", ciudad=" + ciudad + ", nroLibretaUni=" + nroLibretaUni
				+ ", carreras=" + carreras + "]";
	}
	
	
	
	
	
	

}
