package ua.opnu.labwork2;

import org.springframework.stereotype.Service;
import ua.opnu.labwork2.dto.*;
import ua.opnu.labwork2.exception.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArtistService {
    private final ArtistRepository artistRepository;
    private final PerformanceRepository performanceRepository;

    public ArtistService(ArtistRepository artistRepository, PerformanceRepository performanceRepository) {
        this.artistRepository = artistRepository;
        this.performanceRepository = performanceRepository;
    }

    public ArtistResponse create(ArtistCreateRequest request) {
        Artist artist = new Artist(null, request.name(), request.genre(), request.country());
        return mapToResponse(artistRepository.save(artist));
    }

    public ArtistResponse findById(Long id) {
        return artistRepository.findById(id).map(this::mapToResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Artist not found"));
    }

    public List<ArtistResponse> findAll() {
        return artistRepository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    public ArtistResponse update(Long id, ArtistUpdateRequest request) {
        Artist existing = artistRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Artist not found"));
        existing.setName(request.name());
        existing.setGenre(request.genre());
        existing.setCountry(request.country());
        return mapToResponse(artistRepository.save(existing));
    }

    public void delete(Long id) {
        if (!artistRepository.existsById(id)) throw new ResourceNotFoundException("Artist not found");
        artistRepository.deleteById(id);
    }

    public List<ArtistResponse> search(String query) {
        return artistRepository.findByNameContainingIgnoreCase(query).stream()
                .map(this::mapToResponse).collect(Collectors.toList());
    }

    public List<PerformanceResponse> getArtistPerformances(Long artistId) {
        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new ResourceNotFoundException("Artist not found"));
        return artist.getPerformances().stream()
                .map(p -> new PerformanceResponse(p.getId(), p.getPerformanceTime(), p.getDurationMinutes(),
                        p.getArtist().getId(), p.getStage().getId(),
                        p.getVisitor() != null ? p.getVisitor().getId() : null))
                .collect(Collectors.toList());
    }

    private ArtistResponse mapToResponse(Artist artist) {
        return new ArtistResponse(artist.getId(), artist.getName(), artist.getGenre(), artist.getCountry());
    }
}

