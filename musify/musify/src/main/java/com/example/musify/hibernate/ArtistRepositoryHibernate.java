package com.example.musify.hibernate;

import com.example.musify.entity.Person;
import com.example.musify.entity.Band;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class ArtistRepositoryHibernate {

    public List<Person> getAllArtists() {
        Transaction transaction = null;
        List<Person> people = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            people = session.createNamedQuery("getAllArtists", Person.class).getResultList();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.getMessage();
        }
        return people;
    }

    public Person getArtistById(int id) {
        Person person = null;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createNamedQuery("getArtistById", Person.class);
            query.setParameter("id", id);
            person = (Person) query.getResultList().get(0);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.getMessage();
        }
        return person;
    }

    public void addArtist(Person person) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(person);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.getMessage();
        }
    }

    public Person updateArtist(Person person) {
        Transaction transaction = null;
        Person personUpdated = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            personUpdated = session.get(Person.class, person.getId());
            if (personUpdated != null) {
                personUpdated.setFirstName(person.getFirstName());
                personUpdated.setLastName(person.getLastName());
                personUpdated.setStageName(person.getStageName());
                personUpdated.setBirthday(person.getBirthday());
                personUpdated.setActivityStartDate(person.getActivityStartDate());
                personUpdated.setActivityEndDate(person.getActivityEndDate());
                session.update(personUpdated);
            }
            transaction.commit();
            return personUpdated;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.getMessage();
        }
        return personUpdated;
    }

    public Person deleteArtist(int id) {
        Transaction transaction = null;
        Person personDeleted = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            personDeleted = session.get(Person.class, id);
            if (personDeleted != null)
                session.delete(personDeleted);
            transaction.commit();
            return personDeleted;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.getMessage();
        }
        return personDeleted;
    }

    public List<Band> getBandsForAnArtist(Person person) {
        Transaction transaction = null;
        List<Band> bands = new ArrayList<>();
        Person personFromDB = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Artist a JOIN FETCH a.bands AS b WHERE a.id = :ida").setParameter("ida", person.getId());
            if (query.getSingleResult() != null)
                personFromDB = (Person) query.getSingleResult();
            if (personFromDB.getBands() != null)
                bands.addAll(personFromDB.getBands());

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.getMessage();
        }
        return bands;
    }

}
