package edu.controllers;

import java.util.List;

import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import edu.daos.DAOFactoryImpl;
import edu.daos.EstudianteDAO;
import edu.entities.Estudiante;

@Path("/estudiantes")
public class EstudianteRestController {	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Estudiante> getEstudiantes(@DefaultValue("") @QueryParam("orden") String orden) {
		if(orden.isEmpty())
			return DAOFactoryImpl.getInstance().getEstudianteDAO().getEstudiantes();
		else
			return DAOFactoryImpl.getInstance().getEstudianteDAO().getEstudiantes(orden);
		
	}
	
	@GET
	@Path("/lu/{nro}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEstudiantePorLibreta(@PathParam("nro") int numero) {
		Estudiante e = DAOFactoryImpl.getInstance().getEstudianteDAO().getEstudiantePorLibretaUniversitaria(numero);
		
		if(e != null)
			return Response.status(200).entity(e).build();
		
		return Response.status(404).entity("No se encuentra estudiante con este número de libreta").build();
	}
	
	@GET
	@Path("/genero/{genero}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEstudiantePorGenero(@PathParam("genero") String genero) {
		List<Estudiante> e = DAOFactoryImpl.getInstance().getEstudianteDAO().getEstudiantesPorGenero(genero);
		
		return Response.status(200).entity(e).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addEstudiante(Estudiante e) {
		DAOFactoryImpl.getInstance().getEstudianteDAO().add(e);
		return Response.status(201).entity(e).build();
	}
	
}
