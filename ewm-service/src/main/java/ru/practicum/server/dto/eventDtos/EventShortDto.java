package ru.practicum.server.dto.eventDtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import ru.practicum.server.dto.categoryDtos.CategoryDto;
import ru.practicum.server.dto.userDtos.UserShortDto;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventShortDto {
    private String annotation;
    private CategoryDto category;
    private Integer confirmedRequests;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eventDate;
    private Long id;
    private UserShortDto initiator;
    private Boolean paid;
    private String title;
    private Long views;
}
