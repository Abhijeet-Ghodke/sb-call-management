package sbcallmanagement.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
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
        // Parse the booking time string into a LocalDateTime object
        LocalDateTime bookingTime = LocalDateTime.parse(bookingTimeStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        // Find the user and advisor from their respective repositories
        Optional<User> optionalUser = userRepository.findById(userId);
        Optional<Advisor> optionalAdvisor = advisorRepository.findById(advisorId);

        // Check if both the user and advisor exist
        if (optionalUser.isEmpty() || optionalAdvisor.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or advisor not found");
        }

        User user = optionalUser.get();
        Advisor advisor = optionalAdvisor.get();

        // Create a new booking and set the user, advisor, and booking time
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setAdvisor(advisor);
        booking.setBookingTime(bookingTime);

        // Save the booking in the database
        bookingRepository.save(booking);

        // Return a success response
        return ResponseEntity.ok("Booking successfully created");
    }
}



















//import sbcallmanagement.entity.Booking;
//import sbcallmanagement.repository.BookingRepository;
//
//@Service
//public class BookingService {
//    private final BookingRepository bookingRepository;
//
//    public BookingService(BookingRepository bookingRepository) {
//        this.bookingRepository = bookingRepository;
//    }
//
//    public Booking bookCall(Long userId, Long advisorId, Booking booking) {
//        // Perform any necessary validations or business logic
//        // Set the user and advisor based on the IDs
//        // ...
//        return bookingRepository.save(booking);
//    }
//
//    public List<Booking> getBookedCallsByAdvisorId(Long advisorId) {
//        return bookingRepository.findAllByAdvisor_Id(advisorId);
//    }
//
//    public List<Booking> getBookedCallsByUserId(Long userId) {
//        return bookingRepository.findAllByUser_Id(userId);
//    }
//}
