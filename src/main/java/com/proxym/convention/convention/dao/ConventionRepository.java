package com.proxym.convention.convention.dao;

import com.proxym.convention.convention.entities.Convention;
import com.proxym.convention.user.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ConventionRepository extends JpaRepository<Convention, Long> {
  Page<Convention> findByNomConvention(String nom, Pageable pageable);
  Convention findByNomConvention(String nom);

  @Query("SELECT c FROM Convention c ORDER BY CASE WHEN :sortOrder = 'asc' THEN c.nomConvention END ASC, CASE WHEN :sortOrder = 'desc' THEN c.nomConvention END DESC")
  Page<Convention> sortByNom(Pageable pageable, @Param("sortOrder") String sortOrder);


  Page<Convention> findByTypeConvention(Pageable pageable, String sortBy);


  @Query("SELECT c FROM Convention c ORDER BY CASE WHEN :sortOrder = 'asc' THEN c.email END ASC, CASE WHEN :sortOrder = 'desc' THEN c.email END DESC")
  Page<Convention> sortByEmail(Pageable pageable, @Param("sortOrder") String sortOrder);
  Convention findByEmail(String email);

}
