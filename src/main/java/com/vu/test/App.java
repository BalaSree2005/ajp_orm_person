package com.vu.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class App {
    public static void main(String[] args) {
        // Configure Hibernate
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

        // Create Operation
//        createPerson(factory);

        // Read Operation
        readPerson(factory, 30); // Pass the ID of the person to fetch

        // Update Operation
//        updatePerson(factory, 30, "Updated Address", "9876543210");

        // Delete Operation
//        deletePerson(factory, 22);

        // Close the factory
        factory.close();
    }

    // Create
    public static void createPerson(SessionFactory factory) {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        // Creating a new person object and persisting it in the database
        person p = new person(22, "gita", "vij", "8529671349");
        session.persist(p);

        tx.commit();
        session.close();
        System.out.println("Person created: " + p);
    }

    // Read
    public static void readPerson(SessionFactory factory, int id) {
        Session session = factory.openSession();

        // Fetching the person object using the primary key (id)
        person p = session.get(person.class, id);
        if (p != null) {
            System.out.println("Person found: " + p);
        } else {
            System.out.println("Person with ID " + id + " not found.");
        }

        session.close();
    }

    // Update
    public static void updatePerson(SessionFactory factory, int id, String newAddress, String newPhone) {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        // Fetching the person object to update
        person p = session.get(person.class, id);
        if (p != null) {
            p.setAddress(newAddress); // Updating the address field
            p.setPhone(newPhone);     // Updating the phone field
            session.merge(p);         // Using merge to update the entity
            System.out.println("Person updated: " + p);
        } else {
            System.out.println("Person with ID " + id + " not found for update.");
        }

        tx.commit();
        session.close();
    }

    // Delete
    public static void deletePerson(SessionFactory factory, int id) {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        // Fetching the person object to delete
        person p = session.get(person.class, id);
        if (p != null) {
            session.remove(p); // Using remove to delete the entity
            System.out.println("Person deleted: " + p);
        } else {
            System.out.println("Person with ID " + id + " not found for deletion.");
        }

        tx.commit();
        session.close();
    }
}
