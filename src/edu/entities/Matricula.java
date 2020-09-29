package edu.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Entidad que representa la relacion entre estudiantes y carreras
 * de la universidad
 * @author teamBolivar
 * @version v1.0
 * @since   2020-09-21
 */
@Entity
public class Matricula {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@ManyToOne
	private Estudiante estudiante;
	@ManyToOne
	private Carrera carrera;
	@Column
	private int graduado;
	@Column
	private Timestamp fechaInscripcion;
	
	
	/**
	 * Constructor de la clase Matricula
	 * Guarda la fecha y hora actual como fecha de inscripcion
	 */
	public Matricula() {
		super();
		this.fechaInscripcion = new Timestamp(System.currentTimeMillis());
	}

	/**
	 * Otro constructor de la clase Matricula
	 * <p> 
	 * La matricula se agrega a la lista de estudiantes y de carreras
	 * @param estudiante
	 * @param carrera
	 */
	public Matricula(Estudiante estudiante, Carrera carrera) {
		this();
		this.estudiante = estudiante;
		this.carrera = carrera;
		this.graduado = 0;
//		PREGUNTAR SI ESTO ESTA BIEN!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//		this.estudiante.addCarrera(this);
//		this.carrera.addEstudiante(this);
		
	}

	public Estudiante getEstudiante() {
		return estudiante;
	}
	
	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}

	public Carrera getCarrera() {
		return carrera;
	}

	public void setCarrera(Carrera carrera) {
		this.carrera = carrera;
	}

	public int getGraduado() {
		return graduado;
	}

	public void setGraduado(int graduado) {
		this.graduado = graduado;
	}

	public int getId() {
		return id;
	}

	public Timestamp getFechaInscripcion() {
		return fechaInscripcion;
	}

	@Override
	public String toString() {
		return "Matricula [carrera=" + carrera + ", graduado=" + graduado + ", fechaInscripcion="
				+ fechaInscripcion + "]";
	}
	
}
