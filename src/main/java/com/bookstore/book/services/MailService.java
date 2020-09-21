package com.bookstore.book.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    private static final Logger logger = LoggerFactory.getLogger(MailService.class);

    @Autowired
    @Qualifier("gmail")
    private JavaMailSender mailSender;

    public void sendMail( String subject, String toAddresses, String body) {
        MimeMessagePreparator preparator = mimeMessage -> {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
            message.setTo(toAddresses.split("[,;]"));
            message.setFrom("Raziel");
            message.setSubject(subject);
            message.setText(body, false);
        };
        mailSender.send(preparator);
        logger.info("Email sent successfully To {} with Subject {}", toAddresses,  subject);
    }
}
