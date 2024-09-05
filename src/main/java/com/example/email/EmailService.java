package com.example.email;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void  sendthesimpleemailmess(){
        SimpleMailMessage smpmail =  new SimpleMailMessage();
        smpmail.setTo("jetbrains524@gmail.com");
        smpmail.setCc("jetbrains524@gmail.com");
        smpmail.setSubject("test email");
        String text = "I am testing the simple email service  ";
        smpmail.setText(text);
        javaMailSender.send(smpmail);
    }

    public void sendthecomplexmain(String message) throws MessagingException {
        MimeMessage  min =  javaMailSender.createMimeMessage();
        MimeMessageHelper minhelper =  new MimeMessageHelper(min ,"utf-8");
        minhelper.setTo("jetbrains524@gmail.com");
//      minhelper.setCc("tuhsartiwaskar");
        minhelper.setSubject("Sending the complesx email  ");
        minhelper.setText(message,true);
        javaMailSender.send(min);
    }

}
