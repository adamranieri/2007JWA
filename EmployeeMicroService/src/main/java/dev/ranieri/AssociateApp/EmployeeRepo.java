package dev.ranieri.AssociateApp;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface EmployeeRepo extends CrudRepository<Employee,Integer>{

}
