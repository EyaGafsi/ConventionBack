package com.proxym.convention.convention.service;

import com.proxym.convention.convention.dto.AvantageDTO;
import com.proxym.convention.convention.dto.ConventionDTO;
import com.proxym.convention.convention.entities.Avantage;
import com.proxym.convention.convention.entities.Convention;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ConventionService {
  ConventionDTO saveConvention(ConventionDTO c);
  void updateConvention(ConventionDTO p);
   void deleteAvantage(Avantage avantage) ;
  void deleteConventionById(Long id);
  ConventionDTO getConvention(Long id);
  ConventionDTO ajouterConvention(ConventionDTO conventionDTO);
   List<ConventionDTO> getListConvention();
    Page<ConventionDTO> getAllConvention(String nom, Pageable pageable, String sortBy, String sortOrder) ;
     boolean isNomUniqueUpdate(String nom,Long conventionId) ;
   boolean isEmailUniqueUpdate(String email,Long conventionId) ;
   boolean isEmailUnique(String email) ;
   boolean isNomUnique(String nom);

  }
