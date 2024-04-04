package com.pixelpunch.vaultify.core.service.implementations;

import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService implements com.pixelpunch.vaultify.core.service.IEmailService {

    private final JavaMailSender emailSender;

    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendConfirmationEmail(String userEmail, String confirmationCode) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(userEmail);
        message.setSubject("Подтверждение регистрации");
        message.setText("Для завершения регистрации введите следующий код подтверждения: " + confirmationCode);
        emailSender.send(message);
    }
}