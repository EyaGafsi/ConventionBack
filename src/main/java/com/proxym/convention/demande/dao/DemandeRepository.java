package com.proxym.convention.demande.dao;

import com.proxym.convention.demande.entities.Demande;
import com.proxym.convention.user.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DemandeRepository extends JpaRepository<Demande, Long> {

  Page<Demande> findByStatus(String status, Pageable pageable);
  Page<Demande> findByNomDemandeAndStatus(String nom,String status, Pageable pageable) ;
  @Query("SELECT d FROM Demande d WHERE d.status='en attente' ORDER BY CASE WHEN :sortOrder = 'asc' THEN d.nomDemande END ASC, CASE WHEN :sortOrder = 'desc' THEN d.nomDemande END DESC")
  Page<Demande> sortByNomDemande(Pageable pageable, @Param("sortOrder") String sortOrder);

  Page<Demande> findByTypeDemandeAndStatus(Pageable pageable,String sortBy,String status);

  @Query("SELECT d FROM Demande d WHERE d.status='en attente' ORDER BY CASE WHEN :sortOrder = 'asc' THEN d.dateEnvoi END ASC, CASE WHEN :sortOrder = 'desc' THEN d.dateEnvoi END DESC")
  Page<Demande> sortByDate(Pageable pageable, @Param("sortOrder") String sortOrder);

  @Query("SELECT d FROM Demande d WHERE d.status='en attente' ORDER BY CASE WHEN :sortOrder = 'asc' THEN d.emailDemande END ASC, CASE WHEN :sortOrder = 'desc' THEN d.emailDemande END DESC")
  Page<Demande> sortByEmail(Pageable pageable, @Param("sortOrder") String sortOrder);
  Demande findByNomDemande(String nom);
  Demande findByEmailDemande(String email);
  Page<Demande> findByUser(User user, Pageable pageable);
  Page<Demande> findByTypeDemandeAndUser(Pageable pageable,String sortBy,User user);
  Page<Demande> findByNomDemandeAndUser(String nom,User user, Pageable pageable) ;

  @Query("SELECT d FROM Demande d WHERE d.user = :user ORDER BY CASE WHEN :sortOrder = 'asc' THEN d.nomDemande END ASC, CASE WHEN :sortOrder = 'desc' THEN d.nomDemande END DESC")
  Page<Demande> sortByNomDemande(Pageable pageable, @Param("user") User user, @Param("sortOrder") String sortOrder);

  @Query("SELECT d FROM Demande d WHERE d.user = :user ORDER BY CASE WHEN :sortOrder = 'asc' THEN d.dateEnvoi END ASC, CASE WHEN :sortOrder = 'desc' THEN d.dateEnvoi END DESC")
  Page<Demande> sortByDate(Pageable pageable, @Param("user") User user, @Param("sortOrder") String sortOrder);

  @Query("SELECT d FROM Demande d WHERE d.user = :user ORDER BY CASE WHEN :sortOrder = 'asc' THEN d.emailDemande END ASC, CASE WHEN :sortOrder = 'desc' THEN d.emailDemande END DESC")
  Page<Demande> sortByEmail(Pageable pageable, @Param("user") User user, @Param("sortOrder") String sortOrder);

}
