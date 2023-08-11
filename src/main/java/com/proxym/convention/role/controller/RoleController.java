package com.proxym.convention.role.controller;

import com.proxym.convention.role.entities.Role;
import com.proxym.convention.user.dto.UserDTO;
import com.proxym.convention.user.entities.User;
import com.proxym.convention.role.service.RoleService;
import com.proxym.convention.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RoleController {

  @Autowired
  private RoleService roleService;
  @Autowired
  private UserService userService;

    @PostMapping({"/createNewRole"})
    public Role createNewRole(@RequestBody Role role) {
        return roleService.createNewRole(role);
    }
  @PostMapping({"/roleRh"})
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public UserDTO roleRh(@RequestBody UserDTO user) {
    user.getRoles().removeAll(user.getRoles());
    Role r = roleService.findByRoleName("ROLE_RH");
        user.getRoles().add(r);
     return userService.save(user);
  }
  @PostMapping({"/roleEmployee"})
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public UserDTO roleEmployee(@RequestBody UserDTO user) {
    user.getRoles().removeAll(user.getRoles());
    Role r1 = roleService.findByRoleName("ROLE_EMPLOYEE");
    user.getRoles().add(r1);
    return userService.save(user);
  }
}
