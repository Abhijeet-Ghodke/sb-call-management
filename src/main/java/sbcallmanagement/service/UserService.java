package sbcallmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

     public ResponseEntity<String> registerUser(String name, String email, String password) {
        
        if (userRepository.findByEmail(email) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User with this email already exists.");
        }

        
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);

        userRepository.save(user);

        
        String token = jwtUtil.generateToken(user.getId().toString());

        
        return ResponseEntity.ok(token);
    }
    
    public ResponseEntity<String> loginUser(String email, String password) {
        
        User user = userRepository.findByEmail(email);

        
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }

        
        if (!passwordEncoder.matches(password, user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }

        
        String token = jwtUtil.generateToken(user.getId().toString());

        
        return ResponseEntity.ok(token);
    }

}
