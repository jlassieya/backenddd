package projectspringboot2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service

public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private SimpleMailMessage emailVerificationTemplate;

    public void EnvoyerEmailDeVerification(String to, String verificationCode) {
        SimpleMailMessage message = new SimpleMailMessage(emailVerificationTemplate);
        message.setTo(to);
        message.setText("Votre code de vérification est: " + verificationCode);
        mailSender.send(message);
    }
}
