package ua.opnu.labwork2;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "festivals")
public class Festival {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String city;
    private LocalDate startDate;
    private LocalDate endDate;

    @OneToMany(mappedBy = "festival", cascade = CascadeType.ALL)
    private List<Stage> stages;

    @ManyToMany
    @JoinTable(
            name = "festival_artist",
            joinColumns = @JoinColumn(name = "festival_id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id")
    )
    private List<Artist> artists;

    @ManyToMany(mappedBy = "festivals")
    @JsonIgnore
    private List<Visitor> visitors;

    public Festival() {}

    public Festival(Long id, String name, String city, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
    public List<Stage> getStages() { return stages; }
    public void setStages(List<Stage> stages) { this.stages = stages; }
    public List<Artist> getArtists() { return artists; }
    public void setArtists(List<Artist> artists) { this.artists = artists; }
    public List<Visitor> getVisitors() { return visitors; }
    public void setVisitors(List<Visitor> visitors) { this.visitors = visitors; }
}

