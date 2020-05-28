package main;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import Object.CheckExaminationPaper;
import Object.Classes;
import Object.Course;
import Object.CurrentCourse;
import Object.Schedule;
import Object.Student;
import Util.HibernateUtil;

public class Main {
	public static void main(String[] args) {
		Transaction transaction = null;
		CheckExaminationPaper cep=null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            cep=(CheckExaminationPaper)session.get(CheckExaminationPaper.class, 1);
            System.out.println(cep.getCepInfos().get(0).getCurrentCourse().getCurrentCourseID());
            // commit transaction
            transaction.commit();
       } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
