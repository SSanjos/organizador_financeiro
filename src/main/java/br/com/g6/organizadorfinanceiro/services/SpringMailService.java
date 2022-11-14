package br.com.g6.organizadorfinanceiro.services;

import com.sun.mail.smtp.SMTPSendFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class SpringMailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendMail(String toEmail, String subject, String body) throws SMTPSendFailedException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("meu.boleto.pago.g6@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);

        mailSender.send(message);

    }
}
