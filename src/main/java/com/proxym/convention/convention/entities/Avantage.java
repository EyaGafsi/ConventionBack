package com.proxym.convention.convention.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Getter
@Setter
@Entity
public class Avantage {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long avantageId;

  private String details;
  @JsonIgnore
  @ManyToOne
  private Convention convention;

  public Avantage() {
  }

  public Avantage(String details, Convention convention) {
    this.details = details;
    this.convention = convention;
  }

}
