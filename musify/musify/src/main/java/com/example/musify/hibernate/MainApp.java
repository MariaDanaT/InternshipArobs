package com.example.musify.hibernate;

import com.example.musify.entity.Artist;
import org.hibernate.Session;

import java.sql.Date;

public class MainApp {
    public static void main(String[] args) {
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        session.beginTransaction();
//
//        // Check database version
//        String sql = "select version()";
//
//        String result = (String) session.createNativeQuery(sql).getSingleResult();
//        System.out.println("VERSION:" + result);
//

        ArtistRepositoryHibernate artistRepositoryHibernate = new ArtistRepositoryHibernate();
        artistRepositoryHibernate.getAllArtists().forEach(x -> System.out.println(x.toString()));
//        System.out.println("The artist with id=1:\n " + artistRepositoryHibernate.getArtistById(1));
//       artistRepositoryHibernate.addArtist(new Artist("firstName", "lastName", "stageName", Date.valueOf("2000,02,23"), "2015", "", "person"));
//        List<Artist> artists = getArtists();
////        List<Artist> artists = artistRepositoryHibernate.getAllArtists();
//        System.out.println("Afisare din main:");
//        for (Artist a: artists
//        ) {
//            for (Band b: a.getBands()
//            ) {
//                System.out.println(b.getName());
//            }
//        }
//        System.out.println(artists);
//        session.getTransaction().commit();
//        session.close();
//
//        HibernateUtil.shutdown();
    }
}

