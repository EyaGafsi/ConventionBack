package com.proxym.convention.demande.controller;

import com.proxym.convention.demande.dao.DemandeAvantageRepository;
import com.proxym.convention.demande.dto.DemandeDTO;
import com.proxym.convention.demande.entities.Demande;
import com.proxym.convention.demande.service.DemandeService;
import com.proxym.convention.user.dto.UserDTO;
import com.proxym.convention.user.service.UserService;
import com.proxym.convention.user.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class demandeController {
  @Autowired
  private DemandeService demandeService;
  @Autowired
  private UserService userService;
  @Autowired
  private DemandeAvantageRepository demandeAvantageRepository;

  @PostMapping("/ajouterDemande")
  @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
  public void ajouterDemande(@Valid @RequestBody DemandeDTO demandeDTO, @AuthenticationPrincipal UserDetails userDetails, BindingResult bindingResult)
  {
    if (!bindingResult.hasErrors()) {
     demandeService.save(demandeDTO,userDetails);}

  }
  @GetMapping({"/uniqueNameDemande"})
  @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
  public Boolean uniqueName(@RequestParam("name") String name) {
    return demandeService.isNomUnique(name);
  }
  @GetMapping({"/uniqueEmailDemande"})
  @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
  public Boolean uniqueNameUpdate(@RequestParam("email") String email) {
    return demandeService.isEmailUnique(email);
  }
  @GetMapping("/demandes")
  @PreAuthorize("hasRole('ROLE_RH')")
  public Page<DemandeDTO> afficherDemandes(@RequestParam("name") String name,@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int size,@RequestParam("sortBy") String sortBy, @RequestParam(defaultValue = "asc") String sortOrder)
  {
    return demandeService.getAllDemandesParPage(name,PageRequest.of(page, size), sortBy, sortOrder);

}
  @GetMapping("/demandesUser")
  @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
  public Page<DemandeDTO> afficherDemandesUser(@AuthenticationPrincipal UserDetails userDetails,@RequestParam("name") String name,@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int size,@RequestParam("sortBy") String sortBy, @RequestParam(defaultValue = "asc") String sortOrder)
  {
    return demandeService.getAllDemandesUserParPage(userDetails,name,PageRequest.of(page, size), sortBy, sortOrder);

  }
  @PostMapping("/accepterDemande")
  @PreAuthorize("hasRole('ROLE_RH')")
  public void accepterDemande(@RequestBody DemandeDTO demandeDTO)
  {
    demandeService.accepterDemande(demandeDTO);
  }
  @PostMapping("/refuserDemande")
  @PreAuthorize("hasRole('ROLE_RH')")
  public void refuserDemande(@RequestBody DemandeDTO demandeDTO)
  {
    demandeService.refuserDemande(demandeDTO);
  }
}
