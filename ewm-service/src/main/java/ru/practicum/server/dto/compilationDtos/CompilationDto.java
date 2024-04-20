package ru.practicum.server.dto.compilationDtos;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import ru.practicum.server.dto.eventDtos.EventShortDto;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompilationDto {
    private List<EventShortDto> events;
    private Long id;
    private Boolean pinned;
    @Length(min = 1, max = 50)
    private String title;
}
