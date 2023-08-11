package com.proxym.convention.demande.dto;

import com.proxym.convention.demande.entities.Demande;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class DemandeDTOMapper implements Function<Demande, DemandeDTO> {
  @Override
  public DemandeDTO apply(Demande demande) {
    return new DemandeDTO(
      demande.getDemandeId(),
      demande.getNomDemande(),
      demande.getTelephone(),
      demande.getAdresseDemande(),
      demande.getEmailDemande(),
      demande.getBeneficiaireDemande(),
      demande.getTypeDemande(),
      demande.getObjectifDemande(),
      demande.getConditionDemande(),
      demande.getStatus(),
      demande.getDemandeAvantages(),
      demande.getUser()
    );
  }
  public Demande toDemandeEntity(DemandeDTO demandeDTO) {

    return new Demande(
      demandeDTO.getNomDemande(),
      demandeDTO.getTelephone(),
      demandeDTO.getAdresseDemande(),
      demandeDTO.getEmailDemande(),
      demandeDTO.getBeneficiaireDemande(),
      demandeDTO.getTypeDemande(),
      demandeDTO.getObjectifDemande(),
      demandeDTO.getConditionDemande(),
      demandeDTO.getStatus(),
      demandeDTO.getDemandeAvantages(),
      demandeDTO.getUser()
    );
  }
}
