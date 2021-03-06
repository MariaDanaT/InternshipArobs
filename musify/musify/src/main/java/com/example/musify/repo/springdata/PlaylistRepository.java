package com.example.musify.repo.springdata;

import com.example.musify.entity.Playlist;
import com.example.musify.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Integer> {
    List<Playlist> findByTypeLike(String type);

    List<Playlist> findByUserIs(User user);
}
