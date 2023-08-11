package com.proxym.convention.demande.dto;
import com.proxym.convention.demande.entities.DemandeAvantage;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class DemandeAvantageDTOMapper implements Function<DemandeAvantage, DemandeAvantageDTO> {
  @Override
  public DemandeAvantageDTO apply(DemandeAvantage demandeAvantage) {
    return new DemandeAvantageDTO(
      demandeAvantage.getDetails(),
      demandeAvantage.getDemande()
    );
  }
}
