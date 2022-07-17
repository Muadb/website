package com.website.bce.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.website.bce.dto.SmtpConfDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

@Service
public class EmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    @Value("${app.username}")
    private String USERNAME;

    @Value("${app.password}")
    private String PASSWORD;

    public void sendEmailToMe(String message) {
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", "smtp.gmail.com");
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.smtp.port", "465");
        properties.setProperty("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl", "true");
        properties.put("mail.debug", "true");

        Session session = Session.getDefaultInstance(properties,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(USERNAME, PASSWORD);
                    }
                });

        try {

            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress("barkancanerdogdu@gmail.com"));
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress("barkancanerdogdu@icloud.com"));
            mimeMessage.setSubject("barkancanerdogdu.com");
            mimeMessage.setText(message);
            Transport.send(mimeMessage);
            LOGGER.info("Sent email to barkancanerdogdu@icloud.com successfully....");
        } catch (MessagingException mex) {
            throw new RuntimeException("Mail cannot be sent", mex);
        }
    }
}
