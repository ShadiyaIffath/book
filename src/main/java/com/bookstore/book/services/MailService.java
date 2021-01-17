package com.bookstore.book.services;

import com.bookstore.book.repositories.AccountRepository;
import com.bookstore.book.utils.CodeGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    private static final Logger logger = LoggerFactory.getLogger(MailService.class);

    @Autowired
    @Qualifier("gmail")
    private JavaMailSender mailSender;

    @Autowired
    private AccountRepository accountRepository;

    public String findLoggedInAccountEmail(){
        UserDetails userDetails =
                (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getUsername();
    }

    public String sendProfileUpdateRequestMail(int accountId){
        String code = "";
        String accountEmail = accountRepository.findById(accountId).getEmail();
        code = CodeGenerator.getAlphaNumericString(7);
        sendMail("Confirmation Code", accountEmail, "You have requested to change your account login credentials this email is sent with the code " + code + " for the confirmation of this process.");
        return code;
    }

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
