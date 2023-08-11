package com.proxym.convention.demande.entities;



import com.proxym.convention.user.entities.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
@Getter
@Setter
@Entity
public class Demande {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long demandeId;
  @Column(unique = true)
  private String nomDemande;

  private String telephone;

  private String adresseDemande;
  @Column(unique = true)
  private String emailDemande;

  private String beneficiaireDemande;

  private String typeDemande;

  private String objectifDemande;

  private String conditionDemande;
  private LocalDate dateEnvoi;

  private String status;
  @OneToMany(mappedBy = "demande")
  private List<DemandeAvantage> demandeAvantages;
  @ManyToOne(cascade = CascadeType.ALL)
  private User user;
  public Demande() {
  }

  public Demande(String nomDemande, String telephone, String adresseDemande, String emailDemande, String beneficiaireDemande, String typeDemande, String objectifDemande, String conditionDemande,String status, List<DemandeAvantage> demandeAvantages,User user) {
    this.nomDemande = nomDemande;
    this.telephone = telephone;
    this.adresseDemande = adresseDemande;
    this.emailDemande = emailDemande;
    this.beneficiaireDemande = beneficiaireDemande;
    this.typeDemande = typeDemande;
    this.objectifDemande = objectifDemande;
    this.conditionDemande = conditionDemande;
    this.demandeAvantages = demandeAvantages;
    this.user=user;
    this.dateEnvoi = LocalDate.now();
    this.status=status;
  }
  public void setDateEnvoi() {
    this.dateEnvoi = LocalDate.now();
  }

}
