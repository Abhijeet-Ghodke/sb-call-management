package sbcallmanagement.service;

import java.awt.desktop.UserSessionListener;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


import org.springframework.stereotype.Service;

import sbcallmanagement.entity.User;
import sbcallmanagement.repository.UserRepository;
import sbcallmanagement.util.JwtUtil;

@Service
public class UserService  {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private PasswordEncoder passwordEncoder;


    //@Autowired
    //private BCryptPasswordEncoder passwordEncoder;

    public ResponseEntity<String> registerUser(String name, String email, String password) {
        // Check if user already exists with the provided email
        if (userRepository.findByEmail(email) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User with this email already exists.");
        }

        // Create a new user entity
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);

        // Encrypt the password using BCrypt
        //String encryptedPassword = passwordEncoder.encode(password);
        //user.setPassword(encryptedPassword);

        // Save the user to the database
        userRepository.save(user);

        // Generate JWT token with user ID
        String token = jwtUtil.generateToken(user.getId().toString());

        // Return the token with a success response
        return ResponseEntity.ok(token);
    }
    
    public ResponseEntity<String> loginUser(String email, String password) {
        // Find the user by email
        User user = userRepository.findByEmail(email);

        // Check if the user exists
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }

        // Check if the password is correct
        if (!passwordEncoder.matches(password, user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }

        // Generate JWT token with user ID
        String token = jwtUtil.generateToken(user.getId().toString());

        // Return the token with a success response
        return ResponseEntity.ok(token);
    }

}









//
//import sbcallmanagement.entity.User;
//import sbcallmanagement.repository.UserRepository;
//
//@Service
//public class UserService {
//    private final UserRepository userRepository;
//    
//   
//
//    public UserService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    public User registerUser(User user) {
//        return userRepository.save(user);
//    }
//
//    public Optional<User> loginUser(String email, String password) {
//        Optional<User> existingUser = userRepository.findByEmail(email);
//        if (existingUser.isPresent() && existingUser.get().getPassword().equals(password)) {
//            return existingUser;
//        } else {
//            return Optional.empty();
//        }
//    }
//}
//
