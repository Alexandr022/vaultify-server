package com.pixelpunch.vaultify.core.service;

public interface IEmailService {
    // Метод для отправки письма подтверждения по электронной почте
    void sendConfirmationEmail(String userEmail, String confirmationCode);
}
