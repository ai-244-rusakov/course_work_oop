package ua.opnu.labwork2;

import org.springframework.stereotype.Service;
import ua.opnu.labwork2.dto.*;
import ua.opnu.labwork2.exception.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PerformanceService {
    private final PerformanceRepository performanceRepository;
    private final ArtistRepository artistRepository;
    private final StageRepository stageRepository;
    private final VisitorRepository visitorRepository;

    public PerformanceService(PerformanceRepository performanceRepository, ArtistRepository artistRepository,
                             StageRepository stageRepository, VisitorRepository visitorRepository) {
        this.performanceRepository = performanceRepository;
        this.artistRepository = artistRepository;
        this.stageRepository = stageRepository;
        this.visitorRepository = visitorRepository;
    }

    public PerformanceResponse create(PerformanceCreateRequest request) {
        Artist artist = artistRepository.findById(request.artistId())
                .orElseThrow(() -> new ResourceNotFoundException("Artist not found"));
        Stage stage = stageRepository.findById(request.stageId())
                .orElseThrow(() -> new ResourceNotFoundException("Stage not found"));
        Visitor visitor = request.visitorId() != null ?
                visitorRepository.findById(request.visitorId()).orElse(null) : null;

        Performance performance = new Performance(null, request.performanceTime(), request.durationMinutes());
        performance.setArtist(artist);
        performance.setStage(stage);
        performance.setVisitor(visitor);
        return mapToResponse(performanceRepository.save(performance));
    }

    public PerformanceResponse findById(Long id) {
        return performanceRepository.findById(id).map(this::mapToResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Performance not found"));
    }

    public List<PerformanceResponse> findAll() {
        return performanceRepository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    public PerformanceResponse update(Long id, PerformanceUpdateRequest request) {
        Performance existing = performanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Performance not found"));
        Artist artist = artistRepository.findById(request.artistId())
                .orElseThrow(() -> new ResourceNotFoundException("Artist not found"));
        Stage stage = stageRepository.findById(request.stageId())
                .orElseThrow(() -> new ResourceNotFoundException("Stage not found"));
        Visitor visitor = request.visitorId() != null ?
                visitorRepository.findById(request.visitorId()).orElse(null) : null;

        existing.setPerformanceTime(request.performanceTime());
        existing.setDurationMinutes(request.durationMinutes());
        existing.setArtist(artist);
        existing.setStage(stage);
        existing.setVisitor(visitor);
        return mapToResponse(performanceRepository.save(existing));
    }

    public void delete(Long id) {
        if (!performanceRepository.existsById(id)) throw new ResourceNotFoundException("Performance not found");
        performanceRepository.deleteById(id);
    }

    private PerformanceResponse mapToResponse(Performance performance) {
        return new PerformanceResponse(performance.getId(), performance.getPerformanceTime(),
                performance.getDurationMinutes(), performance.getArtist().getId(),
                performance.getStage().getId(),
                performance.getVisitor() != null ? performance.getVisitor().getId() : null);
    }
}

