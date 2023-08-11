package com.proxym.convention.user.service;

import com.proxym.convention.user.dto.UserDTO;
import com.proxym.convention.user.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
  UserDTO registerNewUser(UserDTO user);
  Page<UserDTO> getAllUser(String username, Pageable pageable, String sortBy, String sortOrder);
  UserDTO save(UserDTO user);
  void deleteUser(Long id);
  UserDTO findUsername(String username);
  String getEncodedPassword(String password);
   boolean isEmailUnique(String email) ;
   boolean isUserNameUnique(String userName);
  }
