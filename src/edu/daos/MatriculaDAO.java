package edu.daos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;

import edu.controllers.EMF;
import edu.entities.Carrera;
import edu.entities.Estudiante;
import edu.entities.Matricula;
import edu.util.ReporteCarrera;
import edu.util.ReporteMatricula;

/**
 * Administra consultas hacia la BD de la entidad matricula
 * @author teamBolivar
 * @version v1.0
 * @since   2020-09-21
 */
public class MatriculaDAO extends DAOGenericoImpl<Matricula>{

private static MatriculaDAO instance;
	
	private MatriculaDAO() {
		super(Matricula.class);
	}
	
	public static MatriculaDAO getInstance() {
		if(instance == null)
			instance = new MatriculaDAO();
		
		return instance;
	}

	@SuppressWarnings("unchecked")
	public List<Matricula> getMatriculas(){
		EntityManager em = EMF.getEntityManager();
		List<Matricula> list = em.createQuery(""
				+ "SELECT m "
				+ "FROM Matricula m").getResultList();
		em.close();
		return list;
	}

	/**
 	 * Persiste una carrera en BD
	 * @param m
	 * @return id con el cual se almacena en BD
	 */
	public int addMatricula(Matricula m) {
		EntityManager em = EMF.getEntityManager();
		em.getTransaction().begin();
		em.persist(m);
		em.getTransaction().commit();
		em.close();
		return m.getId();
	}

	/**
	 * Devuelve un reporte de las carreras, que para cada carrera incluya
	 * informaciï¿½n de los inscriptos y egresados por aï¿½o. Se ordenan las 
	 * carreras alfabï¿½ticamente, y presenta los aï¿½os de manera cronolï¿½gica.
	 * @return lista de matriculas junto cantidad de inscriptos y egresados
	 */
	@SuppressWarnings("unchecked")
	public List<ReporteMatricula> reporteEgresados() {
		EntityManager em = EMF.getEntityManager();
		
		//se optiene los estudiantes egresados por carrera
		List<Object[]> egresados = em.createQuery(""
				+ "SELECT m.carrera, m.graduado, COUNT(m.graduado) "
				+ "FROM Matricula m "
				+ "WHERE m.graduado > 0 "
				+ "GROUP BY m.graduado, m.carrera "
				+ "ORDER BY m.carrera.nombre, m.graduado").getResultList();
		
		//se optiene los estudiantes inscriptos por carrera
		List<Object[]> inscriptos = em.createQuery(""
				+ "SELECT m.carrera, YEAR(m.fechaInscripcion) as anio, COUNT(*) "
				+ "FROM Matricula m "
				+ "GROUP BY m.carrera, anio "
				+ "ORDER BY m.carrera.nombre, m.graduado, anio").getResultList();

		int i = 0; //indice de los inscriptos
		int j = 0; //indice de los egresados
		
		List<ReporteMatricula> matriculas = new ArrayList<>();
		
		//recorro principalmente inscriptos y veo si estan en agresados, CRUZAMIENTO.
		while(i < inscriptos.size() && j < egresados.size()) {
			Carrera ica = (Carrera) inscriptos.get(i)[0];
			int ianio = (int) inscriptos.get(i)[1];
			int icant = ((Long) inscriptos.get(i)[2]).intValue();
			
			Carrera jca = (Carrera) egresados.get(j)[0];
			int janio = (int) egresados.get(j)[1];
			int jcant = ((Long) egresados.get(j)[2]).intValue();
			
			//si la carrera del inscripto coincide con la carrera del egresado para el mismo año
			//lo agrego al reporte y se aumentan los indices.
			if((ica.getId() == jca.getId()) && (ianio == janio)) {
				matriculas.add(new ReporteMatricula(ica, ianio, icant, jcant));
				i++;
				j++;
			} 
			//primero se verifica que no se altere el orden de las carreras, 
			//y luego agrega los inscriptos y los egresados que corresponde.
			else if(ica.getNombre().compareTo(jca.getNombre()) < 0 || (ica.getId() == jca.getId() && ianio < janio)) {
				matriculas.add(new ReporteMatricula(ica, ianio, icant, 0)); //agrego un inscripto
				i++;
			}
			//agrega todo los restantes.
			else {
				matriculas.add(new ReporteMatricula(jca, janio, 0, jcant)); //agrego un egresado
				j++;
			}
		}
		
		//si hay mas inscriptos los agrego.
		while(i < inscriptos.size()) {
			Carrera ica = (Carrera) inscriptos.get(i)[0];
			int ianio = (int) inscriptos.get(i)[1];
			int icant = ((Long) inscriptos.get(i)[2]).intValue();
			
			matriculas.add(new ReporteMatricula(ica, ianio, icant, 0));
			i++;
			
		}
		
		//si hay mas egresados los agrego.
		while(j < egresados.size()) {
			Carrera jca = (Carrera) egresados.get(j)[0];
			int janio = (int) egresados.get(j)[1];
			int jcant = ((Long) egresados.get(j)[2]).intValue();
			
			matriculas.add(new ReporteMatricula(jca, janio, 0, jcant));
			j++;
		}
		em.close();
		return matriculas;
	}
}

