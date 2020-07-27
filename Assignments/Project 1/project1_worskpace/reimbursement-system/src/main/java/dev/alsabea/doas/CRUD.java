package dev.alsabea.doas;

public interface CRUD<T> {

	int createInstance(T t);
	
	T retrieveById(int key);
	
	boolean update (T t);
	
	boolean deleteById (int key);
	
	 
	
}
