package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class SimpleEmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleMailMessage.class);

    @Autowired
    private JavaMailSender javaMailSender;

    public void send(final Mail mail) {

        LOGGER.info("Starting email preparation...");
        try {
            javaMailSender.send(createMailMessage(mail));
            LOGGER.info("Email has been sent");
        } catch (MailException ex) {
            LOGGER.error("Fail to precess email sending..", ex.getMessage(), ex);
        }
    }

    private SimpleMailMessage createMailMessage(final Mail mail) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(mail.getMailTo());
        simpleMailMessage.setSubject(mail.getSubject());
        simpleMailMessage.setText(mail.getMessage());

        if (mail.getToCC()!=null) {
            simpleMailMessage.setCc(mail.getToCC());
            LOGGER.info("Email has been sent also to CC");
        } else {
            LOGGER.info("Field toCC is empty");
        }
        return simpleMailMessage;
    }
}

