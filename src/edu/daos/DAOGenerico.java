package edu.daos;

import java.util.List;

public interface DAOGenerico<T> {
	public List<T> getAll();
	public T getById(int id);
	public void add(T entity);
}
