package ru.practicum.server.dto.categoryDtos;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    private Long id;
    @NotBlank
    @Length(min = 1, max = 50)
    private String name;
}
