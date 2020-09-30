package edu.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import edu.daos.CarreraDAO;
import edu.daos.DAOFactoryImpl;
import edu.daos.EstudianteDAO;
import edu.daos.MatriculaDAO;
import edu.entities.Carrera;
import edu.entities.Estudiante;
import edu.entities.Matricula;
import edu.util.ReporteMatricula;

@Path("/matriculas")
public class MatriculaRestController {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Matricula> getMatriculas() {
		return DAOFactoryImpl.getInstance().getMatriculaDAO().getMatriculas();
	}
	
	//Va post
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addMatricula(Object o) {
		
		EstudianteDAO eRepo = DAOFactoryImpl.getInstance().getEstudianteDAO();
//		CarreraDAO cRepo = new CarreraDAO();
		CarreraDAO cRepo = DAOFactoryImpl.getInstance().getCarreraDAO();
		MatriculaDAO mRepo = DAOFactoryImpl.getInstance().getMatriculaDAO();
//		System.out.println(o);
		int idEstudiante = (int)((Map) o).get("idEstudiante");
		int idCarrera = (int)((Map) o).get("idCarrera");

		Estudiante e = eRepo.getEstudiante(idEstudiante);
//		Carrera c = cRepo.getCarrera(idCarrera);
		Carrera c = cRepo.getById(idCarrera);
		
		if((e == null)||(c == null)) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
					   entity("No se pudo persistir la matricula").build();	
		}
		
		Matricula m = new Matricula(e, c);
		mRepo.add(m);
		return Response.status(201).entity(e).build();
	}
	
	@GET
	@Path("/reporte")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getReporteEgresados() {
		List<ReporteMatricula> lista = DAOFactoryImpl.getInstance().getMatriculaDAO().reporteEgresados();
		return Response.status(200).entity(lista).build();
	}
	
}
