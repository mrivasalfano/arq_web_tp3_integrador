package edu.controllers;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import edu.daos.CarreraDAO;
import edu.daos.DAOFactoryImpl;
import edu.daos.EstudianteDAO;
import edu.daos.MatriculaDAO;
import edu.entities.Carrera;
import edu.entities.Estudiante;
import edu.entities.Matricula;

@WebListener
public class EMF implements ServletContextListener {
	private static EntityManagerFactory emf;

	/**
	 * Crea el EntityManagerFactory y llama a insertStarter
	 * para cargar datos de pruebas
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent arg0) {
		emf = Persistence.createEntityManagerFactory("MySQL");
//		insertStarter();
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void close(ServletContextEvent arg0) {
		emf.close();
	}

	public static EntityManager getEntityManager() {
		if (emf == null) {
			throw new IllegalStateException("Context is not initialized yet.");
		}
		return emf.createEntityManager();
	}
	
	private void insertStarter() {
		Estudiante e1 = new Estudiante("Manuel", "Rivas", 21, "Masculino", 41856466, "Bolivar", 1);
		Estudiante e2= new Estudiante("Manuel", "Garcia Amaro", 21, "Masculino", 12345678, "Bolivar", 2);
		Estudiante e3 = new Estudiante("Luciano Martin", "D'Agata Villa", 28, "Masculino", 41856466, "Bolivar", 3);
		Estudiante e4 = new Estudiante("Roberto", "Garcia", 80, "Femenino", 41856466, "Mar del plata", 4);
		Estudiante e5 = new Estudiante("Sebastian", "Saavedra", 65, "Masculino", 41856466, "Bolivar", 5);
		
		Carrera c1 = new Carrera("Tudai");
		Carrera c2 = new Carrera("Tupar");
		Carrera c3 = new Carrera("Ingenieria");
		
		EstudianteDAO er = DAOFactoryImpl.getInstance().getEstudianteDAO();
		CarreraDAO cr = DAOFactoryImpl.getInstance().getCarreraDAO();
		MatriculaDAO mr = DAOFactoryImpl.getInstance().getMatriculaDAO();
		
		er.add(e1);
		er.add(e2);
		er.add(e3);
		er.add(e4);
		er.add(e5);
		
		cr.add(c1);
		cr.add(c2);
		cr.add(c3);
		
		Matricula ec1 = new Matricula(e1, c1);
		Matricula ec2 = new Matricula(e2, c1);
		Matricula ec3 = new Matricula(e3, c1);
		Matricula ec4 = new Matricula(e4, c2);
		Matricula ec5 = new Matricula(e5, c1);
		Matricula ec6 = new Matricula(e4, c1);
		
		mr.add(ec1);
		mr.add(ec2);
		mr.add(ec3);
		mr.add(ec4);
		mr.add(ec5);
		mr.add(ec6);
	}
}