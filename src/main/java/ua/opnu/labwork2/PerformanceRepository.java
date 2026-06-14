package ua.opnu.labwork2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PerformanceRepository extends JpaRepository<Performance, Long> {
    List<Performance> findByStageId(Long stageId);
    List<Performance> findByArtistId(Long artistId);
    List<Performance> findByVisitorId(Long visitorId);

    @Query("SELECT p.stage.name, COUNT(p) FROM Performance p GROUP BY p.stage.name")
    List<Object[]> countPerformancesByStage();
}

