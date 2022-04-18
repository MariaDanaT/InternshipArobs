package com.example.musify.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "songs")
@Getter
@Setter
@NoArgsConstructor
public class Song {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "title")
    private String title;
    @Column(name = "duration")
    private Time duration;
    @Column(name = "creation_date")
    private Date creationDate;

    @OneToMany(mappedBy = "song")
    private Set<AlternativeSongTitle> alternativeSongTitles = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "album_id")
    private Album album;

    @ManyToMany
    @JoinTable(name = "songs_persons",
            joinColumns = {@JoinColumn(name = "song_id")},
            inverseJoinColumns = {@JoinColumn(name = "person_id")})
    private Set<Person> contributors = new HashSet<>();

    @OneToMany(mappedBy = "songFromPlaylist")
    private Set<PlaylistsSongs> playlistsSongs = new HashSet<>();

    public void addAlternativeSongTitle(AlternativeSongTitle alternativeSongTitle) {
        this.alternativeSongTitles.add(alternativeSongTitle);
        alternativeSongTitle.setSong(this);
    }

    public void removeAlternativeSongTitle(AlternativeSongTitle alternativeSongTitle) {
        this.alternativeSongTitles.remove(alternativeSongTitle);
        alternativeSongTitle.setSong(null);
    }
}
