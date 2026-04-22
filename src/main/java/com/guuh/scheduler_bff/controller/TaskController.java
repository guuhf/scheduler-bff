package com.guuh.scheduler_bff.controller;


import com.guuh.scheduler_bff.business.dtos.TaskDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.guuh.scheduler_bff.business.TaskService;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskDTO> createTask(@RequestBody @Valid TaskDTO taskDTO,
                                              @RequestHeader("Authorization") String token){
        return ResponseEntity.status(201).body(taskService.createTask(taskDTO, token));
    }

    @GetMapping("/events")
    public ResponseEntity<List<TaskDTO>> getTaskByEventDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime initialDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime finalDate,
            @RequestHeader("Authorization") String token){
        return ResponseEntity.status(200).body(taskService.getTaskByEventDate(initialDate, finalDate, token));
    }

    @GetMapping("/my-tasks")
    public ResponseEntity<List<TaskDTO>>getMyTasks(@RequestHeader("Authorization") String token){
        return ResponseEntity.status(200).body(taskService.getMyTasks(token));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO>updateTasks(@RequestBody TaskDTO taskDto,
                                              @PathVariable String id,
                                              @RequestHeader("Authorization") String token){
        return ResponseEntity.status(200).body(taskService.updateTask(taskDto, id, token));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TaskDTO>updateTaskStatus(@RequestBody TaskDTO taskDTO,
                                                   @PathVariable String id,
                                                   @RequestHeader("Authorization") String token){
        return ResponseEntity.status(200).body(taskService.updateTaskStatus(taskDTO, id, token));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void>deleteTasks(@PathVariable String id,
                                           @RequestHeader("Authorization") String token){
        taskService.deleteTask(id, token);
        return ResponseEntity.status(204).build();
    }
}
