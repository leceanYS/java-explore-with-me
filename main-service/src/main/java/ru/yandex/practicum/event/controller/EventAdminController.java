package ru.yandex.practicum.event.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.event.model.PublishState;
import ru.yandex.practicum.event.model.dto.EventFullInfoDto;
import ru.yandex.practicum.event.model.dto.EventRequestAdminDto;
import ru.yandex.practicum.event.model.dto.GetFullEventsRequest;
import ru.yandex.practicum.event.service.EventService;
import ru.yandex.practicum.util.OffsetPageable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/events")
@Validated
public class EventAdminController {
    final EventService eventService;

    @PatchMapping("/{eventId}")
    public EventFullInfoDto update(@PathVariable(name = "eventId") long eventId,
                                   @RequestBody @Validated EventRequestAdminDto eventRequestDto) {
        log.info("PATCH \"/admin/events/{}\" Body={}", eventId, eventRequestDto);
        EventFullInfoDto eventFullInfoDto = eventService.updateWithoutValidation(eventId, eventRequestDto);
        log.debug("Event updated=" + eventFullInfoDto);
        return eventFullInfoDto;
    }

    @GetMapping
    public List<EventFullInfoDto> findAllFiltered(@RequestParam(name = "users", required = false) List<Long> users,
                                                  @RequestParam(name = "states", required = false) List<String> states,
                                                  @RequestParam(name = "categories", required = false) List<Long> categories,
                                                  @RequestParam(name = "rangeStart", required = false)
                                                  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime rangeStart,
                                                  @RequestParam(name = "rangeEnd", required = false)
                                                  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime rangeEnd,
                                                  @RequestParam(name = "from", defaultValue = "0") int offset,
                                                  @RequestParam(name = "size", defaultValue = "10") int limit) {
        log.info("GET \"/admin/events?users={}&states={}&categories={}&rangeStart={}&rangeEnd={}&from={}&size={}\"",
                users, states, categories, rangeStart, rangeEnd, offset, limit);
        Pageable pageable = new OffsetPageable(offset, limit);

        List<PublishState> publishStates = null;
        if (states != null) {
            publishStates = new ArrayList<>();
            for (String s : states) {
                publishStates.add(PublishState.from(s));
            }
        }

        GetFullEventsRequest getFullEventsRequest = GetFullEventsRequest.builder()
                .users(users)
                .publishState(publishStates)
                .categories(categories)
                .rangeStart(rangeStart)
                .rangeEnd(rangeEnd)
                .pageable(pageable)
                .build();
        List<EventFullInfoDto> eventFullInfoDtoList = eventService.findFullDtosFiltered(getFullEventsRequest);

        log.debug("Event found=" + eventFullInfoDtoList);
        return eventFullInfoDtoList;
    }
}
