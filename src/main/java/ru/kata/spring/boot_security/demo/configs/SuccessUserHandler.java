package ru.kata.spring.boot_security.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.Set;

@Component
public class SuccessUserHandler implements AuthenticationSuccessHandler {
//    private final UserRepository userRepository;
//    @Autowired
//    public SuccessUserHandler(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    // Spring Security использует объект Authentication, пользователя авторизованной сессии.
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
//        Principal principal = (Principal) authentication.getPrincipal();
//        User user = userRepository.findByUsername(principal.getName());
        if (roles.contains("ROLE_USER")) {
            System.out.println("YES!");
            httpServletResponse.sendRedirect("/user");
        }
//        else if (user == null) {
//            httpServletResponse.sendRedirect("/registration");
//        }
        else {
            httpServletResponse.sendRedirect("/");
        }
    }
}