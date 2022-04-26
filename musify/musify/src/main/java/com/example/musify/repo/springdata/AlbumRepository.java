package com.example.musify.repo.springdata;

import com.example.musify.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Integer> {
    List<Album> findByTitleContainingIgnoreCase(String title);

    List<Album> findByGenreContainingIgnoreCase(String genre);
}
