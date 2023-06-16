package com.example.webxemphim.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Service
public class MailServices {

    @Autowired
    private JavaMailSender javaMailSender;
    public void sendEmail(String recipientEmail, String subject,String content) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("vuanhpham@gmail.com", "vuanhpham");
        helper.setTo(recipientEmail);


        helper.setSubject(subject);

        helper.setText(content, true);
        javaMailSender.send(message);
    }
}
