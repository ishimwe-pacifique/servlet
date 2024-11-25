package util;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import model.Student;
import model.UserAccount;

public class HibernateUtil {

	
	private static SessionFactory sessionFactory = null;
	
	public static SessionFactory getSession() {
		
		if(sessionFactory == null) {
			Configuration conf = new Configuration();
			
			Properties settings = new Properties();
			
			settings.setProperty(Environment.DRIVER, "org.postgresql.Driver");
			settings.setProperty(Environment.URL, "jdbc:postgresql://localhost:5432/webThursday");
			settings.setProperty(Environment.USER, "postgres");
			settings.setProperty(Environment.PASS, "");
			settings.setProperty(Environment.HBM2DDL_AUTO, "create");
			settings.setProperty(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
			settings.setProperty(Environment.SHOW_SQL, "true");
			
			conf.setProperties(settings);
			
			conf.addAnnotatedClass(UserAccount.class);
			conf.addAnnotatedClass(Student.class);
			
			sessionFactory = conf.buildSessionFactory();
			
			return sessionFactory;
			
		}else {
			return sessionFactory;
		}
	}
}
