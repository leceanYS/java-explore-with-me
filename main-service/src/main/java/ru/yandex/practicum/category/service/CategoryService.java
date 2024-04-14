package ru.yandex.practicum.category.service;

import org.springframework.data.domain.Pageable;
import ru.yandex.practicum.category.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto create(CategoryDto categoryDto);

    CategoryDto patch(CategoryDto categoryDto, long catId);

    void delete(long catId);

    List<CategoryDto> getFiltered(Pageable pageable);

    CategoryDto getById(long compId);
}
