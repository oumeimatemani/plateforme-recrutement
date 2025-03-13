package tn.esprit.spring.project.controllers;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import tn.esprit.spring.project.models.ERole;
import tn.esprit.spring.project.models.Role;
import tn.esprit.spring.project.models.StatusUser;
import tn.esprit.spring.project.models.User;
import tn.esprit.spring.project.repository.RoleRepository;
import tn.esprit.spring.project.repository.UserRepository;
import tn.esprit.spring.project.security.jwt.JwtUtils;
import tn.esprit.spring.project.security.services.UserDetailsImpl;
import tn.esprit.spring.project.service.IUserService;
import tn.esprit.spring.project.payload.request.LoginRequest;
import tn.esprit.spring.project.payload.request.SignupRequest;
import tn.esprit.spring.project.payload.request.UpdateUserRequest;
import tn.esprit.spring.project.payload.response.JwtResponse;
import tn.esprit.spring.project.payload.response.MessageResponse;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  IUserService iUserService;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  // LOGIN : AUTHENTIFICATION D'UN UTILISATEUR
  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
      System.out.println("Tentative de connexion : " + loginRequest.getUsername());

      Authentication authentication = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

      SecurityContextHolder.getContext().setAuthentication(authentication);
      String jwt = jwtUtils.generateJwtToken(authentication);
      
      UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();    
      List<String> roles = userDetails.getAuthorities().stream()
          .map(item -> item.getAuthority())
          .collect(Collectors.toList());

      System.out.println("Connexion réussie : " + loginRequest.getUsername());

      return ResponseEntity.ok(new JwtResponse(jwt, 
                           userDetails.getId(), 
                           userDetails.getUsername(), 
                           userDetails.getEmail(), 
                           roles));
  }

  // INSCRIPTION D'UN NOUVEL UTILISATEUR
  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
      System.out.println("Inscription en cours : " + signUpRequest.getUsername());

      if (userRepository.existsByUsername(signUpRequest.getUsername())) {
          return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
      }

      if (userRepository.existsByEmail(signUpRequest.getEmail())) {
          return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
      }
    // Création d'un nouvel utilisateur
    System.out.println("Création d'un nouvel utilisateur...");
      User user = new User(
              signUpRequest.getUsername(),
              signUpRequest.getEmail(),
              encoder.encode(signUpRequest.getPassword())
      );

      if (signUpRequest.getPhoneNumber() != null) { 
        user.setPhoneNumber(signUpRequest.getPhoneNumber()); 
    }

     // Gestion des rôles
      Set<String> strRoles = signUpRequest.getRole() != null ? signUpRequest.getRole() : new HashSet<>();
      Set<Role> roles = new HashSet<>();

      if (strRoles.isEmpty()) {
          Role userRole = roleRepository.findByName(ERole.CANDIDAT)
                  .orElseThrow(() -> new RuntimeException("Error: Default role CANDIDAT not found."));
          roles.add(userRole);
      } else {
          for (String role : strRoles) {
              try {
                  ERole eRole = ERole.valueOf(role);
                  Role foundRole = roleRepository.findByName(eRole)
                          .orElseThrow(() -> new RuntimeException("Error: Role " + role + " is not found."));
                  roles.add(foundRole);
              } catch (IllegalArgumentException e) {
                  return ResponseEntity.badRequest().body(new MessageResponse("Error: Role " + role + " is not valid!"));
              }
          }
      }

      user.setStatus(StatusUser.ACTIVE); 
      user.setRoles(roles);
      userRepository.save(user);

      System.out.println("Utilisateur enregistré avec succès !");
      return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }

  // LISTE DES UTILISATEURS
  @GetMapping("/AllUsers")
  public List<User> gettingAllUsers() {
     return iUserService.getAllUsers();
  }

  // RÉCUPÉRER UN UTILISATEUR PAR ID
  @GetMapping("/getUser/{id}")
  public ResponseEntity<?> getUserById(@PathVariable Long id) {
      System.out.println("Requête reçue pour récupérer l'utilisateur avec ID : " + id);
      
      User user = iUserService.getUserById(id);
      return ResponseEntity.ok(user);
  }

  // MISE À JOUR DU RÔLE D'UN UTILISATEUR
  @PutMapping("/updateRole/{id}")
  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  public ResponseEntity<String> updateUserRole(@PathVariable Long id, @RequestParam String newRole) {
      ERole roleEnum;
      try {
          roleEnum = ERole.valueOf(newRole);
      } catch (IllegalArgumentException e) {
          return ResponseEntity.badRequest().body("Erreur: Rôle invalide !");
      }
      
      iUserService.updateUserRole(id, roleEnum);
      return ResponseEntity.ok("Rôle mis à jour avec succès !");
  }

  // MISE À JOUR DES DÉTAILS D'UN UTILISATEUR (HORS RÔLE)
    @PutMapping("/updateUser/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or #id == principal.id") // Seul l'admin ou l'utilisateur lui-même peut modifier ses infos
    public ResponseEntity<?> updateUserDetails(@PathVariable Long id, @Valid @RequestBody UpdateUserRequest updateUserRequest) {
        System.out.println("Mise à jour des informations de l'utilisateur ID: " + id);
        
        User updatedUser = iUserService.updateUserDetails(id, updateUserRequest);
        
        return ResponseEntity.ok(updatedUser);
    }

  
  // SUPPRESSION D'UN UTILISATEUR
  @DeleteMapping("/deleteUser/{id}")
  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  public ResponseEntity<String> deleteUser(@PathVariable Long id, Principal principal) {
      System.out.println("Suppression de l'utilisateur ID : " + id);
      iUserService.deleteUser(id);
      return ResponseEntity.ok("Utilisateur supprimé avec succès !");
  }
}
