package com.proxym.convention.convention.controller;


import com.proxym.convention.convention.dao.AvantageRepository;
import com.proxym.convention.convention.dto.ConventionDTO;
import com.proxym.convention.convention.service.ConventionService;
import com.proxym.convention.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
@RestController
@CrossOrigin
public class ConventionController {
  @Autowired
  private UserService userService;
  @Autowired
  private ConventionService conventionService;
  @Autowired
  private AvantageRepository avantageRepository;

  @PostMapping({"/ajouterConvention"})
  @PreAuthorize("hasRole('ROLE_RH')")
  public ConventionDTO ajouterConvention(@Valid @RequestBody ConventionDTO conventionDTO, BindingResult bindingResult) {
    if (!bindingResult.hasErrors()) {
      conventionService.ajouterConvention(conventionDTO);
    }
    return conventionDTO;
  }
  @GetMapping({"/afficherConvention"})
  @PreAuthorize("hasRole('ROLE_RH') ")
  public Page<ConventionDTO> afficherConvention(@RequestParam("name") String name, @RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "10") int size, @RequestParam("sortBy") String sortBy, @RequestParam(defaultValue = "asc") String sortOrder)
  {
    return conventionService.getAllConvention(name,PageRequest.of(page, size), sortBy, sortOrder);
  }


  @GetMapping({"/afficherListConvention"})
  @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
  public List<ConventionDTO> afficherListConvention()
  {
    return conventionService.getListConvention();
  }


  @DeleteMapping({"/supprimerConvention"})
  @PreAuthorize("hasRole('ROLE_RH')")
  public void supprimerConvention(@RequestParam Long id) {

    conventionService.deleteConventionById(id);}
  @PutMapping({"/modifierConvention"})
  @PreAuthorize("hasRole('ROLE_RH')")
  public void modifierConvention(@Valid @RequestBody ConventionDTO c,BindingResult bindingResult) {
    if (!bindingResult.hasErrors()) {
      conventionService.updateConvention(c);}

  }
  @GetMapping({"/uniqueNameUpdate"})
  @PreAuthorize("hasRole('ROLE_RH')")
  public Boolean uniqueName(@RequestParam("name") String name,@RequestParam("conventionId") Long conventionId) {
    return conventionService.isNomUniqueUpdate(name,conventionId);
  }
  @GetMapping({"/uniqueEmailConventionUpdate"})
  @PreAuthorize("hasRole('ROLE_RH')")
  public Boolean uniqueEmail(@RequestParam("email") String email,@RequestParam(value = "conventionId", required = false,defaultValue = "") Long conventionId) {
    return conventionService.isEmailUniqueUpdate(email,conventionId);
  }
  @GetMapping({"/uniqueName"})
  @PreAuthorize("hasRole('ROLE_RH')")
  public Boolean uniqueNameUpdate(@RequestParam("name") String name) {
    return conventionService.isNomUnique(name);
  }
  @GetMapping({"/uniqueEmailConvention"})
  @PreAuthorize("hasRole('ROLE_RH')")
  public Boolean uniqueEmailUpdate(@RequestParam("email") String email) {
    return conventionService.isEmailUnique(email);
  }
}
