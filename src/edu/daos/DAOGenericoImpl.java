package edu.daos;

import java.util.List;

import javax.persistence.EntityManager;

import edu.controllers.EMF;

/**
 * Clase generica para operaciones CRUD,
 * se espera que los DAOS concretos hereden de esta clase
 * @author teamBolivar
 * @version v1.0
 * @since   2020-10-05
 * @param <T> es de tipo, es el DAO que quiera implementar
 */
public class DAOGenericoImpl<T> implements DAOGenerico<T>{	
	private Class<T> entity;
	
	/**
	 * Contructor que recibe el entidad correspondiente al DAO
	 * @param entity
	 */
	public DAOGenericoImpl(Class<T> entity) {
		this.entity = entity;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> getAll() {
		EntityManager em = EMF.getEntityManager();
		List<T> list = em.createQuery("SELECT e FROM " + entity.getName() + " e").getResultList();
		em.close();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getById(int id) {
		EntityManager em = EMF.getEntityManager();
		T t = em.find(this.entity, id);
		em.close();
		return t;
	}

	@Override
	public void add(T entity) {
		EntityManager em = EMF.getEntityManager();
		em.getTransaction().begin();
		em.persist(entity);
		em.getTransaction().commit();
		em.close();
	}

}
