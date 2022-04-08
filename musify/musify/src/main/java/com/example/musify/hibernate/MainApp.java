package com.example.musify.hibernate;

import com.example.musify.entity.Artist;
import com.example.musify.entity.Band;

import java.util.List;

public class MainApp {
    public static void main(String[] args) {

        ArtistRepositoryHibernate artistRepositoryHibernate = new ArtistRepositoryHibernate();
        List<Artist> artists = artistRepositoryHibernate.getAllArtists();
        Artist artist = artistRepositoryHibernate.getArtistById(1);
        artists.forEach(x -> System.out.println(x.toString()));
        System.out.println(artist.toString());
        System.out.println("ADELE end activity date:" + artistRepositoryHibernate.getArtistById(2).getActivityEndDate());
//        String firstName, String lastName, String stageName, Date birthday, String activityStartDate, String activityEndDate, String type
//        artistRepositoryHibernate.addArtist(new Artist("Adele","Adkins","Adele",Date.valueOf("1988-05-05"),"2006", null, "person"));
//        artistRepositoryHibernate.addArtist(new Artist("Andrei","Maria","Smiley",Date.valueOf("1983-07-27"),"2001",null,"person"));
        Artist artistToUpdate = artistRepositoryHibernate.getArtistById(3);
//        artistToUpdate.setFirstName("Andrei Tiberiu");
//        System.out.println("new first name: " + artistToUpdate.getFirstName() + "\n" + artistRepositoryHibernate.updateArtist(artistToUpdate).toString());
//        artistToUpdate.setFirstName("Andrei");
//        System.out.println("new first name: " + artistToUpdate.getFirstName() + "\n" + artistRepositoryHibernate.updateArtist(artistToUpdate).toString());
//        System.out.println("Artist deleted: "+artistRepositoryHibernate.deleteArtist(4));
        Band band = new Band(1,"BandForTest","location1", "200",null);
        List<Band> bandsList = artistRepositoryHibernate.getBandsForAnArtist(artist);
//        List<Artist> artistsList = artistRepositoryHibernate.getBandsForAnArtist(artistToUpdate, band);
        bandsList.forEach(System.out::println);
        HibernateUtil.shutdown();
    }
}

