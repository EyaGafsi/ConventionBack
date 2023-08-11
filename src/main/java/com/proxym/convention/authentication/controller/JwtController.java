package com.proxym.convention.authentication.controller;

import com.proxym.convention.authentication.entities.JwtRequest;
import com.proxym.convention.authentication.entities.JwtResponse;
import com.proxym.convention.authentication.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class JwtController {
  @Autowired
  private JwtService jwtService;

  @PostMapping({"/authenticate"})
  public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
      return jwtService.createJwtToken(jwtRequest);

  }
}
