package com.peters.Epay.async;

import com.peters.Epay.event.RabbitMQPublisher;
import com.peters.Epay.user.dto.EmailNotificationDto;
import com.peters.Epay.user.dto.PasswordResetDto;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AsyncRunner {
    private final RabbitMQPublisher rabbitMQPublisher;

    @Async
    public void sendNotification(EmailNotificationDto emailNotificationDto){
        rabbitMQPublisher.sendNotification(emailNotificationDto);
    }

    @Async
    public void sendResetPasswordNotification(PasswordResetDto resetPasswordDto){
        rabbitMQPublisher.sendResetPasswordNotification(resetPasswordDto);
    }

}
