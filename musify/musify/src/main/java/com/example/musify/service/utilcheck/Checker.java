package com.example.musify.service.utilcheck;

import com.example.musify.entity.*;
import com.example.musify.security.JwtUtils;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import java.util.Optional;

public class Checker {
    public static User getUserIfExists(Optional<User> optional, Integer idUser) {
        return optional.orElseThrow(() -> new ResourceNotFoundException("There is no user with id = " + idUser));
    }

    public static Band getBandIfExists(Optional<Band> optional, Integer idBand) {
        return optional.orElseThrow(() -> new ResourceNotFoundException("There is no band with id = " + idBand));
    }

    public static Person getPersonIfExists(Optional<Person> optional, Integer idPerson) {
        return optional.orElseThrow(() -> new ResourceNotFoundException("There is no person with id = " + idPerson));
    }

    public static Album getAlbumIfExists(Optional<Album> optional, Integer idAlbum) {
        return optional.orElseThrow(() -> new ResourceNotFoundException("There is no album with id = " + idAlbum));
    }

    public static Song getSongIfExists(Optional<Song> optional, Integer idSong) {
        return optional.orElseThrow(() -> new ResourceNotFoundException("There is no song with id = " + idSong));

    }

    public static Song getSongIfExistsInPlaylist(Optional<Song> optional) {
        return optional.orElseThrow(() -> new ResourceNotFoundException("This song doesn't exist in the playlist!"));
    }

    public static Playlist getPlaylistIfExists(Optional<Playlist> optional, Integer idPlaylist) {
        return optional.orElseThrow(() -> new ResourceNotFoundException("There is no playlist with id = " + idPlaylist));
    }

    public static boolean isAdmin() {
        return JwtUtils.getUserRoleFromSession().equals("admin");
    }
}
