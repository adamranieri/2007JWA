package dev.kusch.daos;

import java.util.List;

import javax.persistence.OptimisticLockException;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import dev.kusch.entities.Employee;
import dev.kusch.utils.HibernateUtil;

public class EmployeeDAOHibernate implements EmployeeDAO{
	
	private static SessionFactory sf = HibernateUtil.getSessionFactory();
	private static EmployeeDAOHibernate dao = null;
	
	private EmployeeDAOHibernate() {
		super();
	}
	
	public static EmployeeDAOHibernate getEmployeeDAOHibernate() {
		if (dao == null) {
			dao = new EmployeeDAOHibernate();
			return dao;
		}
		
		return dao;
	}

	@Override
	public Employee getEmployeeById(int id) {
		try (Session sess = sf.openSession()) {
			sess.beginTransaction();
			Employee employee = sess.get(Employee.class, id);
			sess.getTransaction().commit();
			return employee;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Employee> getEmployeeByUser(String username) {
		try (Session sess = sf.openSession()) {
			sess.beginTransaction();
			Criteria crit = sess.createCriteria(Employee.class);
			crit.add(Restrictions.like("username", username));
			List<Employee> employees = crit.list();
			sess.getTransaction().commit();
			return employees;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		try (Session sess = sf.openSession()) {
			sess.beginTransaction();
			sess.update(employee);
			sess.getTransaction().commit();
			return employee;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		} catch (OptimisticLockException f) {
			f.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Employee> getEmployeeByPass(String password) {
		try (Session sess = sf.openSession()) {
			sess.beginTransaction();
			Criteria crit = sess.createCriteria(Employee.class);
			crit.add(Restrictions.like("password", password));
			List<Employee> employees = crit.list();
			sess.getTransaction().commit();
			return employees;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}
}
