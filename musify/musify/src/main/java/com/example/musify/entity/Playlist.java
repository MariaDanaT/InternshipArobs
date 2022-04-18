package com.example.musify.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "playlists")
@Getter
@Setter
@NoArgsConstructor
public class Playlist {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "type")
    private String type;
    @Column(name = "created_date")
    private Date createdDate;
    @Column(name = "last_update_date")
    private Date lastUpdateDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_user")
    private User user;

    @ManyToMany(mappedBy = "followedPlaylists")
    private Set<User> users = new HashSet<>();

    @OneToMany(mappedBy = "playlist")
    private Set<PlaylistsSongs> playlistsSongs = new HashSet<>();


}
