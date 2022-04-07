package com.example.musify.hibernate;

import com.example.musify.entity.Artist;
import com.example.musify.entity.Band;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class ArtistRepositoryHibernate {

    public List<Artist> getAllArtists() {
        Transaction transaction = null;
        List<Artist> artists = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            artists = session.createNamedQuery("getAllArtists", Artist.class).getResultList();
            for (Artist a: artists
                 ) {
                for (Band b: a.getBands());

            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.getMessage();
        } finally {
            HibernateUtil.shutdown();
        }
        return artists;
    }

    public Artist getArtistById(int id) {
        Artist artist = null;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createNamedQuery("getArtistById", Artist.class);
            query.setParameter("id", id);
            artist = (Artist) query.getResultList().get(0);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.getMessage();
        } finally {
            HibernateUtil.shutdown();
        }
        return artist;
    }

    public void addArtist(Artist artist) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(artist);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.getMessage();
        } finally {
            HibernateUtil.shutdown();
        }
    }
}
