package dev.alsabea.doas;

public interface CRUD<T> {

	long createInstance(T t);
	
	T retrieveById(long key);
	
	boolean update (T t);
	
	boolean deleteById (long key);
	
	 
	
}
