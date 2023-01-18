package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;


import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;


    @Autowired
    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }



    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findOne(Long id) {
        Optional<User> foundUser = userRepository.findById(id);

        return foundUser.orElse(null);
    }

    @Transactional
    public boolean save(User user) {
        User userFromDB = userRepository.findByUsername(user.getUsername());
        if (userFromDB != null) {
            return false;
        }
        user.setPassword(BCryptPassword().encode(user.getPassword()));
        Role role = new Role("ROLE_USER");
        user.addRoleToUser(role);
        userRepository.save(user);
        roleRepository.save(role);
        return true;
    }

    @Transactional
    public void update(User updateUser) {
        //updateUser.setId(id);
        userRepository.save(updateUser);
    }

    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException(String.format("Ползователь с именем '%s' не найден", username));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
    }

    @Bean
    protected BCryptPasswordEncoder BCryptPassword() {
        return new BCryptPasswordEncoder();
    }
}
