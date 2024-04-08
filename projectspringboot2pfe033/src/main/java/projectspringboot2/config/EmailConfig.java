package projectspringboot2.config;

 import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;
@Configuration

public class EmailConfig {
    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("testemailsender1998@gmail.com");
        mailSender.setPassword("idov rqtz qrkr zuvd"); // Update with the generated app password

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        return mailSender;
    }

    @Bean
    public SimpleMailMessage emailVerificationTemplate() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("colisenroute@gmail.com");
        message.setSubject("Vérification de la réinitialisation du mot de passe");
        return message;
    }
}
