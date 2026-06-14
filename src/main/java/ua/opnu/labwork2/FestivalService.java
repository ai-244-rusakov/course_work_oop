package ua.opnu.labwork2;

import org.springframework.stereotype.Service;
import ua.opnu.labwork2.dto.*;
import ua.opnu.labwork2.exception.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FestivalService {
    private final FestivalRepository festivalRepository;
    private final StageRepository stageRepository;

    public FestivalService(FestivalRepository festivalRepository, StageRepository stageRepository) {
        this.festivalRepository = festivalRepository;
        this.stageRepository = stageRepository;
    }

    public FestivalResponse create(FestivalCreateRequest request) {
        Festival festival = new Festival(null, request.name(), request.city(), request.startDate(), request.endDate());
        return mapToResponse(festivalRepository.save(festival));
    }

    public FestivalResponse findById(Long id) {
        return festivalRepository.findById(id).map(this::mapToResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Festival not found"));
    }

    public List<FestivalResponse> findAll() {
        return festivalRepository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    public FestivalResponse update(Long id, FestivalUpdateRequest request) {
        Festival existing = festivalRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Festival not found"));
        existing.setName(request.name());
        existing.setCity(request.city());
        existing.setStartDate(request.startDate());
        existing.setEndDate(request.endDate());
        return mapToResponse(festivalRepository.save(existing));
    }

    public void delete(Long id) {
        if (!festivalRepository.existsById(id)) throw new ResourceNotFoundException("Festival not found");
        festivalRepository.deleteById(id);
    }

    public List<FestivalResponse> search(String query) {
        return festivalRepository.findByNameContainingIgnoreCase(query).stream()
                .map(this::mapToResponse).collect(Collectors.toList());
    }

    public List<ArtistResponse> getFestivalArtists(Long festivalId) {
        Festival festival = festivalRepository.findById(festivalId)
                .orElseThrow(() -> new ResourceNotFoundException("Festival not found"));
        return festival.getArtists().stream()
                .map(a -> new ArtistResponse(a.getId(), a.getName(), a.getGenre(), a.getCountry()))
                .collect(Collectors.toList());
    }

    public List<StageResponse> getFestivalStages(Long festivalId) {
        Festival festival = festivalRepository.findById(festivalId)
                .orElseThrow(() -> new ResourceNotFoundException("Festival not found"));
        return festival.getStages().stream()
                .map(s -> new StageResponse(s.getId(), s.getName(), s.getCapacity(), s.getFestival().getId()))
                .collect(Collectors.toList());
    }

    private FestivalResponse mapToResponse(Festival festival) {
        return new FestivalResponse(festival.getId(), festival.getName(), festival.getCity(),
                festival.getStartDate(), festival.getEndDate());
    }
}

