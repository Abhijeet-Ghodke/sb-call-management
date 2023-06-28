package sbcallmanagement.service;

import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import sbcallmanagement.entity.Advisor;
import sbcallmanagement.entity.Booking;
import sbcallmanagement.entity.User;
import sbcallmanagement.repository.AdvisorRepository;
import sbcallmanagement.repository.BookingRepository;
import sbcallmanagement.repository.UserRepository;


@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdvisorRepository advisorRepository;

    public ResponseEntity<String> bookCall(Long userId, Long advisorId, String bookingTimeStr) {
        
        LocalDateTime bookingTime = LocalDateTime.parse(bookingTimeStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        
        Optional<User> optionalUser = userRepository.findById(userId);
        Optional<Advisor> optionalAdvisor = advisorRepository.findById(advisorId);

        
        if (optionalUser.isEmpty() || optionalAdvisor.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or advisor not found");
        }

        User user = optionalUser.get();
        Advisor advisor = optionalAdvisor.get();

        
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setAdvisor(advisor);
        booking.setBookingTime(bookingTime);

        
        bookingRepository.save(booking);

        
        return ResponseEntity.ok("Booking successfully created");
    }
}
