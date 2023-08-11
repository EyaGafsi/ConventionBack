package com.proxym.convention.convention.dto;

import com.proxym.convention.convention.entities.Avantage;
import com.proxym.convention.demande.dto.DemandeAvantageDTO;
import com.proxym.convention.demande.entities.DemandeAvantage;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class AvantageDTOMapper implements Function<Avantage, AvantageDTO> {
  @Override
  public AvantageDTO apply(Avantage avantage) {
    return new AvantageDTO(
      avantage.getDetails(),
      avantage.getConvention()
    );
  }
  public Avantage avantageEntity(AvantageDTO avantageDTO) {
    return new Avantage(
      avantageDTO.getDetails(),
      avantageDTO.getConvention()
    );
  }
}
