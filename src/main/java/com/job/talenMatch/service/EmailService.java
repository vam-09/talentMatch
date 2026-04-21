package com.job.talenMatch.service;

import com.job.talenMatch.model.Job;
import com.job.talenMatch.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailService {

    JavaMailSender javaMailSender;

    @Autowired
    EmailService(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }
    @Value("${spring.mail.username}")
    private String sender;

    public String sendMail(String emailId){
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(sender);
            simpleMailMessage.setSubject("New Job For You");
            simpleMailMessage.setTo(emailId);
            simpleMailMessage.setText("Hi, we have found a new job for you.");

            javaMailSender.send(simpleMailMessage);
            return "Mail sent successfully";
        }
        catch (Exception e){
            return "Error while sending mail";
        }
    }

    public void sendMail(List<User> users, Job job){
        for(User user : users) {

            String text = "Hi " + user.getUserName() + ",\n\nWe have found a new job for you that matches your skills:\n\n" +
                    "Job Title: " + job.getJobTitle() + "\n" +
                    "Company: " + job.getCompanyName() + "\n" +
                    "Location: " + job.getLocation() + "\n" +
                    "Description: " + job.getJobDescription() + "\n\n" +
                    "Best regards,\nTalentMatch Team";

            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(sender);
            simpleMailMessage.setSubject("New Job For You");
            simpleMailMessage.setTo(user.getEmail());
            simpleMailMessage.setText(text);

            javaMailSender.send(simpleMailMessage);
        }
    }
}
