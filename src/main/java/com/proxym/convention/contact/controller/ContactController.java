package com.proxym.convention.contact.controller;

import com.proxym.convention.contact.form.ContactForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {
  private final JavaMailSender emailSender;

  @Autowired
  public ContactController(JavaMailSender emailSender) {
    this.emailSender = emailSender;
  }

  @PostMapping("/contact")
  public void contactUs(@RequestBody ContactForm contactForm) {
    sendEmail(contactForm);
  }

  private void sendEmail(ContactForm contactForm) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo("eyagafsi17@gmail.com");
    message.setSubject(contactForm.getSubject());
    message.setText("Nom : " + contactForm.getName() +
      "\nEmail : " + contactForm.getEmail() +
      "\nMessage : " + contactForm.getMessage());
    emailSender.send(message);
  }
}
