package sbcallmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sbcallmanagement.dto.AdvisorDTO;
import sbcallmanagement.service.AdvisorService;
import sbcallmanagement.service.BookingService;
import sbcallmanagement.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private AdvisorService advisorService;
    
    @Autowired
    private BookingService bookingService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestParam String name, @RequestParam String email, @RequestParam String password) {
        return userService.registerUser(name, email, password);
    }
    
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestParam String email, @RequestParam String password) {
        return userService.loginUser(email, password);
    }
    
    
    @GetMapping("/advisor")
    @RequestMapping("/{user-id}")
    public ResponseEntity<List<AdvisorDTO>> getAdvisorsByUserId(@PathVariable("user-id") Long userId) {
        return advisorService.getAdvisorsByUserId(userId);
    }
    
    
    
    @PostMapping
    @RequestMapping("/{user-id}/advisor/{advisor-id}")
    public ResponseEntity<String> bookCall(@PathVariable("user-id") Long userId,
                                           @PathVariable("advisor-id") Long advisorId,
                                           @RequestParam("booking-time") String bookingTime) {
        return bookingService.bookCall(userId, advisorId, bookingTime);
    }
    
    @RequestMapping("/{user-id}/advisor/booking")
    @GetMapping
    public ResponseEntity<List<AdvisorDTO>> getBookedCallsByUserId(@PathVariable("user-id") Long userId) {
        return advisorService.getBookedCallsByUserId(userId);
    }
}
