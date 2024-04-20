package ru.practicum.server.dto.requestDtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventRequestStatusUpdateResult {
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<ParticipationRequestDto> confirmedRequests = new ArrayList<>();
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<ParticipationRequestDto> rejectedRequests = new ArrayList<>();
}
