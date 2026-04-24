package com.guuh.scheduler_bff.infrastructure.client;

import com.guuh.scheduler_bff.business.dtos.request.TaskRequestDTO;
import com.guuh.scheduler_bff.business.dtos.response.TaskResponseDTO;
import com.guuh.scheduler_bff.infrastructure.enums.NotificationStatusEnum;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "task-scheduler", url = "${task.url}")
public interface TaskClient {

    @PostMapping("/tasks")
    TaskResponseDTO createTask(@RequestBody @Valid TaskRequestDTO dto,
                               @RequestHeader("Authorization") String token);

    @GetMapping("/tasks/events")
    public List<TaskResponseDTO> getTaskByEventDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime initialDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime finalDate,
            @RequestHeader("Authorization") String token);

    @GetMapping("/tasks/my-tasks")
    List<TaskResponseDTO> getMyTasks(@RequestHeader("Authorization") String token);

    @PutMapping("/tasks/{id}")
    TaskResponseDTO updateTasks(@RequestBody TaskRequestDTO dto,
                                @PathVariable String id,
                                @RequestHeader("Authorization") String token);

    @PatchMapping("/tasks/{id}")
    TaskResponseDTO updateTaskStatus(@RequestParam("status")NotificationStatusEnum statusEnum,
                                     @PathVariable String id,
                                     @RequestHeader("Authorization") String token);


    @DeleteMapping("/tasks/{id}")
    Void deleteTasks(@PathVariable String id,
                     @RequestHeader("Authorization") String token);
}
