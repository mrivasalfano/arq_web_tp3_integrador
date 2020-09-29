package edu.controllers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import edu.daos.DAOFactoryImpl;
import edu.entities.Carrera;
import edu.entities.Estudiante;
import edu.util.ReporteCarrera;

@Path("/carreras")
public class CarreraRestController {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Carrera> getCarreras() {
		return DAOFactoryImpl.getInstance().getCarreraDAO().getAll();
	}
	
	@GET
	@Path("/reporte")
	@Produces(MediaType.APPLICATION_JSON)
	public ReporteCarrera getCarreras(@DefaultValue("") @QueryParam("filtro") String filtro) {
		if (filtro.equals("inscriptos"))
			return DAOFactoryImpl.getInstance().getCarreraDAO().getCarrerasEstudiantesIns();
		
		return null;			
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCarrera(@PathParam("id") int id) {
		Carrera c = DAOFactoryImpl.getInstance().getCarreraDAO().getById(id);
		
		if(c == null) {
			return Response.status(404).entity("No se encuentra esta carrera").
					type(MediaType.TEXT_PLAIN).build();
		}
		
		return Response.status(200).entity(c).build();
	}
	
	@GET
	@Path("/{id}/estudiantes")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCarrera(@PathParam("id") int id, @DefaultValue("") @QueryParam("ciudad") String ciudad) {
		Carrera c1= DAOFactoryImpl.getInstance().getCarreraDAO().getById(id);
		List<Estudiante> listaEstudiantes = new ArrayList<Estudiante>();
		if(c1 != null) {
			String cNombre = c1.getNombre();
			if(ciudad.isEmpty()) {
				//TODO agregar el metodo comentado
//				listaEstudiantes = DAOFactoryImpl.getInstance().getCarreraDAO().getCarrerasEstudiantes();
				
			} else {
				listaEstudiantes = DAOFactoryImpl.getInstance().getCarreraDAO().getEstudiantesCarreraCiudad(cNombre, ciudad);
			}
		}
		return Response.status(200).entity(listaEstudiantes).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCarrera(Carrera c) {
		DAOFactoryImpl.getInstance().getCarreraDAO().add(c);
		return Response.status(201).entity(c).build();
	}
	
}
