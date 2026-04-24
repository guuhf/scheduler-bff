package com.guuh.scheduler_bff.infrastructure.client;


import com.guuh.scheduler_bff.business.dtos.response.TaskResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notification", url = "${notification.url}")
public interface NotificationClient {

    @PostMapping("/email")
    void sendEmail(@RequestBody TaskResponseDTO dto);
}
