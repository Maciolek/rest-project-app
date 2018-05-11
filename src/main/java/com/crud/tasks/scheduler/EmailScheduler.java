package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailScheduler {

    private static String SUBJECT = "Tasks: Onces a day email";
    @Autowired
    private SimpleEmailService emailService;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private AdminConfig adminConfig;

    @Scheduled(fixedDelay = 1000000)
//            //(cron ="0 0 10 * * *")
    public void sendInformationEmail (){
        emailService.send(new Mail(
                adminConfig.getAdminMail(),
                SUBJECT,
                "Currently, in database you got " + taskRepository.count() + " tasks"
        ));
    }
}
