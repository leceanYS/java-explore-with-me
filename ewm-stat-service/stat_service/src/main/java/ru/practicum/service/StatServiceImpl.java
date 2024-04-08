package ru.practicum.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import ru.practicum.dao.StatServiceRepository;
import ru.practicum.mapper.StatMapper;
import ru.practicum.model.Stat;
import ru.practicum.model.StatUniqueOrNot;

import javax.validation.constraints.Min;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public abstract class StatServiceImpl  implements StatService {

    private final StatServiceRepository serviceRepository;

    @Transactional
    @Override
    public Stat postStat(Stat stat) {
        boolean answer = serviceRepository.existsByUri(stat.getUri());
        Stat newStat;
        if (answer) {
            boolean answerIp = serviceRepository.existsByUriAndIp(stat.getUri(), stat.getIp());
            if (answerIp) {
                newStat = serviceRepository.findByUri(stat.getUri());
                long hit = newStat.getHits();
                newStat.setHits(hit + 1);
                log.info("update stat parameter hits");
            } else { //если нет то и уникальный
                newStat = serviceRepository.findByUri(stat.getUri());
                long hit = newStat.getHits();
                long hitUnique = newStat.getHitsUnique();
                newStat.setHits(hit + 1);
                log.info("update stat parameter hits");
                newStat.setHitsUnique(hitUnique + 1);
                log.info("update stat parameter hits unique");
            }
        } else { //если нет
            stat.setHits(1);
            stat.setHitsUnique(1);
            newStat = serviceRepository.save(stat);
            log.info("create new stat");
        }
        return newStat;
    }


    @Override
    public List<StatUniqueOrNot> getStat(@RequestParam("start") @Min(19) String start, @RequestParam("end") @Min(19) String end,
                                         @RequestParam(defaultValue = "") List<String> uris,
                                         @RequestParam(defaultValue = "false") boolean unique) {
        List<StatUniqueOrNot> list;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateStart = LocalDateTime.parse(start, formatter);
        LocalDateTime dateEnd = LocalDateTime.parse(end, formatter);
        if (uris.isEmpty()) {
            if (unique) {
                list = StatMapper.toListUnique(serviceRepository.findByTimestampBetween(dateStart, dateEnd));
                log.info("get unique list without uris");
            } else {
                list = StatMapper.toListNotUnique(serviceRepository.findByTimestampBetween(dateStart, dateEnd));
                log.info("get not unique list without uris");
            }
        } else {
            if (unique) {
                list = StatMapper.toListUnique(serviceRepository.findByTimestampBetweenAndUri(dateStart, dateEnd, uris));
                log.info("get unique list with uris");
            } else {
                list = StatMapper.toListNotUnique(serviceRepository.findByTimestampBetweenAndUri(dateStart, dateEnd, uris));
                log.info("get not unique list with uris");
            }
        }
        return list;
    }
}
