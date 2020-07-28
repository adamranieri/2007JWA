package dev.ranieri.daos;

import java.util.List;

import dev.ranieri.entities.Director;

public interface DirectorDAO {
	
	// CREATE
	Director createDirector(Director director);
	
	// READ
	Director getDirectorById(int id);
	List<Director> getDirectorsByName(String name);
	List<Director> getAllDirectors();
		
	//UPDATE
	Director updateDirector(Director director);
	
	//DELETE
	boolean deleteDirector(Director director);
	
	

}
