package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class SimpleEmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleMailMessage.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    MailCreatorService mailCreatorService;

    public void send(final Mail mail) {

        LOGGER.info("Starting email preparation...");
        try {
            javaMailSender.send(createMimeMessage(mail));
            LOGGER.info("Email has been sent");
        } catch (MailException ex) {
            LOGGER.error("Fail to precess email sending..", ex.getMessage(), ex);
        }
    }

    private MimeMessagePreparator createMimeMessage(final Mail mail) {
//
//        if (mail.getToCC()!=null) {
//            simpleMailMessage.setCc(mail.getToCC());
//        LOGGER.info("Email has been sent also to CC");
//    } else {
//        LOGGER.info("Field toCC is empty");
//    }
        return mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(mail.getMailTo());
            messageHelper.setSubject(mail.getSubject());
            messageHelper.setText(mailCreatorService.buildTrelloCardMail(mail.getMessage()), true);
        };
    }
//
//    private SimpleMailMessage createMailMessage(final Mail mail) {
//        SimpleMailMessage mailMessage = new SimpleMailMessage();
//        mailMessage.setTo(mail.getMailTo());
//        mailMessage.setSubject(mail.getSubject());
//        mailMessage.setText(mailCreatorService.buildTrelloCardMail(mail.getMessage()));
//        return mailMessage;
//    }

//    }    private SimpleMailMessage createMailMessage(final Mail templates.mail) {
//        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//        simpleMailMessage.setTo(templates.mail.getMailTo());
//        simpleMailMessage.setSubject(templates.mail.getSubject());
//        simpleMailMessage.setText(templates.mail.getMessage());
//
//        if (templates.mail.getToCC()!=null) {
//            simpleMailMessage.setCc(templates.mail.getToCC());
//            LOGGER.info("Email has been sent also to CC");
//        } else {
//            LOGGER.info("Field toCC is empty");
//        }
//        return simpleMailMessage;
//    }
}

