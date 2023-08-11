package com.proxym.convention.role.dao;


import com.proxym.convention.role.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface RoleRepository extends JpaRepository<Role, Long> {
  @Query(" select u from Role u where u.roleName = ?1")
  Role findByRoleName(String nom);
}
