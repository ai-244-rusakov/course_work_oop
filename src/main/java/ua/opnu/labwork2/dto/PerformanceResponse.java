package ua.opnu.labwork2.dto;

import java.time.LocalDateTime;

public record PerformanceResponse(
        Long id, LocalDateTime performanceTime, Integer durationMinutes,
        Long artistId, Long stageId, Long visitorId
) {}

