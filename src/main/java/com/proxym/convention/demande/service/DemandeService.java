package com.proxym.convention.demande.service;

import com.proxym.convention.demande.dto.DemandeDTO;
import com.proxym.convention.demande.entities.Demande;
import com.proxym.convention.user.dto.UserDTO;
import com.proxym.convention.user.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;

public interface DemandeService {
  DemandeDTO save(DemandeDTO demandeDTO, UserDetails user);
   DemandeDTO saveDemande(DemandeDTO demandeDTO);
    Page<DemandeDTO> getAllDemandesParPage(String nom, Pageable pageable, String sortBy, String sortOrder);
  public void accepterDemande(DemandeDTO demandeDTO);
  void refuserDemande(DemandeDTO demandeDTO);
   boolean isNomUnique(String nom) ;
   boolean isEmailUnique(String email) ;
   Page<DemandeDTO> getAllDemandesUserParPage(UserDetails userDetails, String nom, Pageable pageable, String sortBy, String sortOrder);

  }
