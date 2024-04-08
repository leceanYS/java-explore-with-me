package ru.practicum.service;

import org.springframework.web.bind.annotation.RequestParam;
import ru.practicum.model.Stat;
import ru.practicum.model.StatUniqueOrNot;

import javax.validation.constraints.Min;
import java.time.LocalDateTime;
import java.util.List;

public interface StatService {

    Stat postStat(Stat stat);

    List<StatUniqueOrNot> getStat(LocalDateTime start, LocalDateTime end, List<String> uris, boolean unique);

    List<StatUniqueOrNot> getStat(@RequestParam("start") @Min(19) String start, @RequestParam("end") @Min(19) String end,
                                  @RequestParam(defaultValue = "") List<String> uris,
                                  @RequestParam(defaultValue = "false") boolean unique);
}
