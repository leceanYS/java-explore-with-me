package ru.practicum.server.models;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class PublicFilterParam {
    private String text;
    private List<Long> categories;
    private Boolean paid;
    private LocalDateTime start;
    private LocalDateTime end;
    private Boolean available;
    private SortMethod sort;
    private Integer from;
    private Integer size;

    public enum SortMethod {EVENT_DATE, VIEWS}

}
