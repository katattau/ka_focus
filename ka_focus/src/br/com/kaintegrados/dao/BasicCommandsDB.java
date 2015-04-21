package br.com.kaintegrados.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Comandos básicos e padrões que utilizo em comandos SQL
 * @author katattau
 *
 */
public interface BasicCommandsDB {

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("amigo_secreto");
	EntityManager manager = factory.createEntityManager();
	
	public abstract boolean add(Object o);
	public abstract boolean update(Object o);
	public abstract List select();
	public abstract List select(Object o);
	public abstract Object select(int id);
	public abstract boolean delete(Object o);
	
}
