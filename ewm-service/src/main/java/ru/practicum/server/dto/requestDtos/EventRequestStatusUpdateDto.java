package ru.practicum.server.dto.requestDtos;

import lombok.*;
import ru.practicum.server.enums.EventRequestStatusEnum;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventRequestStatusUpdateDto {
    private Long[] requestIds;
    private EventRequestStatusEnum status;
}
