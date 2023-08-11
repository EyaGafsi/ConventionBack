package com.proxym.convention.user.controller;

import com.proxym.convention.user.dto.UserDTO;
import com.proxym.convention.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
public class UserController {

  @Autowired
  private UserService userService;

    @PostMapping({"/registerNewUser"})
    public UserDTO signup(@Valid @RequestBody UserDTO user, BindingResult bindingResult) {
      if (bindingResult.hasErrors()) {
        return user;
      }

   userService.registerNewUser(user);

      return user;
    }

  @DeleteMapping({"/supprimerUser"})
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public void supprimerUser(@RequestParam Long id) {
    userService.deleteUser(id);}
  @GetMapping({"/afficherUser"})
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public Page<UserDTO> afficherUser(@RequestParam("username") String username,@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "10") int size,@RequestParam("sortBy") String sortBy, @RequestParam(defaultValue = "asc") String sortOrder) {
    return userService.getAllUser(username,PageRequest.of(page, size),sortBy,sortOrder);
  }

  @GetMapping({"/uniqueEmail"})
  public Boolean uniqueEmail(@RequestParam("email") String email) {
    return userService.isEmailUnique(email);
  }
  @GetMapping({"/uniqueUserName"})
  public Boolean uniqueUserName(@RequestParam("userName") String userName) {
    return userService.isUserNameUnique(userName);
  }


}

