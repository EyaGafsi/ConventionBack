package com.proxym.convention.convention.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
@Getter
@Setter
@Entity
public class Convention {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long conventionId;
  @Column(unique = true)

  private String nomConvention;

  private String telephone;
  @Column(unique = true)

  private String email;

  private String adresseConvention;

  private String beneficiaireConvention;

  private String typeConvention;

  private String objectifConvention;

  private String conditionConvention;
  @OneToMany(mappedBy = "convention")
  private List<Avantage> avantages;

  public Convention() {
  }

  public Convention(String nomConvention, String telephone, String adresseConvention, String email,String beneficiaireConvention, String typeConvention, String objectifConvention, String conditionConvention, List<Avantage> avantages) {
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

  public Convention(String nomConvention, String telephone, String email, String adresseConvention, String beneficiaireConvention, String typeConvention, String objectifConvention, String conditionConvention) {
    this.nomConvention = nomConvention;
    this.telephone = telephone;
    this.email = email;
    this.adresseConvention = adresseConvention;
    this.beneficiaireConvention = beneficiaireConvention;
    this.typeConvention = typeConvention;
    this.objectifConvention = objectifConvention;
    this.conditionConvention = conditionConvention;
  }

}
