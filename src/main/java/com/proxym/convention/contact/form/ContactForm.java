package com.proxym.convention.contact.form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactForm {
  private String name;
  private String email;
  private String subject;

  private String message;

  public ContactForm(String name, String email,  String subject, String message) {
    this.name = name;
    this.subject=subject;
    this.email = email;
    this.message = message;
  }

  public ContactForm() {
  }
}
