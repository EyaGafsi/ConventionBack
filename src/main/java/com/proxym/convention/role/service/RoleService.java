package com.proxym.convention.role.service;

import com.proxym.convention.role.entities.Role;

public interface RoleService {
  Role createNewRole(Role role);
  Role findByRoleName(String nom);
}
