package com.example.musify.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "bands")
@Getter
@Setter
@NoArgsConstructor
public class Band {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "location")
    private String location;
    @Column(name = "activity_start_date")
    private String activityStartDate;
    @Column(name = "activity_end_date")
    private String activityEndDate;

    @ManyToMany(mappedBy = "bands")
    private Set<Person> people = new HashSet<>();

    @OneToMany(mappedBy = "band")
    private Set<Album> albums = new HashSet<>();

    //addSong, removeSong

    public Band(Integer id, String name, String location, String activityStartDate, String activityEndDate) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.activityStartDate = activityStartDate;
        this.activityEndDate = activityEndDate;
    }

    public Band(Integer id, String name, String location, String activityStartDate, String activityEndDate, Set<Person> people) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.activityStartDate = activityStartDate;
        this.activityEndDate = activityEndDate;
        this.people = people;
    }

    @Override
    public String toString() {
        return "Band{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", activityStartDate='" + activityStartDate + '\'' +
                ", activityEndDate='" + activityEndDate + '\'' +
                '}';
    }
}
