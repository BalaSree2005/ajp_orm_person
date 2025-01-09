package com.vu.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;



public class App {
    public static void main(String[] args) {
    	SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

		person p1 = new person(28, "abc", "VIJ", "8872268603");
		person p2 = new person(14, "def", "HYD", "9687795276");
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();

		session.persist(p2);

		tx.commit();

		factory.close();
    }
}
