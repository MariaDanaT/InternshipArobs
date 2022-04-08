package com.example.musify.hibernate;

import com.example.musify.entity.Artist;
import com.example.musify.entity.Band;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ArtistRepositoryHibernate {

    public List<Artist> getAllArtists() {
        Transaction transaction = null;
        List<Artist> artists = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            artists = session.createNamedQuery("getAllArtists", Artist.class).getResultList();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.getMessage();
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
        }
    }

    public Artist updateArtist(Artist artist) {
        Transaction transaction = null;
        Artist artistUpdated = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            artistUpdated = session.get(Artist.class, artist.getId());
            if (artistUpdated != null) {
                artistUpdated.setFirstName(artist.getFirstName());
                artistUpdated.setLastName(artist.getLastName());
                artistUpdated.setStageName(artist.getStageName());
                artistUpdated.setBirthday(artist.getBirthday());
                artistUpdated.setActivityStartDate(artist.getActivityStartDate());
                artistUpdated.setActivityEndDate(artist.getActivityEndDate());
                artistUpdated.setType(artist.getType());
                session.update(artistUpdated);
            }
            transaction.commit();
            return artistUpdated;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.getMessage();
        }
        return artistUpdated;
    }

    public Artist deleteArtist(int id) {
        Transaction transaction = null;
        Artist artistDeleted = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            artistDeleted = session.get(Artist.class, id);
            if (artistDeleted != null)
                session.delete(artistDeleted);
            transaction.commit();
            return artistDeleted;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.getMessage();
        }
        return artistDeleted;
    }

    public List<Band> getBandsForAnArtist(Artist artist) {
        Transaction transaction = null;
        List<Band> bands = new ArrayList<>();
        Artist artistFromDB = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Artist a JOIN FETCH a.bands AS b WHERE a.id = :ida").setParameter("ida", artist.getId());
            if (query.getSingleResult() != null)
                artistFromDB = (Artist) query.getSingleResult();
            if (artistFromDB.getBands() != null)
                bands.addAll(artistFromDB.getBands());

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
