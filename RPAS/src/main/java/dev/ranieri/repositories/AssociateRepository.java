package dev.ranieri.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import dev.ranieri.entities.Associate;

@Component
@Repository // essentially a DAO. Functionally the same.   Type of entity, type of the primary key
public interface AssociateRepository extends CrudRepository<Associate,Integer>{

	List<Associate> findByName(String name);
	List<Associate> findByPointsBetween(int min,int max);// Spring data will read your method signatures
	// to create queries
}
