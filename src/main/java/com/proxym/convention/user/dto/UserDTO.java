package com.proxym.convention.user.dto;

import com.proxym.convention.demande.entities.Demande;
import com.proxym.convention.role.entities.Role;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class UserDTO {

  private Long userId;
  @NotNull
  @Size(max = 50)
  private String userName;
  @NotNull
  @Size(max = 50)
  private String firstName;
  @NotNull
  @Size(max = 50)
  private String lastName;
  @NotNull
  @Size(min = 8,max = 8)
  private String phone;
  @NotNull
  @Size(max = 50)
  private String email;
  @NotNull
  private String password;
  private LocalDate dateAjout;
  private Set<Role> roles;
  private List<Demande> demandes;




  public UserDTO(Long userId, String userName, String firstName, String lastName, String phone, String email,
                 String password,LocalDate dateAjout, Set<Role> roles, List<Demande> demandes) {
    this.userId = userId;
    this.userName = userName;
    this.firstName = firstName;
    this.lastName = lastName;
    this.phone = phone;
    this.email = email;
    this.password = password;
    this.dateAjout=dateAjout;
    this.roles = roles;
    this.demandes=demandes;
  }
  public void setDateAjout() {
    this.dateAjout = LocalDate.now();
  }


}
