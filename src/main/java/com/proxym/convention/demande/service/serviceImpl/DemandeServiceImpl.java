package com.proxym.convention.demande.service.serviceImpl;

import com.proxym.convention.convention.dao.AvantageRepository;
import com.proxym.convention.convention.dao.ConventionRepository;
import com.proxym.convention.convention.dto.ConventionDTO;
import com.proxym.convention.convention.dto.ConventionDTOMapper;
import com.proxym.convention.convention.entities.Avantage;
import com.proxym.convention.convention.entities.Convention;
import com.proxym.convention.convention.service.ConventionService;
import com.proxym.convention.demande.dao.DemandeAvantageRepository;
import com.proxym.convention.demande.dao.DemandeRepository;
import com.proxym.convention.demande.dto.DemandeAvantageDTOMapper;
import com.proxym.convention.demande.dto.DemandeDTO;
import com.proxym.convention.demande.dto.DemandeDTOMapper;
import com.proxym.convention.demande.entities.Demande;
import com.proxym.convention.demande.entities.DemandeAvantage;
import com.proxym.convention.demande.service.DemandeService;
import com.proxym.convention.user.dao.UserRepository;
import com.proxym.convention.user.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
public class DemandeServiceImpl implements DemandeService {
  @Autowired
  DemandeRepository demandeRepository;
  @Autowired
  UserRepository userRepository;

  @Autowired
  DemandeAvantageRepository demandeAvantageRepository;
  @Autowired
  AvantageRepository avantageRepository;
  @Autowired
  ConventionService conventionService;
  @Autowired
  ConventionRepository conventionRepository;
  final DemandeDTOMapper demandeDTOMapper;
  final DemandeAvantageDTOMapper demandeAvantageDTOMapper;
  final ConventionDTOMapper conventionDTOMapper;

  @Autowired
  private SimpMessagingTemplate messageSendingTemplate;

  public DemandeServiceImpl(DemandeDTOMapper demandeDTOMapper, DemandeAvantageDTOMapper demandeAvantageDTOMapper, ConventionDTOMapper conventionDTOMapper) {
    this.demandeDTOMapper = demandeDTOMapper;
    this.demandeAvantageDTOMapper = demandeAvantageDTOMapper;
    this.conventionDTOMapper = conventionDTOMapper;
  }

  @Override
  public DemandeDTO save(DemandeDTO demandeDTO, UserDetails userDetails) {
      String username = userDetails.getUsername();
    User user = userRepository.findByUserName(username);
    demandeDTO.setDateEnvoi();
    demandeDTO.setStatus("en attente");
    demandeDTO.setUser(user);
    Demande demande=demandeDTOMapper.toDemandeEntity(demandeDTO);
    messageSendingTemplate.convertAndSend("/topic/progress", "vous avez une nouvelle demande " + demande.getNomDemande());
    demandeRepository.save(demande);
    for (DemandeAvantage avantage : demandeDTO.getDemandeAvantages()) {
      avantage.setDemande(demande);
      demandeAvantageRepository.save(avantage);
    }
    return demandeDTOMapper.apply(demande);}




  @Override
  public DemandeDTO saveDemande(DemandeDTO demandeDTO) {
    Demande demande=demandeRepository.findById(demandeDTO.getDemandeId()).get();
    demandeRepository.save(demande);
    for (DemandeAvantage avantage : demandeDTO.getDemandeAvantages()) {
      avantage.setDemande(demande);
      demandeAvantageRepository.save(avantage);
    }
    return demandeDTOMapper.apply(demande);
  }
  @Override
  public Page<DemandeDTO> getAllDemandesParPage(String nom, Pageable pageable, String sortBy, String sortOrder) {
    if (nom.isEmpty()) {
      if (sortBy == null || sortBy.isEmpty()) {
        return demandeRepository.findByStatus("en attente", pageable)
          .map(demandeDTOMapper::apply);
      } else {
        switch (sortBy.toLowerCase()) {
          case "nom":
            return demandeRepository.sortByNomDemande(pageable, sortOrder)
              .map(demandeDTOMapper::apply);
          case "date":
            return demandeRepository.sortByDate(pageable, sortOrder)
              .map(demandeDTOMapper::apply);
          case "email":
            return demandeRepository.sortByEmail(pageable, sortOrder)
              .map(demandeDTOMapper::apply);
          default:
            return demandeRepository.findByTypeDemandeAndStatus(pageable, sortBy, "en attente")
              .map(demandeDTOMapper::apply);
        }
      }
    } else {
      return demandeRepository.findByNomDemandeAndStatus(nom, "en attente", pageable)
        .map(demandeDTOMapper::apply);
    }
  }


  @Override
  public Page<DemandeDTO> getAllDemandesUserParPage(UserDetails userDetails,String nom,Pageable pageable,String sortBy,String sortOrder) {
    String username = userDetails.getUsername();
    User user = userRepository.findByUserName(username);
    if(nom.isEmpty()) {
      if (sortBy == "") {
        return demandeRepository.findByUser(user,pageable).map(demandeDTOMapper::apply);

      }
      else {
        switch (sortBy.toLowerCase()) {
          case "nom":
            return demandeRepository.sortByNomDemande(pageable,user,sortOrder).map(demandeDTOMapper::apply);
          case "date":
            return demandeRepository.sortByDate(pageable,user,sortOrder).map(demandeDTOMapper::apply);
          case "email":
            return demandeRepository.sortByEmail(pageable,user,sortOrder).map(demandeDTOMapper::apply);
          default:
            return demandeRepository.findByTypeDemandeAndUser(pageable,sortBy,user).map(demandeDTOMapper::apply);
        }
      }}
    else{
      return demandeRepository.findByNomDemandeAndUser(nom,user,pageable).map(demandeDTOMapper::apply);
    }
  }


  @Override
  public boolean isNomUnique(String nom) {
    Convention convention=conventionRepository.findByNomConvention(nom);
    Demande existingDemande = demandeRepository.findByNomDemande(nom);
    return existingDemande==null && convention==null;
  }
  @Override
  public boolean isEmailUnique(String email) {
    Convention convention=conventionRepository.findByEmail(email);
    Demande existingDemande = demandeRepository.findByEmailDemande(email);
    return existingDemande == null && convention==null;
  }
  @Override
  @Transactional
  public void accepterDemande(DemandeDTO demandeDTO)
  {
    messageSendingTemplate.convertAndSend("/topic/progress", "la demande "+demandeDTO.getNomDemande()+" a été accépté");
    Demande demande=demandeRepository.findById(demandeDTO.getDemandeId()).get();
    demande.setStatus("accepté");
    demandeRepository.save(demande);
    Convention convention=new Convention(demandeDTO.getNomDemande(),demandeDTO.getTelephone(),demandeDTO.getAdresseDemande(),demandeDTO.getEmailDemande(),demandeDTO.getBeneficiaireDemande(), demandeDTO.getTypeDemande(), demandeDTO.getObjectifDemande(), demandeDTO.getConditionDemande());
    conventionRepository.save(convention);
    for (DemandeAvantage avantage : demandeDTO.getDemandeAvantages()) {
      Avantage av=new Avantage(avantage.getDetails(), convention);
     avantageRepository.save(av);
    }
  }
  @Override
  public void refuserDemande(DemandeDTO demandeDTO)
  {  messageSendingTemplate.convertAndSend("/topic/progress", "la demande "+demandeDTO.getNomDemande()+" a été refusé");
    Demande demande=demandeRepository.findById(demandeDTO.getDemandeId()).get();
    demande.setStatus("refusé");
    demandeRepository.save(demande);
  }
}
