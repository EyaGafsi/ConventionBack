package com.proxym.convention.convention.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proxym.convention.convention.entities.Convention;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Getter
@Setter
public class AvantageDTO {
  private Long avantageId;
  @NotNull
  @Size(max = 100)
  private String details;
  @JsonIgnore
  @ManyToOne
  private Convention convention;

  public AvantageDTO() {
  }

  public AvantageDTO(String details, Convention convention) {
    this.details = details;
    this.convention = convention;
  }

}
