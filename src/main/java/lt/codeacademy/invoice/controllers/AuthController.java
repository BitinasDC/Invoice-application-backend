package lt.codeacademy.invoice.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lt.codeacademy.invoice.entities.ERole;
import lt.codeacademy.invoice.entities.Role;
import lt.codeacademy.invoice.entities.User;
import lt.codeacademy.invoice.repositories.RoleRepository;
import lt.codeacademy.invoice.repositories.UserRepository;
import lt.codeacademy.invoice.request.LoginRequest;
import lt.codeacademy.invoice.request.RecoveryRequest;
import lt.codeacademy.invoice.request.SignupRequest;
import lt.codeacademy.invoice.response.JwtResponse;
import lt.codeacademy.invoice.response.MessageResponse;

import lt.codeacademy.invoice.security.jwt.JwtUtils;
import lt.codeacademy.invoice.security.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);
    
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();    
    List<String> roles = userDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());

    return ResponseEntity.ok(new JwtResponse(jwt, 
                         userDetails.getId(), 
                         userDetails.getUsername(), 
                         userDetails.getEmail(), 
                         roles));
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Username is already taken!"));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Email is already in use!"));
    }

    // Create new user's account
    User user = new User(signUpRequest.getUsername(), 
    		signUpRequest.getEmail(),
            encoder.encode(signUpRequest.getPassword()));

    Role userRole = roleRepository.findByName(ERole.ROLE_USER)
            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
    user.setRoles(userRole);
    userRepository.save(user);

    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }
  
  //************ TODO uzbaigti password resset'a
  // https://www.codejava.net/frameworks/spring-boot/spring-security-forgot-password-tutorial
  // https://www.baeldung.com/spring-security-registration-i-forgot-my-password
  
  @PostMapping("/recover")
  public ResponseEntity<?> recoverPassword(@Valid @RequestBody RecoveryRequest recoveryRequest) {
      if (userRepository.existsByEmail(recoveryRequest.getEmail())) {
         User user = userRepository.findByEmail(recoveryRequest.getEmail());

         user.setPassword("aha"); ////////////////////////// passwordas

      return ResponseEntity.ok(new MessageResponse("Recovery mail successfully sent to " + recoveryRequest.getEmail()));
  }
      return ResponseEntity
              .badRequest()
              .body(new MessageResponse("Error: Email not found!"));
}
}