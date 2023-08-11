package com.proxym.convention.convention.dto;

import com.proxym.convention.convention.entities.Avantage;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
@Getter
@Setter
public class ConventionDTO {

  private Long conventionId;
  @NotNull
  @Size(max = 50)
  private String nomConvention;
  @NotNull
  @Size(min = 8, max = 8)
  private String telephone;
  @NotNull
  @Size( max = 50)
  private String email;
  @NotNull
  @Size(max = 50)
  private String adresseConvention;
  @NotNull
  @Size(max = 100)
  private String beneficiaireConvention;
  @NotNull
  @Size(max = 100)
  private String typeConvention;
  @NotNull
  @Size(max = 100)
  private String objectifConvention;
  @NotNull
  @Size(max = 100)
  private String conditionConvention;
  private List<Avantage> avantages;

  public ConventionDTO() {
  }

  public ConventionDTO(Long conventionId,String nomConvention, String telephone, String adresseConvention, String email,String beneficiaireConvention, String typeConvention, String objectifConvention, String conditionConvention, List<Avantage> avantages) {
    this.conventionId=conventionId;
    this.nomConvention = nomConvention;
    this.telephone = telephone;
    this.adresseConvention = adresseConvention;
    this.beneficiaireConvention = beneficiaireConvention;
    this.typeConvention = typeConvention;
    this.objectifConvention = objectifConvention;
    this.conditionConvention = conditionConvention;
    this.avantages = avantages;
    this.email=email;
  }


  public ConventionDTO(String nomConvention, String telephone, String adresseConvention, String email,String beneficiaireConvention, String typeConvention, String objectifConvention, String conditionConvention) {
    this.nomConvention = nomConvention;
    this.telephone = telephone;
    this.adresseConvention = adresseConvention;
    this.beneficiaireConvention = beneficiaireConvention;
    this.typeConvention = typeConvention;
    this.objectifConvention = objectifConvention;
    this.conditionConvention = conditionConvention;
    this.email=email;
  }
}
