package sbcallmanagement.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import sbcallmanagement.dto.AdvisorDTO;
import sbcallmanagement.entity.Advisor;
import sbcallmanagement.entity.Booking;
import sbcallmanagement.repository.AdvisorRepository;
import sbcallmanagement.repository.BookingRepository;


@Service
public class AdvisorService {
    private final AdvisorRepository advisorRepository;
    private BookingRepository bookingRepository;

    public AdvisorService(AdvisorRepository advisorRepository) {
        this.advisorRepository = advisorRepository;
    }

	public void addAdvisor(String name, String photoUrl) {
		Advisor advisor = new Advisor();
        advisor.setName(name);
        advisor.setPhotoUrl(photoUrl);
        advisorRepository.save(advisor);
	}
	
	 public ResponseEntity<List<AdvisorDTO>> getAdvisorsByUserId(Long userId) {
	        // Retrieve the advisors from the database
	        List<Advisor> advisors = advisorRepository.findAll();

	        // Create a list of AdvisorDTO objects to hold the response data
	        List<AdvisorDTO> advisorDTOs = new ArrayList<>();

	        // Convert the Advisor entities to AdvisorDTOs
	        for (Advisor advisor : advisors) {
	            AdvisorDTO advisorDTO = new AdvisorDTO();
	            advisorDTO.setId(advisor.getId());
	            advisorDTO.setName(advisor.getName());
	            advisorDTO.setPhotoUrl(advisor.getPhotoUrl());
	            advisorDTOs.add(advisorDTO);
	        }

	        // Return the list of AdvisorDTOs with a success response
	        return ResponseEntity.ok(advisorDTOs);
	    }
	 
	 public ResponseEntity<List<AdvisorDTO>> getBookedCallsByUserId(Long userId) {
	        // Retrieve the bookings for the given user ID from the database
	        List<Booking> bookings = bookingRepository.findByUser_Id(userId);

	        // Create a list of AdvisorDTO objects to hold the response data
	        List<AdvisorDTO> advisorDTOs = new ArrayList<>();

	        // Convert the bookings to AdvisorDTOs
	        for (Booking booking : bookings) {
	            AdvisorDTO advisorDTO = new AdvisorDTO();
	            advisorDTO.setId(booking.getAdvisor().getId());
	            advisorDTO.setName(booking.getAdvisor().getName());
	            advisorDTO.setPhotoUrl(booking.getAdvisor().getPhotoUrl());
	            advisorDTO.setBookingTime(booking.getBookingTime());
	            advisorDTO.setBookingId(booking.getId());
	            advisorDTOs.add(advisorDTO);
	        }

	        // Return the list of AdvisorDTOs with a success response
	        return ResponseEntity.ok(advisorDTOs);
	    }
	 
}





//import sbcallmanagement.entity.Advisor;
//import sbcallmanagement.repository.AdvisorRepository;

//@Service
//public class AdvisorService {
//    private final AdvisorRepository advisorRepository;
//
//    public AdvisorService(AdvisorRepository advisorRepository) {
//        this.advisorRepository = advisorRepository;
//    }
//
//    public Advisor addAdvisor(Advisor advisor) {
//        return advisorRepository.save(advisor);
//    }
//
//    public List<Advisor> getAllAdvisors() {
//        return advisorRepository.findAll();
//    }
//}
