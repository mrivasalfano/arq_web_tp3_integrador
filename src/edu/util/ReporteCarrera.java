package edu.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.entities.Carrera;
import edu.entities.Matricula;

/**
 * reporte de las carreras junto con la cantidad de estudiantes 
 * @author teamBolivar
 * @version v1.0
 * @since   2020-09-21
 */
public class ReporteCarrera {
	private List<Carrera> carrera;
	private Map<Integer, Integer> cantidad;
	
	/**
	 * Constructor de la clase ReporteCarrera
	 */
	public ReporteCarrera() {
		this.carrera = new ArrayList<>();
		this.cantidad = new HashMap<Integer, Integer>();
	}
	
	/**
	 * Agrega una carrera con cantidad de estudiantes
	 * @param c
	 * @param cantidad
	 */
	public void addCarrera(Carrera c, Integer cantidad) {
		this.carrera.add(c);
		this.cantidad.put(c.getId(), cantidad);
	}
	
	/**
	 * muestra en pantalla el reporte de la carrera
	 */
	public void imprimir() {
		for (Carrera c: carrera) {
			Integer p = cantidad.get(c.getId());
			System.out.println(c + " Cantidad Estudiantes: " + p);
		}
	}

	public List<Carrera> getCarrera() {
		return carrera;
	}

	public Map<Integer, Integer> getCantidad() {
		return cantidad;
	}

}
