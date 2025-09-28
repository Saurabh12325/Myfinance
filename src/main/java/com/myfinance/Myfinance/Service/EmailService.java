package com.myfinance.Myfinance.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sendinblue.ApiClient;
import sendinblue.ApiException;
import sendinblue.Configuration;
import sibApi.TransactionalEmailsApi;
import sibModel.SendSmtpEmail;
import sibModel.SendSmtpEmailSender;
import sibModel.SendSmtpEmailTo;
;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class EmailService {
//    private final JavaMailSender mailSender;
//
//    @Value("${spring.mail.properties.mail.smtp.from}")
//    private String fromEmail;
//    public  void sendEmail(String to, String subject, String body) {
//      try{
//          SimpleMailMessage message = new SimpleMailMessage();
//          message.setFrom(fromEmail);
//          message.setTo(to);
//          message.setSubject(subject);
//          message.setText(body);
//          mailSender.send(message);
//      } catch (Exception e) {
//          throw new RuntimeException(e.getMessage());
//      }
//    }


        @Value("${brevo.api.key}")
        private String apiKey;

        @Value("${brevo.sender.email}")
        private String senderEmail;

        @Value("${brevo.sender.name}")
        private String senderName;

        public void sendEmail(String to, String subject, String body) {
            ApiClient defaultClient = Configuration.getDefaultApiClient();
            defaultClient.setApiKey(apiKey);

            TransactionalEmailsApi apiInstance = new TransactionalEmailsApi(defaultClient);

            SendSmtpEmailSender sender = new SendSmtpEmailSender();
            sender.setName(senderName);
            sender.setEmail(senderEmail);

            SendSmtpEmailTo recipient = new SendSmtpEmailTo();
            recipient.setEmail(to);

            SendSmtpEmail email = new SendSmtpEmail();
            email.setSender(sender);
            email.setTo(Arrays.asList(recipient));
            email.setSubject(subject);
            email.setHtmlContent((body));

            try {
                apiInstance.sendTransacEmail(email);
                System.out.println("Email sent to " + to);
            } catch (ApiException e) {
                throw new RuntimeException("Error sending email: " + e.getResponseBody());
            }
        }
    }


