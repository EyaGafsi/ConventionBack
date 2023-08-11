package com.proxym.convention.demande.dto;

import com.proxym.convention.demande.entities.Demande;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class DemandeAvantageDTO {

  private Long avantageId;
  @NotNull
  @Size(max = 50)
  private String details;
  private Demande demande;

  public DemandeAvantageDTO() {
  }

  public DemandeAvantageDTO(String details) {
    this.details = details;
  }

  public DemandeAvantageDTO(String details, Demande demande) {
    this.details = details;
    this.demande = demande;
  }



}
