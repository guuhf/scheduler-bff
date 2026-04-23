package com.guuh.scheduler_bff.business.dtos.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.guuh.scheduler_bff.infrastructure.enums.NotificationStatusEnum;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskRequestDTO {
    private String taskName;
    private String description;
    @FutureOrPresent
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime eventDate;
}
