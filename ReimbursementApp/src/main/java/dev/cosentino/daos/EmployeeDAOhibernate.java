package dev.cosentino.daos;

import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import dev.cosentino.entities.Employee;
import dev.cosentino.entities.Reimbursement;
import dev.cosentino.services.EmployeeService;
import dev.cosentino.utils.HibernateUtil;

public class EmployeeDAOhibernate implements EmployeeDAO {
	
	private static SessionFactory sf = HibernateUtil.getSessionFactory();;
	private static EmployeeDAO edao = null;

	public EmployeeDAOhibernate() {
		super();
	}
	
	public static EmployeeDAO getEmployeeDAOhibernate() {
		if(edao == null) {
			edao = new EmployeeDAOhibernate();
		}
		return edao;
	}
	
	public List<Employee> getAllEmployees() {
		try(Session sess = sf.openSession()){
			Criteria crit = sess.createCriteria(Employee.class);
			crit.add(Restrictions.in("title", "Employee"));
			crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			
			List<Employee> employees = crit.list();
			return employees;
		}
	}

	public Employee getEmployeeById(int id) {
		Session sess = sf.openSession();
		
		Employee employee = sess.get(Employee.class, id);
		
		sess.close();
		return employee;
	}

	public Employee getEmployeeByUsername(String username) {
		try(Session sess = sf.openSession()){
			Criteria crit = sess.createCriteria(Employee.class);
			crit.add(Restrictions.like("username", username));
			crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			List<Employee> employees = crit.list();
			
			return employees.get(0);
		}
	}

	public List<Employee> getEmployeeByName(String name) {
		try(Session sess = sf.openSession()){
			Criteria crit = sess.createCriteria(Employee.class);
			crit.add(Restrictions.like("name", name));
			crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			List<Employee> employees = crit.list();
			
			return employees;
		}
	}

	public Employee updateEmployee(Employee employee) {
		Session sess = sf.openSession();
		sess.beginTransaction();
		sess.update(employee);
		sess.getTransaction().commit();
		sess.close();
		return employee;
	}

	@Override
	public List<Employee> getAllManagers() {
		try(Session sess = sf.openSession()){
			Criteria crit = sess.createCriteria(Employee.class);
			crit.add(Restrictions.in("title", "Manager"));
			crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			
			List<Employee> employees = crit.list();
			return employees;
		} 
	}


}
