package sbcallmanagement.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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



















//import sbcallmanagement.entity.Booking;
//import sbcallmanagement.entity.User;
//import sbcallmanagement.service.BookingService;
//import sbcallmanagement.service.UserService;
//import sbcallmanagement.util.JwtUtil;

//@RestController
//@RequestMapping("/user")
//public class UserController {
//    private final UserService userService;
//    private final BookingService bookingService;
//    private final JwtUtil jwtUtil;
//    
//	/*
//	 * public UserController(UserService userService, JwtUtil jwtUtil) {
//	 * this.userService = userService; this.jwtUtil = jwtUtil; }
//	 */
//
//    @Autowired
//    public UserController(UserService userService, BookingService bookingService, JwtUtil jwtUtil) {
//        this.userService = userService;
//        this.bookingService = bookingService;
//		this.jwtUtil = jwtUtil;
//    }
//
//	/*
//	 * @PostMapping("/register") public ResponseEntity<Long>
//	 * registerUser(@RequestBody User user) { Long registeredUser =
//	 * userService.registerUser(user); // Generate JWT token String jwtToken =
//	 * jwtUtil.generateToken(registeredUser); // Generate JWT token based on
//	 * registeredUser details registeredUser.setJwtToken(jwtToken); return
//	 * ResponseEntity.ok(registeredUser); }
//	 */
//    
//    @PostMapping("/register")
//    public ResponseEntity<JwtResponse> registerUser(@RequestBody User user) {
//        User registeredUser = userService.registerUser(userRequest.getName(), userRequest.getEmail(), userRequest.getPassword());
//
//        // Generate JWT token for the registered user
//        String token = jwtUtil.generateToken(registeredUser.getId());
//
//        JwtResponse response = new JwtResponse(token);
//        return ResponseEntity.status(HttpStatus.CREATED).body(response);
//    }
//    
//    
//
//    @PostMapping("/login")
//    public ResponseEntity<User> loginUser(@RequestBody User user) {
//        Optional<User> loggedInUser = userService.loginUser(user.getEmail(), user.getPassword());
//        if (loggedInUser.isPresent()) {
//            // Generate JWT token
//            String jwtToken =jwtUtil.generateToken(loggedInUser.get()); // Generate JWT token based on loggedInUser details
//            loggedInUser.get().setJwtToken(jwtToken);
//            return ResponseEntity.ok(loggedInUser.get());
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//    }
//
//    @PostMapping("/{userId}/advisor/{advisorId}/booking")
//    public ResponseEntity<Booking> bookCall(@PathVariable Long userId, @PathVariable Long advisorId, @RequestBody Booking booking) {
//        Booking bookedCall = bookingService.bookCall(userId, advisorId, booking);
//        return ResponseEntity.ok(bookedCall);
//    }
//
//    @GetMapping("/{userId}/booked-calls")
//    public ResponseEntity<List<Booking>> getBookedCalls(@PathVariable Long userId) {
//        List<Booking> bookedCalls = bookingService.getBookedCallsByUserId(userId);
//        return ResponseEntity.ok(bookedCalls);
//    }
//}
