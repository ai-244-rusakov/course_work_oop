package ua.opnu.labwork2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StageRepository extends JpaRepository<Stage, Long> {
    List<Stage> findByFestivalId(Long festivalId);
    List<Stage> findByNameContainingIgnoreCase(String name);
}

