package com.proxym.convention.user.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proxym.convention.demande.entities.Demande;
import com.proxym.convention.role.entities.Role;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
  @Column(unique = true)
  private String userName;

    private String firstName;

    private String lastName;

    private String phone;
  @Column(unique = true)
  private String email;
    private String password;
    private LocalDate dateAjout;
  @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
  @JsonIgnore
  @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
  private List<Demande> demandes;
  public User() {
  }

  public User(String userName, String firstName, String lastName, String phone, String email, String password, Set<Role> roles,List<Demande> demandes) {
    this.userName = userName;
    this.firstName = firstName;
    this.lastName = lastName;
    this.phone = phone;
    this.email = email;
    this.password = password;
    this.roles = roles;
    this.demandes=demandes;
    this.dateAjout = LocalDate.now();
  }

  public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setDateAjout(LocalDate dateAjout) {
    this.dateAjout = dateAjout;
  }

  public List<Demande> getDemandes() {
    return demandes;
  }

  public void setDemandes(List<Demande> demandes) {
    this.demandes = demandes;
  }

  public LocalDate getDateAjout() {
    return dateAjout;
  }

  public void setDateAjout() {
    this.dateAjout = LocalDate.now();
  }
}
