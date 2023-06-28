package sbcallmanagement.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

















//import sbcallmanagement.entity.Advisor;
//import sbcallmanagement.service.AdvisorService;

//@RestController
//@RequestMapping("/admin")
//public class AdminController {
//    private final AdvisorService advisorService;
//
//    public AdminController(AdvisorService advisorService) {
//        this.advisorService = advisorService;
//    }
//
//    @PostMapping("/advisor")
//    public ResponseEntity<Advisor> createAdvisor(@RequestBody Advisor advisor) {
//        Advisor createdAdvisor = advisorService.addAdvisor(advisor);
//        return ResponseEntity.ok(createdAdvisor);
//    }
//
//    @GetMapping("/advisors")
//    public ResponseEntity<List<Advisor>> getAllAdvisors() {
//        List<Advisor> advisors = advisorService.getAllAdvisors();
//        return ResponseEntity.ok(advisors);
//    }
//}
