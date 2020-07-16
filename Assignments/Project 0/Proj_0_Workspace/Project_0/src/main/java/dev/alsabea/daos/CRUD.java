package dev.alsabea.daos;

public interface CRUD <T> {
	
	boolean create( T t) ;
	
	boolean delete(int id);
	
	T retrieveById(int id);
	
	boolean update ( T t);
	

}
