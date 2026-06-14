package ua.opnu.labwork2;

import org.springframework.stereotype.Service;
import ua.opnu.labwork2.dto.*;
import ua.opnu.labwork2.exception.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VisitorService {
    private final VisitorRepository visitorRepository;

    public VisitorService(VisitorRepository visitorRepository) {
        this.visitorRepository = visitorRepository;
    }

    public VisitorResponse create(VisitorCreateRequest request) {
        Visitor visitor = new Visitor(null, request.firstName(), request.lastName(), request.email(), request.registrationDate());
        return mapToResponse(visitorRepository.save(visitor));
    }

    public VisitorResponse findById(Long id) {
        return visitorRepository.findById(id).map(this::mapToResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Visitor not found"));
    }

    public List<VisitorResponse> findAll() {
        return visitorRepository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    public VisitorResponse update(Long id, VisitorUpdateRequest request) {
        Visitor existing = visitorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Visitor not found"));
        existing.setFirstName(request.firstName());
        existing.setLastName(request.lastName());
        existing.setEmail(request.email());
        existing.setRegistrationDate(request.registrationDate());
        return mapToResponse(visitorRepository.save(existing));
    }

    public void delete(Long id) {
        if (!visitorRepository.existsById(id)) throw new ResourceNotFoundException("Visitor not found");
        visitorRepository.deleteById(id);
    }

    public List<VisitorResponse> search(String query) {
        return visitorRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(query, query).stream()
                .map(this::mapToResponse).collect(Collectors.toList());
    }

    public List<FestivalResponse> getVisitorFestivals(Long visitorId) {
        Visitor visitor = visitorRepository.findById(visitorId)
                .orElseThrow(() -> new ResourceNotFoundException("Visitor not found"));
        return visitor.getFestivals().stream()
                .map(f -> new FestivalResponse(f.getId(), f.getName(), f.getCity(), f.getStartDate(), f.getEndDate()))
                .collect(Collectors.toList());
    }

    private VisitorResponse mapToResponse(Visitor visitor) {
        return new VisitorResponse(visitor.getId(), visitor.getFirstName(), visitor.getLastName(),
                visitor.getEmail(), visitor.getRegistrationDate());
    }
}

