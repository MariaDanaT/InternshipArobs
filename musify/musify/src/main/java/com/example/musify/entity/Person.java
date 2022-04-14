package com.example.musify.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "persons")
@NamedQueries({
        @NamedQuery(name = "getAllArtists", query = "FROM Person"),
        @NamedQuery(name = "getArtistById", query = "FROM Person WHERE id = :id")
})
@Getter
@Setter
@NoArgsConstructor
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "stage_name")
    private String stageName;
    @Column(name = "birthday")
    private Date birthday;
    @Column(name = "activity_start_date")
    private String activityStartDate;
    @Column(name = "activity_end_date")
    private String activityEndDate;

    @ManyToMany
    @JoinTable(name = "bands_members",
            joinColumns = {@JoinColumn(name = "person_id")},
            inverseJoinColumns = {@JoinColumn(name = "band_id")})
    private Set<Band> bands = new HashSet<>();

    @OneToMany(mappedBy = "person")
    private Set<Album> albums = new HashSet<>();

    @ManyToMany(mappedBy = "contributors")
    private Set<Song> contributorSongs = new HashSet<>();

    //addSong, removeSong
    public void addBand(Band b) {
        this.bands.add(b);
        b.getPeople().add(this);
    }

    public void removeBand(Band b) {
        this.bands.remove(b);
        b.getPeople().remove(this);
    }

    public Person(String firstName, String lastName, String stageName, Date birthday, String activityStartDate, String activityEndDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.stageName = stageName;
        this.birthday = birthday;
        this.activityStartDate = activityStartDate;
        this.activityEndDate = activityEndDate;
    }

    public Person(int id, String firstName, String lastName, String stageName, Date birthday, String activityStartDate, String activityEndDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.stageName = stageName;
        this.birthday = birthday;
        this.activityStartDate = activityStartDate;
        this.activityEndDate = activityEndDate;
    }

    @Override
    public String toString() {

        return "Artist{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", stageName='" + stageName + '\'' +
                ", birthday=" + birthday +
                ", activityStartDate='" + activityStartDate + '\'' +
                ", activityEndDate='" + activityEndDate + '\'' +
                '}';
    }
}
