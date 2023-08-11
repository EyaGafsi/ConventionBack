package com.proxym.convention.demande.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Getter
@Setter
@Entity
  public class DemandeAvantage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long avantageId;

    private String details;
    @JsonIgnore
    @ManyToOne
    private Demande demande;

    public DemandeAvantage() {
    }

    public DemandeAvantage(String details) {
      this.details = details;
    }

    public DemandeAvantage(String details, Demande demande) {
      this.details = details;
      this.demande = demande;
    }



  }
