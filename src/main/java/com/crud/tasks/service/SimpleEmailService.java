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

    public void send(final Mail mail, EmailTemplateSelector template) {

        LOGGER.info("Starting email preparation...");
        try {
            javaMailSender.send(createMimeMessage(mail, template));
            LOGGER.info("Email has been sent");
        } catch (MailException ex) {
            LOGGER.error("Fail to precess email sending..", ex.getMessage(), ex);
        }
    }

    private MimeMessagePreparator createMimeMessage(final Mail mail, EmailTemplateSelector template) {
        return mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(mail.getMailTo());
            messageHelper.setSubject(mail.getSubject());
            messageHelper.setText(getMailHtmlTextForTemplateSelector(mail.getMessage(), template), true);
            messageHelper.setCc(mail.getToCC());
    };
    }

    private SimpleMailMessage createMailMessage(final Mail mail) {

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mailCreatorService.buildTrelloCardMail(mail.getMessage()));

        if (mail.getToCC() != null) {
            mailMessage.setCc(mail.getToCC());
            LOGGER.info("Email has been sent also to CC");
        } else {
            LOGGER.info("Field toCC is empty");
        }
        return mailMessage;
    }

    private String getMailHtmlTextForTemplateSelector(String message, EmailTemplateSelector template) {
        if (template == EmailTemplateSelector.TRELLO_CARD_EMAIL) {
            return mailCreatorService.buildTrelloCardMail(message);
        } else if (template == EmailTemplateSelector.SCHEDULED_EMAIL) {
            return mailCreatorService.buildScheduledMail(message);
        }
        return "";
    }
}

