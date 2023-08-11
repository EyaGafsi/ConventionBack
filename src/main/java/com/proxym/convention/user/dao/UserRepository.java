package com.proxym.convention.user.dao;

import com.proxym.convention.user.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository  extends JpaRepository<User, Long> {
    @Query(" select u from User u where u.userName = ?1")
    Optional<User> findUserWithName(String username);
  Page<User> findByUserName(String username, Pageable pageable) ;
  @Query("SELECT u FROM User u ORDER BY CASE WHEN :sortOrder = 'asc' THEN u.userName END ASC, CASE WHEN :sortOrder = 'desc' THEN u.userName END DESC")
  Page<User> sortByUserName(Pageable pageable, @Param("sortOrder") String sortOrder);

  @Query("SELECT u FROM User u ORDER BY CASE WHEN :sortOrder = 'asc' THEN u.firstName END ASC, CASE WHEN :sortOrder = 'desc' THEN u.firstName END DESC")
  Page<User> sortByFirstName(Pageable pageable, @Param("sortOrder") String sortOrder);

  @Query("SELECT u FROM User u ORDER BY CASE WHEN :sortOrder = 'asc' THEN u.lastName END ASC, CASE WHEN :sortOrder = 'desc' THEN u.lastName END DESC")
  Page<User> sortByLastName(Pageable pageable, @Param("sortOrder") String sortOrder);

  @Query("SELECT u FROM User u ORDER BY CASE WHEN :sortOrder = 'asc' THEN u.email END ASC, CASE WHEN :sortOrder = 'desc' THEN u.email END DESC")
  Page<User> sortByEmail(Pageable pageable, @Param("sortOrder") String sortOrder);

  User findByUserName(String userName);
  User findByEmail(String email);
}
