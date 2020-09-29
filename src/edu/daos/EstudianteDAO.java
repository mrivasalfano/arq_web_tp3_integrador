package edu.daos;

import java.util.List;

import javax.persistence.EntityManager;

import edu.controllers.EMF;
import edu.entities.Carrera;
import edu.entities.Estudiante;

/**
 * Administra consultas hacia la BD de la entidad estudiante
 * @author teamBolivar
 * @version v1.0
 * @since   2020-09-21
 */
public class EstudianteDAO extends DAOGenericoImpl<Estudiante>{
	private static EstudianteDAO instance;
	
	private EstudianteDAO() {
		super(Estudiante.class);
	}
	
	public static EstudianteDAO getInstance() {
		if(instance == null)
			instance = new EstudianteDAO();
		
		return instance;
	}

	/**
	 * Persiste un estudiante en BD
	 * @param e
	 * @return id con el cual se almacena en BD
	 */
//	public boolean addEstudiante(Estudiante e) {
//		EntityManager em = EMF.getEntityManager();
//		em.getTransaction().begin();
//		em.persist(e);
//		em.getTransaction().commit();
//		em.close();
//		
//		return this.getEstudiante(e.getId()) != null;
//	}

	public Estudiante getEstudiante(int id) {
		EntityManager em = EMF.getEntityManager();
		Estudiante e = em.find(Estudiante.class, id);
		em.close();
		return e;
	}
	
	/**
	 * Devuelve todos los estudiantes con un criterio de orden
	 * @param orden nombre del atributo por el cual ordenar
	 * @return una lista de estudiantes ordenada
	 */
	@SuppressWarnings("unchecked")
	public List<Estudiante> getEstudiantes(String orden){
		EntityManager em = EMF.getEntityManager();
		List<Estudiante> list = em.createQuery(""
				+ "SELECT e "
				+ "FROM Estudiante e "
				+ "ORDER BY e." + orden).getResultList();
		
		em.close();
		return list;
	}
	
	/**
	 * Devuelve los estudiantes
	 * @return una lista de estudiantes
	 */
	@SuppressWarnings("unchecked")
	public List<Estudiante> getEstudiantes(){
		EntityManager em = EMF.getEntityManager();
		List<Estudiante> list = em.createQuery(""
				+ "SELECT e "
				+ "FROM Estudiante e").getResultList();
		em.close();
		return list;
	}

	
	/**
	 * Devuelve un estudiante, en base a su n�mero de libreta universitaria
	 * @param nro numero de libreta universitaria
	 * @return lista de estudiantes
	 */
	@SuppressWarnings("unchecked")
	public Estudiante getEstudiantePorLibretaUniversitaria(int nro) {
		EntityManager em = EMF.getEntityManager();
		
		List<Estudiante> list = em.createQuery(""
				+ "SELECT e "
				+ "FROM Estudiante e "
				+ "WHERE e.nroLibretaUni = :nro", Estudiante.class)
		.setParameter("nro", nro).getResultList();
		em.close();
		
		if(!list.isEmpty())
			return list.get(0);
		
		return null;
	}

	/**
	 * Devuelve los estudiantes con un determinado genero
	 * @param genero
	 * @return lista de estudiantes
	 */
	@SuppressWarnings("unchecked")
	public List<Estudiante> getEstudiantesPorGenero(String genero) {
		EntityManager em = EMF.getEntityManager();
		List<Estudiante> list = em.createQuery(""
				+ "SELECT e "
				+ "FROM Estudiante e "
				+ "WHERE e.genero = :genero ")
		.setParameter("genero", genero).getResultList();
		em.close();
		return list;
	}
}
