package ru.kata.spring.boot_security.demo.services;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class AdminService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;


    public AdminService(UserRepository userRepository,
                        RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Transactional
    public Role saveRole(Role role) {
        Role roleDB = roleRepository.findByName(role.getName());
        if (roleDB == null) {
            roleRepository.save(role);
        }
        return roleRepository.findByName(role.getName());
    }

    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public User findOne(Long id) {
        User user = null;
        Optional<User> foundUser = userRepository.findById(id);
        if (foundUser.orElse(null) != null) {
            user = foundUser.get();
            user.setPassword(null);
        }
        return user;
    }

    @Transactional
    public void update(User user) {
        if (user.getPassword().isEmpty()) {
            //ignore
        } else {
            user.setPassword(BCrypt().encode(user.getPassword()));
            user.addRoleToUser(saveRole(new Role("ROLE_USER")));
            userRepository.save(user);
        }
    }

    @Bean
    private BCryptPasswordEncoder BCrypt() {
        return new BCryptPasswordEncoder();
    }

    @Transactional
    public void saveAdmin(User user) {
        User userFromDB = userRepository.findByUsername(user.getUsername());
        if (userFromDB == null) {
            user.setPassword(BCrypt().encode(user.getPassword()));
            Role role = new Role("ROLE_ADMIN");
            user.addRoleToUser(role);
            userRepository.save(user);
            roleRepository.save(role);
        }
    }

}
