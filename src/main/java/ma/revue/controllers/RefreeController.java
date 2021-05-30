package ma.revue.controllers;

import ma.revue.beans.Review;
import ma.revue.services.RefreeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/refree")
public class RefreeController {
    @Autowired
    RefreeServices refreeServices;

    @PostMapping("/reviews")
    public ResponseEntity saveReviews(@RequestBody Review review, Principal principal){
        try{
            refreeServices.saveReviews(principal.getName(), review);
            return  new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/reviews/empty")
    public ResponseEntity getReviewsARemplire(Principal principal){
        try{
            return  new ResponseEntity(refreeServices.getEmptyReviews(principal.getName()),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/reviews")
    public ResponseEntity getReviewsRemplie(Principal principal){
        try{
            return  new ResponseEntity(refreeServices.getNotEmptyReviews(principal.getName()),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

}
