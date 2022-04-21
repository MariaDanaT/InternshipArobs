package com.example.musify.repo.springdata;

import com.example.musify.entity.Album;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Integer> {
    @Query("SELECT a FROM Album a WHERE a.title iLIKE %:title%")
    List<Album> findByTitleContainingIgnoreCase(@Param("title") String title);

    @Query("SELECT a FROM Album a WHERE a.genre iLIKE %:genre%")
    List<Album> findByGenreContainingIgnoreCase(@Param("genre") String genre);
}
