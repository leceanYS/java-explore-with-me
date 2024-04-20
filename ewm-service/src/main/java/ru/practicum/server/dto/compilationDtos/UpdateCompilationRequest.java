package ru.practicum.server.dto.compilationDtos;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCompilationRequest {
    private List<Long> events;
    private Boolean pinned;
    @Length(min = 1, max = 50)
    private String title;
}
