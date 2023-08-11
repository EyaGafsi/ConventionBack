package com.proxym.convention.convention.service.serviceImpl;

import com.proxym.convention.convention.dao.AvantageRepository;
import com.proxym.convention.convention.dao.ConventionRepository;
import com.proxym.convention.convention.dto.AvantageDTO;
import com.proxym.convention.convention.dto.AvantageDTOMapper;
import com.proxym.convention.convention.dto.ConventionDTO;
import com.proxym.convention.convention.dto.ConventionDTOMapper;
import com.proxym.convention.convention.entities.Avantage;
import com.proxym.convention.convention.entities.Convention;
import com.proxym.convention.convention.service.ConventionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConventionServiceImpl implements ConventionService {
  @Autowired
  ConventionRepository conventionRepository;
  @Autowired
  AvantageRepository avantageRepository;

  @Autowired
  AvantageDTOMapper avantageDTOMapper;
  @Autowired
  ConventionDTOMapper conventionDTOMapper;
  @Autowired
  private SimpMessagingTemplate messageSendingTemplate;
  @Override
  public ConventionDTO saveConvention(ConventionDTO conventionDTO) {
    messageSendingTemplate.convertAndSend("/topic/progress", "la convention "+conventionDTO.getNomConvention()+" a été ajouté");
    Convention convention=conventionDTOMapper.toConventionEntity(conventionDTO);
     conventionRepository.save(convention);
     return conventionDTOMapper.apply(convention);
  }
  @Override
  public ConventionDTO ajouterConvention(ConventionDTO conventionDTO) {
    ConventionDTO conventionDTO1= saveConvention(conventionDTO);
    Convention convention=conventionRepository.findById(conventionDTO1.getConventionId()).get();
    for (Avantage avantage : convention.getAvantages()) {
    avantage.setConvention(convention);
    avantageRepository.save(avantage);
  }
    return conventionDTOMapper.apply(convention);
  }
  @Override
  public void deleteAvantage(Avantage avantage) {
    avantageRepository.delete(avantage);
  }

  @Override
  @Transactional
  public void updateConvention(ConventionDTO conventionDTO) {
    messageSendingTemplate.convertAndSend("/topic/progress", "la convention "+conventionDTO.getNomConvention()+" a été modifié");
    Convention convention = conventionRepository.findById(conventionDTO.getConventionId()).get();
    convention.setAdresseConvention(conventionDTO.getAdresseConvention());
    convention.setBeneficiaireConvention(conventionDTO.getBeneficiaireConvention());
    convention.setObjectifConvention(conventionDTO.getObjectifConvention());
    convention.setConditionConvention(conventionDTO.getConditionConvention());
    convention.setNomConvention(conventionDTO.getNomConvention());
    convention.setEmail(conventionDTO.getEmail());
    convention.setTypeConvention(conventionDTO.getTypeConvention());
    convention.setTelephone(conventionDTO.getTelephone());
    conventionRepository.save(convention);
    List<Avantage> existingAvantages = convention.getAvantages();
    for (Avantage existingAvantage : existingAvantages) {
      if (!conventionDTO.getAvantages().contains(existingAvantage)) {
        deleteAvantage(existingAvantage);
      }
    }
    for (Avantage avantage : conventionDTO.getAvantages()) {
      if (avantage.getConvention() == null) {
        avantage.setConvention(convention);
        avantageRepository.save(avantage);
      }
    }
  }

  @Override
  public void deleteConventionById(Long id) {
    Convention convention = conventionRepository.findById(id).orElse(null);
    messageSendingTemplate.convertAndSend("/topic/progress", "la convention "+convention.getNomConvention()+" a été supprimé");
    if (convention != null) {
      for (Avantage avantage : convention.getAvantages()) {
        avantage.setConvention(null);
        deleteAvantage(avantage);
      }
      conventionRepository.delete(convention);
    }
  }
  @Override
  public ConventionDTO getConvention(Long id) {
    Optional<Convention> convention = conventionRepository.findById(id);
    return convention.map(conventionDTOMapper::apply).orElse(null);
  }

  @Override
  public List<ConventionDTO> getListConvention() {
    List<Convention> conventions = conventionRepository.findAll();
    return conventions.stream()
      .map(conventionDTOMapper::apply)
      .collect(Collectors.toList());
  }

  @Override
  public Page<ConventionDTO> getAllConvention(String nom, Pageable pageable, String sortBy, String sortOrder) {
    if(nom.isEmpty()) {
      if (sortBy == "") {
        return conventionRepository.findAll(pageable).map(conventionDTOMapper::apply);

      }
      else {
        switch (sortBy.toLowerCase()) {
          case "nom":
            return conventionRepository.sortByNom(pageable,sortOrder).map(conventionDTOMapper::apply);
          case "email":
            return conventionRepository.sortByEmail(pageable,sortOrder).map(conventionDTOMapper::apply);
          default:
            return conventionRepository.findByTypeConvention(pageable,sortBy).map(conventionDTOMapper::apply);
        }
      }}
    else{
      return conventionRepository.findByNomConvention(nom,pageable).map(conventionDTOMapper::apply);
    }
  }
  @Override
  public boolean isNomUniqueUpdate(String nom,Long conventionId) {
    Convention convention= conventionRepository.findById(conventionId).get();
    Convention existingConvention = conventionRepository.findByNomConvention(nom);
    return existingConvention==null || convention.getNomConvention().equals(existingConvention.getNomConvention());
  }
  @Override
  public boolean isEmailUniqueUpdate(String email,Long conventionId) {
    Convention existingConvention = conventionRepository.findByEmail(email);
   Convention convention= conventionRepository.findById(conventionId).get();
    return existingConvention == null || convention.getEmail().equals(email);
  }
  @Override
  public boolean isNomUnique(String nom) {
    Convention existingConvention = conventionRepository.findByNomConvention(nom);
    return existingConvention==null;
  }
  @Override
  public boolean isEmailUnique(String email) {
    Convention existingConvention = conventionRepository.findByEmail(email);
      return existingConvention == null;
    }

}
