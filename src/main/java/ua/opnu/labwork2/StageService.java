package ua.opnu.labwork2;

import org.springframework.stereotype.Service;
import ua.opnu.labwork2.dto.*;
import ua.opnu.labwork2.exception.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StageService {
    private final StageRepository stageRepository;
    private final FestivalRepository festivalRepository;

    public StageService(StageRepository stageRepository, FestivalRepository festivalRepository) {
        this.stageRepository = stageRepository;
        this.festivalRepository = festivalRepository;
    }

    public StageResponse create(StageCreateRequest request) {
        Festival festival = festivalRepository.findById(request.festivalId())
                .orElseThrow(() -> new ResourceNotFoundException("Festival not found"));
        Stage stage = new Stage(null, request.name(), request.capacity());
        stage.setFestival(festival);
        return mapToResponse(stageRepository.save(stage));
    }

    public StageResponse findById(Long id) {
        return stageRepository.findById(id).map(this::mapToResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Stage not found"));
    }

    public List<StageResponse> findAll() {
        return stageRepository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    public StageResponse update(Long id, StageUpdateRequest request) {
        Stage existing = stageRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Stage not found"));
        Festival festival = festivalRepository.findById(request.festivalId())
                .orElseThrow(() -> new ResourceNotFoundException("Festival not found"));
        existing.setName(request.name());
        existing.setCapacity(request.capacity());
        existing.setFestival(festival);
        return mapToResponse(stageRepository.save(existing));
    }

    public void delete(Long id) {
        if (!stageRepository.existsById(id)) throw new ResourceNotFoundException("Stage not found");
        stageRepository.deleteById(id);
    }

    public List<PerformanceResponse> getStagePerformances(Long stageId) {
        Stage stage = stageRepository.findById(stageId)
                .orElseThrow(() -> new ResourceNotFoundException("Stage not found"));
        return stage.getPerformances().stream()
                .map(p -> new PerformanceResponse(p.getId(), p.getPerformanceTime(), p.getDurationMinutes(),
                        p.getArtist().getId(), p.getStage().getId(),
                        p.getVisitor() != null ? p.getVisitor().getId() : null))
                .collect(Collectors.toList());
    }

    public FestivalResponse getStageFestival(Long stageId) {
        Stage stage = stageRepository.findById(stageId)
                .orElseThrow(() -> new ResourceNotFoundException("Stage not found"));
        Festival festival = stage.getFestival();
        return new FestivalResponse(festival.getId(), festival.getName(), festival.getCity(),
                festival.getStartDate(), festival.getEndDate());
    }

    private StageResponse mapToResponse(Stage stage) {
        return new StageResponse(stage.getId(), stage.getName(), stage.getCapacity(),
                stage.getFestival() != null ? stage.getFestival().getId() : null);
    }
}

