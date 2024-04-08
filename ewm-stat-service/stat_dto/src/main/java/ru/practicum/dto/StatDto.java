package ru.practicum.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

@Data
@Builder
@EqualsAndHashCode
public class StatDto {

    @NotNull(groups = {Marker.Create.class, Marker.Update.class})
    @Size(max = 50)
    private String app;

    @NotNull(groups = {Marker.Create.class, Marker.Update.class})
    @Size(max = 50)
    private String uri;

    @NotNull(groups = {Marker.Create.class, Marker.Update.class})
    @Size(max = 15)
    private String ip;

    @Null(groups = {Marker.Create.class, Marker.Update.class})
    @Size(max = 20)
    private String timestamp;
}
