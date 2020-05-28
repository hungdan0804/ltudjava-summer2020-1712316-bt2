package Util;


import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;


public class HibernateUtil {
	private static final SessionFactory sessionFactory = buildSessionFatory();
	private static SessionFactory buildSessionFatory() {
		 try {
	          ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
	                  .configure("/main/hibernate.cfg.xml").build();
	  
	          Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();
	          return metadata.getSessionFactoryBuilder().build();
	      } catch (Throwable ex) {
	          System.err.println("Initial SessionFactory creation failed." + ex);
	          throw new ExceptionInInitializerError(ex);
	      }
	}
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
}
