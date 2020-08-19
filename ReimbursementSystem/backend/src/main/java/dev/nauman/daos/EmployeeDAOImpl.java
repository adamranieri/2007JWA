package dev.nauman.daos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import dev.nauman.entities.Employee;
import dev.nauman.entities.Manager;
import dev.nauman.utils.HibernateUtil;

public class EmployeeDAOImpl implements EmployeeDAO {

	private static SessionFactory sf = HibernateUtil.getSessionFactory();
	private static EmployeeDAOImpl edao = null;
	
	private EmployeeDAOImpl() {
	}

	public static EmployeeDAOImpl getEmployeeDAOImpl() {
		if(edao == null) {
			return new EmployeeDAOImpl();
		}
		return null;
	}
	@Override
	public Employee getEmployeeById(int id) {
		try(Session session =sf.openSession()) {
			return session.get(Employee.class, id);
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public List<Employee> getAllEmployees() {
		try(Session session =sf.openSession()) {
			Criteria criteria = session.createCriteria(Employee.class);
			return criteria.list();
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public Employee updateEmployee(Employee employee) {
		if(this.getEmployeeById(employee.getid()) == null) {
			return null;
		}
		try(Session session =sf.openSession()) {
			session.beginTransaction();
			
			session.update(employee);
			
			session.getTransaction().commit();
			return employee;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Employee> getAllManagers() {
		try(Session session =sf.openSession()) {
			Criteria criteria = session.createCriteria(Employee.class);
			criteria.add(Restrictions.eq("permission",true));
			return criteria.list();
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}


}
