package com.example.musify.service.utilcheck;

import com.example.musify.entity.*;
import com.example.musify.security.JwtUtils;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import java.util.Optional;

public class Checker {
    public static User getUserIfExists(Optional<User> optional, Integer idUser) {
        if (optional.isEmpty()) {
            throw new ResourceNotFoundException("There is no user with id = " + idUser);
        }
        return optional.get();
    }

    public static Band getBandIfExists(Optional<Band> optional, Integer idBand) {
        if (optional.isEmpty()) {
            throw new ResourceNotFoundException("There is no band with id = " + idBand);
        }
        return optional.get();
    }

    public static Person getPersonIfExists(Optional<Person> optional, Integer idPerson) {
        if (optional.isEmpty()) {
            throw new ResourceNotFoundException("There is no person with id = " + idPerson);
        }
        return optional.get();
    }

    public static Album getAlbumIfExists(Optional<Album> optional, Integer idAlbum) {
        if (optional.isEmpty()) {
            throw new ResourceNotFoundException("There is no album with id = " + idAlbum);
        }
        return optional.get();
    }

    public static Song getSongIfExists(Optional<Song> optional, Integer idSong) {
        if (optional.isEmpty()) {
            throw new ResourceNotFoundException("There is no song with id = " + idSong);
        }
        return optional.get();
    }

    public static Song getSongIfExistsInPlaylist(Optional<Song> optional) {
        if (optional.isEmpty()) {
            throw new ResourceNotFoundException("This song doesn't exist in the playlist! ");
        }
        return optional.get();
    }

    public static Playlist getPlaylistIfExists(Optional<Playlist> optional, Integer idPlaylist) {
        if (optional.isEmpty()) {
            throw new ResourceNotFoundException("There is no playlist with id = " + idPlaylist);
        }
        return optional.get();
    }

    public static boolean isAdmin(){
        if(JwtUtils.getUserRoleFromSession().equals("admin"))
            return true;
        return false;
    }
}
