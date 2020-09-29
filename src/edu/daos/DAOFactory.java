package edu.daos;

public interface DAOFactory {
	public CarreraDAO getCarreraDAO();
	public EstudianteDAO getEstudianteDAO();
	public MatriculaDAO getMatriculaDAO();
}
