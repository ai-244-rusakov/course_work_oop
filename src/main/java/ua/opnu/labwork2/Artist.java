package ua.opnu.labwork2;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "artists")
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String genre;
    private String country;

    @ManyToMany(mappedBy = "artists")
    private List<Festival> festivals;

    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL)
    private List<Performance> performances;

    public Artist() {}

    public Artist(Long id, String name, String genre, String country) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.country = country;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }
    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
    public List<Festival> getFestivals() { return festivals; }
    public void setFestivals(List<Festival> festivals) { this.festivals = festivals; }
    public List<Performance> getPerformances() { return performances; }
    public void setPerformances(List<Performance> performances) { this.performances = performances; }
}

