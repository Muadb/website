package com.website.bce.service;

import com.website.bce.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    public void sendEmailToMe(String message) {
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", "smtp.gmail.com");
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.smtp.port", "465");
        properties.setProperty("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.auth", "true");
        //properties.put("mail.debug", "true");

        final String username = "barkancanerdogdu@gmail.com";//
        final String password = "xvgstchdbmivhyjv";

        Session session = Session.getDefaultInstance(properties,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
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
            mex.printStackTrace();
        }
    }
}
