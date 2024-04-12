package com.example.eco_market.Services.Impls;

import com.example.eco_market.Models.User;
import com.example.eco_market.Models.Role;
import com.example.eco_market.Repositories.RoleRepository;
import com.example.eco_market.Repositories.UserRepository;
import com.example.eco_market.Services.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {
    @PersistenceContext
    private EntityManager em;
    @Autowired

    UserRepository userRepository;
    @Autowired

    PasswordEncoder encoder;

    @Autowired
    RoleRepository roleRepository;

//    @Autowired
//    private JavaMailSender mailSender;

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findUserByUsername((String) authentication.getPrincipal());
    }

    @Override
    public List<User> allUsers() {
        return userRepository.findAll();
    }

    @Override
    public void saveUser(User user) {
        if(user.getEmail().contains("admin")){
            user.getRoles().add(roleRepository.findByName("ROLE_ADMIN"));
        } else {
            user.getRoles().add(roleRepository.findByName("ROLE_USER"));
        }
        user.setPassword(encoder.encode(user.getPassword()));
        String token = UUID.randomUUID().toString();
        user.setVerificationToken(token);
//        String subject = "Подтверждение регистрации";
//        String verificationUrl = "http://localhost:8080/verify?token=" + user.getVerificationToken();
//        String message = "Для завершения регистрации перейдите по ссылке: " + verificationUrl;
//        SimpleMailMessage email = new SimpleMailMessage();
//        email.setTo(user.getEmail());
//        email.setSubject(subject);
//        email.setText(message);
//
//        mailSender.send(email);

        userRepository.save(user);
    }

    @Override
    public boolean deleteUser(Long userId) {
        if (userRepository.findById(userId).isPresent()){
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }

    @Override
    public List<User> userGetList(Long idMin) {
        return em.createQuery("SELECT u FROM User u WHERE u.id > :paramId", User.class)
                .setParameter("paramId", idMin).getResultList();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }
}
