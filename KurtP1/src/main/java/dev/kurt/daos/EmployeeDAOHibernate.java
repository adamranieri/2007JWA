package dev.kurt.daos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import dev.kurt.entities.Employee;
import dev.kurt.entities.Manager;
import dev.kurt.entities.Reimbursement;
import dev.kurt.utils.HibernateUtil;

public class EmployeeDAOHibernate implements EmployeeDAO {

	private static SessionFactory sf = HibernateUtil.getSessionFactory();
	
	
	@Override
	public Employee createEmployee(Employee employee) {
		Session sess = sf.openSession();
		sess.beginTransaction();		
		
		sess.save(employee);
		
		sess.getTransaction().commit();
		sess.close();
		
		return employee;
	}

	@Override
	public Employee getEmployeeById(int id) {
		Session sess = sf.openSession();	
		Employee employee = sess.get(Employee.class, id);
		
		sess.close();
		
		return employee;
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		Session sess = sf.openSession();
		sess.beginTransaction();		
		
		sess.update(employee);
		
		sess.getTransaction().commit();
		sess.close();
		
		return employee;
	}

	@Override
	public boolean deleteEmployee(Employee employee) {
		Session sess = sf.openSession();
		sess.beginTransaction();		
		
		sess.delete(employee);
		
		sess.getTransaction().commit();
		sess.close();
		
		return true;
	}

	@Override
	public List<Employee> getAllEmployees() {
		Session sess = sf.openSession();
		Criteria crit = sess.createCriteria(Employee.class);
		List<Employee> employees = crit.list();
		
		sess.close();
		
		return employees;
	}

	@Override
	public Employee getEmployeeByLogin(String user, String pass) {
		Session sess = sf.openSession();	
		Criteria crit = sess.createCriteria(Employee.class)
				.add(Restrictions.eq("empUsername",user))
				.add(Restrictions.eq("empPassword",pass));
		List<Employee> employees = crit.list();
		
		sess.close();
		
		return employees.get(0);
	}

	@Override
	public List<Employee> getEmployeesByManager(Manager manager) {
		Session sess = sf.openSession();	
		Criteria crit = sess.createCriteria(Employee.class);
		crit.add(Restrictions.like("manager",manager));
		List<Employee> employees = crit.list();
		
		sess.close();
		
		return employees;
	}

}
