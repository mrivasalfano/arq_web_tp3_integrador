package edu.daos;

public class DAOFactoryImpl implements DAOFactory {
	
	private static DAOFactoryImpl DAOFactoryImpl;
	
	private DAOFactoryImpl() {
		
	}
	
	public static DAOFactoryImpl getInstance() {
		if(DAOFactoryImpl==null)
			DAOFactoryImpl=new DAOFactoryImpl();
		
		return DAOFactoryImpl;
	}
	
	@Override
	public CarreraDAO getCarreraDAO() {
		return CarreraDAO.getInstance();
	}

	@Override
	public EstudianteDAO getEstudianteDAO() {
		return EstudianteDAO.getInstance();
	}

	@Override
	public MatriculaDAO getMatriculaDAO() {
		return MatriculaDAO.getInstance();
	}

}
