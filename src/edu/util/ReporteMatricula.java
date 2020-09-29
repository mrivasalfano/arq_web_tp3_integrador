package edu.util;

import edu.entities.Carrera;

/**
 * reporte de las matriculas junto con el anio, 
 * cantidad de inscriptos y egresados 
 * @author teamBolivar
 * @version v1.0
 * @since   2020-09-21
 */
public class ReporteMatricula {
	
	private Carrera carrera;
	private int anio;
	private int cantInscriptos;
	private int cantEgresados;
	
	/**
	 * Constructor de la clase ReporteMatricula
	 * @param carrera
	 * @param anio
	 * @param cantInscriptos
	 * @param cantEgresados
	 */
	public ReporteMatricula(Carrera carrera, int anio, int cantInscriptos, int cantEgresados) {
		super();
		this.carrera = carrera;
		this.anio = anio;
		this.cantInscriptos = cantInscriptos;
		this.cantEgresados = cantEgresados;
	}

	public Carrera getCarrera() {
		return carrera;
	}

	public void setCarrera(Carrera carrera) {
		this.carrera = carrera;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public int getCantInscriptos() {
		return cantInscriptos;
	}

	public void setCantInscriptos(int cantInscriptos) {
		this.cantInscriptos = cantInscriptos;
	}

	public int getCantEgresados() {
		return cantEgresados;
	}

	public void setCantEgresados(int cantEgresados) {
		this.cantEgresados = cantEgresados;
	}

	@Override
	public String toString() {
		return "ReporteMatricula [carrera=" + carrera + ", anio=" + anio + ", cantInscriptos=" + cantInscriptos
				+ ", cantEgresados=" + cantEgresados + "] \n";
	}
	
}
