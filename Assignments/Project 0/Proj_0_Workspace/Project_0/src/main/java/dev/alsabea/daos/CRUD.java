package dev.alsabea.daos;


/*
 * Create, Retrieve, Update, Delete
 */
public interface CRUD <T> {
	
	int create( T t) ;
	
	boolean delete(int id);
	
	T retrieveById(int id);
	
	boolean update ( T t);

	
	

}
