package com.website.bce.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.website.bce.dto.SmtpConfDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    public void sendEmailToMe(String message) {
        ObjectMapper mapper = new ObjectMapper();
        SmtpConfDto smtpConfDto = null;
        try {
            smtpConfDto = mapper.readValue(new File("/resource/smtpConf.json"), SmtpConfDto.class);
        } catch (IOException e) {
            throw new RuntimeException("SmtpConfDto configuration file cannot be found", e);
        }
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", smtpConfDto.getSmtpService());
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.smtp.port", smtpConfDto.getSmtpPort());
        properties.setProperty("mail.smtp.socketFactory.port", smtpConfDto.getSmtpPort());
        properties.put("mail.smtp.auth", "true");
        //properties.put("mail.debug", "true");

        final String username = smtpConfDto.getUsername();
        final String password = smtpConfDto.getPasswd();

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
