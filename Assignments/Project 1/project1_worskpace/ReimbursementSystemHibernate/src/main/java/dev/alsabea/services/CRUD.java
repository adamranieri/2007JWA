package dev.alsabea.services;

public interface CRUD<T> {

	
	long createInstance(T t);
	
	T retrieveById(long key);
	
	boolean update (T t);
	
	boolean deleteById (long key);
}
