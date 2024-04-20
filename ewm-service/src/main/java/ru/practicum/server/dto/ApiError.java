package ru.practicum.server.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiError {
    private List<String> errors;
    private String status;
    private String reason;
    private String message;
    private LocalDateTime timestamp;
}
