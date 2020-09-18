package walking.footwear.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import walking.footwear.dao.RoleRepository;
import walking.footwear.dao.UserRepository;
import walking.footwear.model.ERole;
import walking.footwear.model.Role;
import walking.footwear.model.User;

import java.util.Arrays;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Long countByEmail(String email) {
        return userRepository.countByEmail(email);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).get();
    }

    public void saveCustomer(User user) {
        user.setRoles(Arrays.asList(roleRepository.findByRole(ERole.CUSTOMER).get())
                .stream()
                .collect(Collectors.toSet()));
//        user.setEnabled(true);
        userRepository.save(user);
    }

    public void saveAdmin(User user) {
        Role admin=roleRepository.findByRole(ERole.ADMIN).get();
//        user.setRoles(Arrays.asList(roleRepository.findByRole(ERole.ADMIN).get())
//                .stream()
//                .collect(Collectors.toSet()));
//        user.setEnabled(true);
        user.addRole(admin);
        userRepository.save(user);
    }

    // returns currently logged in user
    public User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        return userRepository.findByUsername(currentUserName).get();
    }


    public String encode(String password) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    public boolean isAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getAuthorities()
                .stream()
                .anyMatch(r -> r.getAuthority().equals("Admin"));
    }

    public boolean isCustomer() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getAuthorities()
                .stream()
                .anyMatch(r -> r.getAuthority().equals("Customer"));
    }
}
