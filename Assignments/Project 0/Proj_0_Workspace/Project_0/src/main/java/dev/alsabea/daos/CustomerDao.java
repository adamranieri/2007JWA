package dev.alsabea.daos;

import java.util.List;

import dev.alsabea.entities.Customer;

public interface CustomerDao extends CRUD<Customer>{
	
	
	List<Customer> retrieveAll();
	List<Customer> retrieveByUsername(String name);
	
}
