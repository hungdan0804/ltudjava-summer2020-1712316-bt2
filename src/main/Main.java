package main;


import org.hibernate.Session;
import org.hibernate.Transaction;
import Util.HibernateUtil;

public class Main {
	public static void main(String[] args) {
		Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
      
            // commit transaction
            transaction.commit();
       } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
