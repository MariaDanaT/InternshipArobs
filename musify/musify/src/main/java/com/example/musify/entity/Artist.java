package com.example.musify.entity;


import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "artists")
@NamedQueries({
        @NamedQuery(name = "getAllArtists", query = "FROM Artist"),
        @NamedQuery(name = "getArtistById", query = "FROM Artist WHERE id = :id")
})
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
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
    private String type;

    @ManyToMany
    @JoinTable(name = "bands_members",
            joinColumns = {@JoinColumn(name = "artist_id")},
            inverseJoinColumns = {@JoinColumn(name = "band_id")})
    private Set<Band> bands = new HashSet<>();


    public void addBand(Band b) {
        this.bands.add(b);
        b.getArtists().add(this);
    }

    public void removeBand(Band b) {
        this.bands.remove(b);
        b.getArtists().remove(this);
    }

    public Artist() {
    }

    public Artist(String firstName, String lastName, String stageName, Date birthday, String activityStartDate, String activityEndDate, String type) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.stageName = stageName;
        this.birthday = birthday;
        this.activityStartDate = activityStartDate;
        this.activityEndDate = activityEndDate;
        this.type = type;
    }

    public Artist(int id, String firstName, String lastName, String stageName, Date birthday, String activityStartDate, String activityEndDate, String type) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.stageName = stageName;
        this.birthday = birthday;
        this.activityStartDate = activityStartDate;
        this.activityEndDate = activityEndDate;
        this.type = type;
    }

    public Artist(int id, String firstName, String lastName, String stageName, Date birthday, String activityStartDate, String activityEndDate, String type, Set<Band> bands) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.stageName = stageName;
        this.birthday = birthday;
        this.activityStartDate = activityStartDate;
        this.activityEndDate = activityEndDate;
        this.type = type;
        this.bands = bands;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getActivityStartDate() {
        return activityStartDate;
    }

    public void setActivityStartDate(String activityStartDate) {
        this.activityStartDate = activityStartDate;
    }

    public String getActivityEndDate() {
        return activityEndDate;
    }

    public void setActivityEndDate(String activityEndDate) {
        this.activityEndDate = activityEndDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<Band> getBands() {
        return bands;
    }

    public void setBands(Set<Band> bands) {
        this.bands = bands;
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
                ", type='" + type + '\'' +
                '}';
    }
}
