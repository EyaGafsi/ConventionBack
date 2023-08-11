package com.proxym.convention.demande.dto;

import com.proxym.convention.demande.entities.DemandeAvantage;
import com.proxym.convention.user.entities.User;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
@Getter
@Setter
public class DemandeDTO {

    private Long demandeId;
    @NotNull
    @Size(max = 50)
    private String nomDemande;
    @NotNull
    @Size(min = 8, max = 8)
    private String telephone;
    @NotNull
    @Size(max = 50)
    private String adresseDemande;
    @Column(unique = true)
    @NotNull
    @Size(max = 50)
    private String emailDemande;
    @NotNull
    @Size(max = 100)
    private String beneficiaireDemande;
    @NotNull
    @Size(max = 100)
    private String typeDemande;
    @NotNull
    @Size(max = 100)
    private String objectifDemande;
    @NotNull
    @Size(max = 100)
    private String conditionDemande;
    private LocalDate dateEnvoi;

    private String status;
    private List<DemandeAvantage> demandeAvantages;
    private User user;
    public DemandeDTO() {
    }

    public DemandeDTO(Long demandeId,String nomDemande, String telephone, String adresseDemande, String emailDemande, String beneficiaireDemande, String typeDemande, String objectifDemande, String conditionDemande,String status, List<DemandeAvantage> demandeAvantages,User user) {
      this.demandeId=demandeId;
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
