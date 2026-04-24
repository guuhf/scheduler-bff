package com.guuh.scheduler_bff.business;


import com.guuh.scheduler_bff.business.dtos.request.LoginRequestDTO;
import com.guuh.scheduler_bff.business.dtos.request.TaskRequestDTO;
import com.guuh.scheduler_bff.business.dtos.response.TaskResponseDTO;
import com.guuh.scheduler_bff.infrastructure.enums.NotificationStatusEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CronService {

    private final TaskService taskService;
    private final NotificationService notificationService;
    private final UserService userService;

    @Value("${admin.email}")
    private String email;
    @Value("${admin.password}")
    private String password;

    @Scheduled(cron = "${cron.expression}")
    public void sendEmail(){
        log.info("inicio do metodo");
        String token = adminLogin(toLoginRequestDTO(email,password));
        log.info("iniciada busca por tarefas");

        LocalDateTime base = LocalDateTime.now().plusHours(1);

        LocalDateTime timeOne = base.minusMinutes(2);
        LocalDateTime timeTwo = base.plusMinutes(3);

         List<TaskResponseDTO> taskList = taskService.getTaskByEventDate(timeOne, timeTwo, token);
         log.info("tarefas encontradas: {}", taskList);

         taskList.forEach(tasks -> {
             notificationService.sendEmail(tasks);
             log.info("email enviado para o usuário: " + tasks.getEmail());
             taskService.updateTaskStatus(NotificationStatusEnum.NOTIFIED, tasks.getId(), token );
         });
         log.info("encerrado o envio de tarefas!");
    }

    public String adminLogin(LoginRequestDTO dto){
        return userService.userLogin(dto);
    }

    public LoginRequestDTO toLoginRequestDTO(String email, String password){
        return LoginRequestDTO.builder()
                .email(email)
                .password(password)
                .build();
    }
}
