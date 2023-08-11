package com.proxym.convention.convention.dto;

import com.proxym.convention.convention.entities.Convention;

import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class ConventionDTOMapper implements Function<Convention, ConventionDTO> {
  @Override
  public ConventionDTO apply(Convention convention) {
    return new ConventionDTO(
      convention.getConventionId(),
      convention.getNomConvention(),
      convention.getTelephone(),
      convention.getAdresseConvention(),
      convention.getEmail(),
      convention.getBeneficiaireConvention(),
      convention.getTypeConvention(),
      convention.getObjectifConvention(),
      convention.getConditionConvention(),
      convention.getAvantages()
    );
  }

  public Convention toConventionEntity(ConventionDTO conventionDTO) {
    return new Convention(
      conventionDTO.getNomConvention(),
      conventionDTO.getTelephone(),
      conventionDTO.getAdresseConvention(),
      conventionDTO.getEmail(),
      conventionDTO.getBeneficiaireConvention(),
      conventionDTO.getTypeConvention(),
      conventionDTO.getObjectifConvention(),
      conventionDTO.getConditionConvention(),
      conventionDTO.getAvantages()
    );
  }
}
