package tn.esprit.spring.project.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {

   //for public access 
  @GetMapping("/all")
  public String allAccess() {
    return "Public Content.";
  }


@GetMapping("/user")
@PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('RH') or hasRole('MANAGER') or hasRole('EXPERT_TECHNIQUE') or hasRole('CANDIDAT')")
public String userAccess() {
  return "User Content.";
}

@GetMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public String adminAccess() {
  return "Admin Board.";
}

@GetMapping("/rh")
@PreAuthorize("hasRole('RH')")
public String rhAccess() {
  return "RH Board.";
}

@GetMapping("/manager")
@PreAuthorize("hasRole('MANAGER')")
public String managerAccess() {
  return "Manager Board.";
}

@GetMapping("/expert-technique")
@PreAuthorize("hasRole('EXPERT_TECHNIQUE')")
public String expertTechniqueAccess() {
  return "Expert Technique Board.";
}

@GetMapping("/candidat")
@PreAuthorize("hasRole('CANDIDAT')")
public String candidatAccess() {
  return "Candidat Board.";
}

}