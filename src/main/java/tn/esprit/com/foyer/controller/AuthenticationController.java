package tn.esprit.com.foyer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.com.foyer.requests.AuthenticationRequest;
import tn.esprit.com.foyer.requests.RegisterRequest;
import tn.esprit.com.foyer.services.AuthenticationService;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService service;

  @PostMapping("/register")
  public ResponseEntity register(@RequestBody RegisterRequest request) {
    return service.register(request);
  }

  @PostMapping("/authenticate")
  public ResponseEntity authenticate(
      @RequestBody AuthenticationRequest request
  ) {
    return service.authenticate(request);
  }




}
