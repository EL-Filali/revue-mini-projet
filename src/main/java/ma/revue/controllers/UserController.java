package ma.revue.controllers;

import ma.revue.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserServices userServices;
    @GetMapping("/articles/{id}")
    public ResponseEntity getArticles(@PathVariable Long id , Principal principal) throws Exception {
        try{
            return new ResponseEntity(  userServices.getArticle(id), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity( e.getMessage() ,HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/articles")
    public ResponseEntity getArticles(@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "id") String sortBy, Principal principal){
        return new ResponseEntity(  userServices.getAllArticles(pageNo,pageSize,sortBy),HttpStatus.OK);
    }


}
