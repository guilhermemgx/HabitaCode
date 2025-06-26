package com.example.HabitaCode.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;

    public void enviarVerificacaoEmail(String destinatario, String token) {
        String link = "https://habita.vercel.app/verificar?token=" + token;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(destinatario);
        message.setSubject("Confirmação de E-mail - HabitaCode");
        message.setText("Olá! Para ativar sua conta, clique no link abaixo:\n\n" + link);

        mailSender.send(message);
    }


}
