package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

@Service
@Transactional(readOnly = true)
public class AdminService {
    private UserRepository userRepository;
    private UserService userService;
    private RoleRepository roleRepository;

    @Autowired
    public AdminService(UserRepository userRepository,
                        UserService userService,
                        RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @Transactional
    public void save(User user) {
        User userFromDB = userRepository.findByUsername(user.getUsername());
        if (userFromDB == null) {
            user.setPassword(userService.BCryptPassword().encode(user.getPassword()));
            Role role = new Role("ROLE_ADMIN");
            user.addRoleToUser(role);
            userRepository.save(user);
            roleRepository.save(role);
        }
    }
}
