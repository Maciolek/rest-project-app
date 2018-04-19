package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class SimpleEmailServiceTest {

    @InjectMocks
    private SimpleEmailService simpleEmailService;

    @Mock
    private JavaMailSender javaMailSender;

    @Test
    public void shouldSendEmailToCC() {

        //given
        Mail mail = new Mail("maciolek.dariusz@gmail.com", "mail testowy", "pierwszy test", "sa@test.pl");

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());
        mailMessage.setCc(mail.getToCC());

        //when
        simpleEmailService.send(mail);

        //then
        verify(javaMailSender, times(1)).send(mailMessage);
        mailMessage.toString();
    }

    @Test
    public void shouldSendEmailWithoutToCC() {

        //given
        Mail mail = new Mail("maciolek.dariusz@gmail.com", "mail testowy", "pierwszy test");

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());

        //when
        simpleEmailService.send(mail);

        //then
        verify(javaMailSender, times(1)).send(mailMessage);
        mailMessage.toString();
    }
}