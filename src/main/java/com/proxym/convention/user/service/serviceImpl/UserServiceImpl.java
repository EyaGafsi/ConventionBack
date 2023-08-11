package com.proxym.convention.user.service.serviceImpl;

import com.proxym.convention.role.dao.RoleRepository;
import com.proxym.convention.user.dao.UserRepository;
import com.proxym.convention.role.entities.Role;
import com.proxym.convention.user.dto.UserDTO;
import com.proxym.convention.user.dto.UserDtoMapper;
import com.proxym.convention.user.entities.User;
import com.proxym.convention.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
  @Autowired
  private UserRepository userDao;
  @Autowired
  private RoleRepository roleDao;
  @Autowired
  private PasswordEncoder passwordEncoder;
  private final UserDtoMapper userDtoMapper;

  public UserServiceImpl(UserDtoMapper userDtoMapper) {
    this.userDtoMapper = userDtoMapper;
  }


    @Override
    public UserDTO registerNewUser(UserDTO userDto) {
      Role role = roleDao.findById(2L).orElse(null);
      User user =userDtoMapper.toUserEntity(userDto);
      Set<Role> roles=new HashSet<>();
      roles.add(role);
      user.setRoles(roles);
      user.setDateAjout();
      user = userDao.save(user);
      return userDtoMapper.apply(user);
    }
  @Override
  public String getEncodedPassword(String password){
    return passwordEncoder.encode(password);
  }
  @Override
  public Page<UserDTO> getAllUser(String username, Pageable pageable, String sortBy, String sortOrder) {
    if (username.isEmpty()) {
      if (sortBy != null) {
        switch (sortBy.toLowerCase()) {
          case "username":
            return userDao.sortByUserName(pageable, sortOrder).map(userDtoMapper::apply);
          case "nom":
            return userDao.sortByLastName(pageable, sortOrder).map(userDtoMapper::apply);
          case "prenom":
            return userDao.sortByFirstName(pageable, sortOrder).map(userDtoMapper::apply);
          case "email":
            return userDao.sortByEmail(pageable, sortOrder).map(userDtoMapper::apply);
          default:
            return userDao.sortByUserName(pageable, sortOrder).map(userDtoMapper::apply);
        }
      } else {
        return userDao.findAll(pageable).map(userDtoMapper::apply);
      }
    } else {
      return userDao.findByUserName(username, pageable).map(userDtoMapper::apply);
    }
  }

  @Override
  public UserDTO save(UserDTO userdto) {
    if (userdto.getUserId() == null) {
      User user = userDtoMapper.toUserEntity(userdto);
      userDao.save(user);
      return userDtoMapper.apply(user);
    } else {
       User user = userDao.findById(userdto.getUserId()).get();
        user.setRoles(userdto.getRoles());

        userDao.save(user);
        return userDtoMapper.apply(user);


    }
  }
  @Override
  public void deleteUser(Long id) {
    userDao.deleteById(id);
  }
    @Override
    public UserDTO findUsername(String username) {
      Optional<User> userOptional = userDao.findUserWithName(username);
      return userOptional.map(userDtoMapper::apply).orElse(null);
    }
  @Override
  public boolean isUserNameUnique(String userName) {
    User existingUser = userDao.findByUserName(userName);
    return existingUser == null;
  }
  @Override
  public boolean isEmailUnique(String email) {
    User existingUser = userDao.findByEmail(email);
return existingUser==null;
}
}
