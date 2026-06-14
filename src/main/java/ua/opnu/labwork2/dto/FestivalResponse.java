package ua.opnu.labwork2.dto;

import java.time.LocalDate;

public record FestivalResponse(
        Long id, String name, String city, LocalDate startDate, LocalDate endDate
) {}

