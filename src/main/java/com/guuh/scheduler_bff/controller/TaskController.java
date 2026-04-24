package com.guuh.scheduler_bff.controller;

import com.guuh.scheduler_bff.business.TaskService;
import com.guuh.scheduler_bff.business.dtos.request.TaskRequestDTO;
import com.guuh.scheduler_bff.business.dtos.response.TaskResponseDTO;
import com.guuh.scheduler_bff.infrastructure.configs.SecurityConfig;
import com.guuh.scheduler_bff.infrastructure.enums.NotificationStatusEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
@Tag(name = "Tasks", description = "Task management")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    @Operation(summary = "Create a new task", description = "Creates a task for the authenticated user")
    @ApiResponse(responseCode = "201", description = "Task created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid input or invalid date")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    public ResponseEntity<TaskResponseDTO> createTask(@RequestBody @Valid TaskRequestDTO dto,
                                                      @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.status(201).body(taskService.createTask(dto, token));
    }

    @GetMapping("/events")
    @Operation(summary = "Get tasks by event date", description = "Returns tasks within a date range")
    @ApiResponse(responseCode = "200", description = "Tasks retrieved successfully")
    @ApiResponse(responseCode = "400", description = "Invalid date format")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    public ResponseEntity<List<TaskResponseDTO>> getTaskByEventDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime initialDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime finalDate,
            @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.status(200).body(taskService.getTaskByEventDate(initialDate, finalDate, token));
    }

    @GetMapping("/my-tasks")
    @Operation(summary = "Get my tasks", description = "Returns all tasks of the authenticated user")
    @ApiResponse(responseCode = "200", description = "Tasks retrieved successfully")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    public ResponseEntity<List<TaskResponseDTO>> getMyTasks(
            @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.status(200).body(taskService.getMyTasks(token));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a task", description = "Updates all fields of a task")
    @ApiResponse(responseCode = "200", description = "Task updated successfully")
    @ApiResponse(responseCode = "404", description = "Task not found")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    public ResponseEntity<TaskResponseDTO> updateTasks(@RequestBody TaskRequestDTO dto,
                                                       @PathVariable String id,
                                                       @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.status(200).body(taskService.updateTask(dto, id, token));
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update task status", description = "Updates only the notification status of a task")
    @ApiResponse(responseCode = "200", description = "Task status updated successfully")
    @ApiResponse(responseCode = "404", description = "Task not found")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    public ResponseEntity<TaskResponseDTO> updateTaskStatus(@RequestParam("status")NotificationStatusEnum statusEnum,
                                                            @PathVariable String id,
                                                            @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.status(200).body(taskService.updateTaskStatus(statusEnum, id, token));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a task", description = "Deletes a task by ID")
    @ApiResponse(responseCode = "204", description = "Task deleted successfully")
    @ApiResponse(responseCode = "404", description = "Task not found")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    public ResponseEntity<Void> deleteTasks(@PathVariable String id,
                                            @RequestHeader(name = "Authorization", required = false) String token){
        taskService.deleteTask(id, token);
        return ResponseEntity.status(204).build();
    }
}
