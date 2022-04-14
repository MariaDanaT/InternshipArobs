package com.example.musify.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "alternative_songs_titles")
@Getter
@Setter
@NoArgsConstructor
public class AlternativeSongTitle {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "alternative_title")
    private String alternativeTitle;
    @Column(name = "language")
    private String language;

    @ManyToOne
    @JoinColumn(name = "song_id")
    private Song song;


}
