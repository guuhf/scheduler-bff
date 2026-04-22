package com.guuh.scheduler_bff.infrastructure.client;

import com.guuh.scheduler_bff.business.dtos.TaskDTO;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "task-scheduler", url = "${task.url}")
public interface TaskClient {
    @PostMapping
    TaskDTO createTask(@RequestBody @Valid TaskDTO taskDTO,
                       @RequestHeader("Authorization") String token);

    @GetMapping("/events")
    public List<TaskDTO> getTaskByEventDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime initialDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime finalDate,
            @RequestHeader("Authorization") String token);

    @GetMapping("/my-tasks")
    List<TaskDTO> getMyTasks(@RequestHeader("Authorization") String token);

    @PutMapping("/{id}")
    TaskDTO updateTasks(@RequestBody TaskDTO taskDto,
                        @PathVariable String id,
                        @RequestHeader("Authorization") String token);

    @PatchMapping("/{id}")
    TaskDTO updateTaskStatus(@RequestBody TaskDTO taskDTO,
                             @PathVariable String id,
                             @RequestHeader("Authorization") String token);


    @DeleteMapping("/{id}")
    Void deleteTasks(@PathVariable String id,
                     @RequestHeader("Authorization") String token);
}
