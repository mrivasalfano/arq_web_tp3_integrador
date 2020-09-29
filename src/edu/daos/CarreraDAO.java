package edu.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import edu.controllers.EMF;
import edu.entities.Carrera;
import edu.entities.Estudiante;
import edu.entities.Matricula;
import edu.util.ReporteCarrera;

/**
 * Administra consultas hacia la BD de la entidad carrera
 * @author teamBolivar
 * @version v1.0
 * @since   2020-09-21
 */
public class CarreraDAO extends DAOGenericoImpl<Carrera>{
	private static CarreraDAO instance;
	
	private CarreraDAO() {
		super(Carrera.class);
	}
	
	public static CarreraDAO getInstance() {
		if(instance == null)
			instance = new CarreraDAO();
		
		return instance;
	}

	/**
	 * Devuelve las carreras con estudiantes inscriptos ordenados por cantidad
	 * @return reporte de las carreras 
	 */
	@SuppressWarnings("unchecked")
	public ReporteCarrera getCarrerasEstudiantesIns() {
		EntityManager em = EMF.getEntityManager();
		List<Object[]> carreras = em.createQuery(""
				+ "SELECT m.carrera, COUNT(m.carrera) as cantidad "
				+ "FROM Matricula m "
				+ "GROUP BY m.carrera "
				+ "ORDER BY cantidad DESC").getResultList();
		ReporteCarrera report = new ReporteCarrera();
		for (Object[] carrer : carreras) {
			report.addCarrera((Carrera)carrer[0], ((Long)carrer[1]).intValue());			
		}
		em.close();
		System.out.println(report);
		return report;
	}
	
	/**
	 * Devuelve los estudiantes de una determinada carrera y ciudad
	 * @param carrera 
	 * @param ciudad
	 * @return lista de estudiantes
	 */
	@SuppressWarnings("unchecked")
	public List<Estudiante> getEstudiantesCarreraCiudad(String carrera, String ciudad) {
		EntityManager em = EMF.getEntityManager();
		List<Estudiante> e = em.createQuery(""
				+ "SELECT m.estudiante "
				+ "FROM Matricula m "
				+ "WHERE m.carrera.nombre = :carrera "
				+ "AND m.estudiante.ciudad = :ciudad")
				.setParameter("carrera", carrera)
				.setParameter("ciudad", ciudad)
				.getResultList();
		em.close();
		return e;
	}
}
