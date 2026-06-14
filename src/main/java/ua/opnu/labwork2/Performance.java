package ua.opnu.labwork2;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "performances")
public class Performance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime performanceTime;
    private Integer durationMinutes;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @ManyToOne
    @JoinColumn(name = "stage_id")
    private Stage stage;

    @ManyToOne
    @JoinColumn(name = "visitor_id")
    private Visitor visitor;

    public Performance() {}

    public Performance(Long id, LocalDateTime performanceTime, Integer durationMinutes) {
        this.id = id;
        this.performanceTime = performanceTime;
        this.durationMinutes = durationMinutes;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDateTime getPerformanceTime() { return performanceTime; }
    public void setPerformanceTime(LocalDateTime performanceTime) { this.performanceTime = performanceTime; }
    public Integer getDurationMinutes() { return durationMinutes; }
    public void setDurationMinutes(Integer durationMinutes) { this.durationMinutes = durationMinutes; }
    public Artist getArtist() { return artist; }
    public void setArtist(Artist artist) { this.artist = artist; }
    public Stage getStage() { return stage; }
    public void setStage(Stage stage) { this.stage = stage; }
    public Visitor getVisitor() { return visitor; }
    public void setVisitor(Visitor visitor) { this.visitor = visitor; }
}

