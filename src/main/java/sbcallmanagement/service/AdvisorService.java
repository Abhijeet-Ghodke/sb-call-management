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
	       
	        List<Advisor> advisors = advisorRepository.findAll();

	       
	        List<AdvisorDTO> advisorDTOs = new ArrayList<>();

	        
	        for (Advisor advisor : advisors) {
	            AdvisorDTO advisorDTO = new AdvisorDTO();
	            advisorDTO.setId(advisor.getId());
	            advisorDTO.setName(advisor.getName());
	            advisorDTO.setPhotoUrl(advisor.getPhotoUrl());
	            advisorDTOs.add(advisorDTO);
	        }

	        
	        return ResponseEntity.ok(advisorDTOs);
	    }
	 
	 public ResponseEntity<List<AdvisorDTO>> getBookedCallsByUserId(Long userId) {
	        
	        List<Booking> bookings = bookingRepository.findByUser_Id(userId);

	        
	        List<AdvisorDTO> advisorDTOs = new ArrayList<>();

	        
	        for (Booking booking : bookings) {
	            AdvisorDTO advisorDTO = new AdvisorDTO();
	            advisorDTO.setId(booking.getAdvisor().getId());
	            advisorDTO.setName(booking.getAdvisor().getName());
	            advisorDTO.setPhotoUrl(booking.getAdvisor().getPhotoUrl());
	            advisorDTO.setBookingTime(booking.getBookingTime());
	            advisorDTO.setBookingId(booking.getId());
	            advisorDTOs.add(advisorDTO);
	        }

	        
	        return ResponseEntity.ok(advisorDTOs);
	    } 
}
