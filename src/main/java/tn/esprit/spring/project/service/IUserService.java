package tn.esprit.spring.project.service;

import tn.esprit.spring.project.models.ERole;
import tn.esprit.spring.project.models.User;
import tn.esprit.spring.project.payload.request.UpdateUserRequest;

import java.util.*;


public interface IUserService {
    public List<User> getAllUsers();
    User getUserById(Long id);
    public void updateUser(User user);
    User updateUserDetails(Long id, UpdateUserRequest updateUserRequest);
    public User updateUserRole(Long userId, ERole newRole);
    void deleteUser(Long id);


}
