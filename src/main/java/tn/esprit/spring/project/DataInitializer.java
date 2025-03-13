package tn.esprit.spring.project;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import tn.esprit.spring.project.models.ERole;
import tn.esprit.spring.project.models.Role;
import tn.esprit.spring.project.repository.RoleRepository;

import java.util.Optional;

@Component
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    public DataInitializer(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        for (ERole role : ERole.values()) {
            Optional<Role> existingRole = roleRepository.findByName(role);
            if (existingRole.isEmpty()) {
                Role newRole = new Role(role);
                roleRepository.save(newRole);
                System.out.println("Rôle ajouté: " + role);
            }
        }
        System.out.println(" Tous les rôles sont prêts !");
    }
}
