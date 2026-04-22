package com.guuh.scheduler_bff.business;


import com.guuh.scheduler_bff.business.dtos.TaskDTO;
import com.guuh.scheduler_bff.infrastructure.client.TaskClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TaskService {

    private final TaskClient taskClient;

    public TaskDTO createTask(TaskDTO taskDto, String token) {
        return taskClient.createTask(taskDto, token);
    }

    public List<TaskDTO> getTaskByEventDate(LocalDateTime initialDate, LocalDateTime finalDate, String token) {
        return taskClient.getTaskByEventDate(initialDate, finalDate, token);
    }

    public List<TaskDTO> getMyTasks(String token) {
        return taskClient.getMyTasks(token);
    }

    public TaskDTO updateTask(TaskDTO taskDto, String id, String token){
        return taskClient.updateTasks(taskDto, id, token);
    }

    public void deleteTask(String id, String token){
      taskClient.deleteTasks(id, token);
    }

    public TaskDTO updateTaskStatus(TaskDTO taskDTO, String id, String token){
        return taskClient.updateTaskStatus(taskDTO, id, token);
    }
}
