package com.guuh.scheduler_bff.business;


import com.guuh.scheduler_bff.business.dtos.request.TaskRequestDTO;
import com.guuh.scheduler_bff.business.dtos.response.TaskResponseDTO;
import com.guuh.scheduler_bff.infrastructure.client.TaskClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TaskService {

    private final TaskClient taskClient;

    public TaskResponseDTO createTask(TaskRequestDTO dto, String token) {
        return taskClient.createTask(dto, token);
    }

    public List<TaskResponseDTO> getTaskByEventDate(LocalDateTime initialDate, LocalDateTime finalDate, String token) {
        return taskClient.getTaskByEventDate(initialDate, finalDate, token);
    }

    public List<TaskResponseDTO> getMyTasks(String token) {
        return taskClient.getMyTasks(token);
    }

    public TaskResponseDTO updateTask(TaskRequestDTO dto, String id, String token){
        return taskClient.updateTasks(dto, id, token);
    }

    public void deleteTask(String id, String token){
      taskClient.deleteTasks(id, token);
    }

    public TaskResponseDTO updateTaskStatus(TaskRequestDTO dto, String id, String token){
        return taskClient.updateTaskStatus(dto, id, token);
    }
}
