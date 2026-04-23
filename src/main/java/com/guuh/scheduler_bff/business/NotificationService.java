package com.guuh.scheduler_bff.business;


import com.guuh.scheduler_bff.business.dtos.response.TaskResponseDTO;
import com.guuh.scheduler_bff.infrastructure.client.NotificationClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationClient notificationClient;

    public void sendEmail(TaskResponseDTO dto){
        notificationClient.sendEmail(dto);
    }
}
