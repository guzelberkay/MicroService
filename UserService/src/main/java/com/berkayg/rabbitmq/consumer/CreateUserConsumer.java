package com.berkayg.rabbitmq.consumer;

import com.berkayg.dto.request.UserSaveRequestDto;
import com.berkayg.rabbitmq.model.SaveAuthModel;
import com.berkayg.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserConsumer {
    private final UserService userService;
    @RabbitListener(queues = "queue-auth")
    public void createUserFromQueue(SaveAuthModel model){
        userService.save(UserSaveRequestDto.builder()
                .email(model.getEmail())
                .username(model.getUsername())
                .authId(model.getAuthId())
                .build());


    }
}
