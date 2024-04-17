package ru.practicum.server.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.practicum.server.enums.StateEnum;
import ru.practicum.server.repository.entities.EventEntity;

import java.util.Optional;

public interface EventRepository extends JpaRepository<EventEntity, Long>, JpaSpecificationExecutor<EventEntity> {
    Page<EventEntity> getEventEntitiesByOwnerId(Long ownerId, Pageable pageable);

    Optional<EventEntity> getEventEntitiesByOwnerIdAndId(Long ownerId, Long id);

    Optional<EventEntity> getEventEntityByIdAndState(Long id, StateEnum state);
}
