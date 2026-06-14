package ua.opnu.labwork2.dto;

import java.time.LocalDate;

public record VisitorResponse(
        Long id, String firstName, String lastName, String email, LocalDate registrationDate
) {}

