package com.example.musify.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "country")
    private String country;
    @Column(name = "role")
    private String role;
    @Column(name = "deleted")
    private Boolean deleted;

    @OneToMany(mappedBy = "user")
    List<Playlist> playlists = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "followed_playlists",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "playlist_id")})
    private Set<Playlist> followedPlaylists = new HashSet<>();

    public void addPlaylistToOwnerUser(Playlist playlist) {
        playlists.add(playlist);
        playlist.setUser(this);
    }

    public void removePlaylistFromOwnerUser(Playlist playlist) {
        playlists.remove(playlist);
        playlist.setUser(null);
    }

    public User(Integer id, String firstName, String lastName, String email, String password, String country, String role, Boolean deleted) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.country = country;
        this.role = role;
        this.deleted = deleted;
    }
}
