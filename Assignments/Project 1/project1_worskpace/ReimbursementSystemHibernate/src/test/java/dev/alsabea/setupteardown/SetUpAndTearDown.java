package dev.alsabea.setupteardown;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import dev.alsabea.connection.HibernateConnectionEstablisher;

public class SetUpAndTearDown {

	private static SessionFactory sf= HibernateConnectionEstablisher.getSessionFactory();
	
	public static void setup() {
		
		try(Session sess = sf.openSession()){
			sess.getTransaction().begin();
			@SuppressWarnings("rawtypes")
			Query q= sess.createSQLQuery("CALL reimbursement_system_db.procedure_setup_proj1_hibernate_db");
			q.executeUpdate();
			sess.getTransaction().commit();
		}
		
		try(Session sess = sf.openSession()){
			sess.getTransaction().begin();
			@SuppressWarnings("rawtypes")
			Query q= sess.createSQLQuery("CALL reimbursement_system_db.procedure_populate_proj1_hibernate_db");
			q.executeUpdate();
			sess.getTransaction().commit();
		}
		
	}
	
	
	public static void teardown() {
		
		try(Session sess = sf.openSession()){
			sess.getTransaction().begin();
			@SuppressWarnings("rawtypes")
			Query q= sess.createSQLQuery("CALL reimbursement_system_db.procedure_tear_down_hibernate_project1_db");
			q.executeUpdate();
			sess.getTransaction().commit();
		}
	}
	
}
