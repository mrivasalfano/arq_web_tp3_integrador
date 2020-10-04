package edu.daos;

/**
 * Retorna los distintos DAOS
 * @author teamBolivar
 * @version v1.0
 * @since   05/10/20
 */
public class DAOFactoryImpl implements DAOFactory {
	
	private static DAOFactoryImpl DAOFactoryImpl;
	
	private DAOFactoryImpl() {
		
	}
	
	/**
	 * Singleton
	 * @return Instancia de DAOFactoryImpl
	 */
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
