package com.proxym.convention.user.dto;

import com.proxym.convention.user.entities.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class UserDtoMapper implements Function<User, UserDTO> {

  @Override
  public UserDTO apply(User user) {
    return new UserDTO(
      user.getUserId(),
      user.getUserName(),
      user.getFirstName(),
      user.getLastName(),
      user.getPhone(),
      user.getEmail(),
      user.getPassword(),
      user.getDateAjout(),
      user.getRoles(),
      user.getDemandes()
    );
  }

  public User toUserEntity(UserDTO userDto) {

    return new User(
      userDto.getUserName(),
      userDto.getFirstName(),
      userDto.getLastName(),
      userDto.getPhone(),
      userDto.getEmail(),
      userDto.getPassword(),
      userDto.getRoles(),
      userDto.getDemandes()
    );
  }


}
