package tn.esprit.spring.project.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import tn.esprit.spring.project.models.ERole;
import tn.esprit.spring.project.models.Role;
import tn.esprit.spring.project.models.StatusUser;
import tn.esprit.spring.project.models.User;
import tn.esprit.spring.project.payload.request.UpdateUserRequest;
import tn.esprit.spring.project.repository.RoleRepository;
import tn.esprit.spring.project.repository.UserRepository;


@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder encoder;


    //get All User
    public List<User> getAllUsers(){return userRepository.findAll();}

    //get User By Id
    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur avec l'ID " + id + " non trouvé !"));
    }

    //total of User
    public long getTotalUsersCount() {
        return userRepository.count();
    }


    //update role of user 
    @Override
    public User updateUserRole(Long userId, ERole newRole) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé !"));

        Role role = roleRepository.findByName(newRole)
                .orElseThrow(() -> new RuntimeException("Rôle non trouvé !"));

        user.getRoles().clear();
        user.getRoles().add(role);
        return userRepository.save(user);
    }

    //Update User
    public void updateUser(User user){
        userRepository.save(user);
    }

    //Delete User
    @Override
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));
        
        user.setStatus(StatusUser.INACTIVE);  // Mettre à jour le statut de l'utilisateur
        userRepository.save(user);
    }
    

    @Override
    public User updateUserDetails(Long userId, UpdateUserRequest updateUserRequest) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé !"));

        // Vérifier si l'email ou le username existent déjà pour un autre utilisateur
        if (userRepository.existsByUsername(updateUserRequest.getUsername()) && !user.getUsername().equals(updateUserRequest.getUsername())) {
            throw new RuntimeException("Erreur : Ce nom d'utilisateur est déjà utilisé !");
        }
        
        if (userRepository.existsByEmail(updateUserRequest.getEmail()) && !user.getEmail().equals(updateUserRequest.getEmail())) {
            throw new RuntimeException("Erreur : Cet email est déjà utilisé !");
        }

        // Mettre à jour les informations
        user.setUsername(updateUserRequest.getUsername());
        user.setEmail(updateUserRequest.getEmail());
        
        if (updateUserRequest.getPassword() != null && !updateUserRequest.getPassword().isEmpty()) {
            user.setPassword(encoder.encode(updateUserRequest.getPassword())); // Encoder le nouveau mot de passe
        }

        if (updateUserRequest.getPhoneNumber() != null) {
            user.setPhoneNumber(updateUserRequest.getPhoneNumber());
        }

        return userRepository.save(user);
    }


  
}
