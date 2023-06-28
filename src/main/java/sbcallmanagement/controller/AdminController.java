package sbcallmanagement.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sbcallmanagement.service.AdvisorService;
@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AdvisorService advisorService;

    public AdminController(AdvisorService advisorService) {
        this.advisorService = advisorService;
    }

    @PostMapping("/advisor")
    public ResponseEntity<Void> addAdvisor(@RequestParam String name, @RequestParam String photoUrl) {
        advisorService.addAdvisor(name, photoUrl);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
