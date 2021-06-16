package ma.revue.controllers;

import ma.revue.beans.Auteur;
import ma.revue.dto.LoginRequestDTO;
import ma.revue.dto.RegisterRequestDTO;
import ma.revue.services.VisitorServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;


import javax.security.auth.login.LoginException;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/visitor")
public class VisitorController {
    @Autowired
    VisitorServices visitorServices;
    @PostMapping("/register")
    public ResponseEntity register( @RequestBody @Valid RegisterRequestDTO registerRequestDTO  ){

        return new ResponseEntity(visitorServices.register(registerRequestDTO),HttpStatus.OK);
    }
    @PostMapping("/login")
    public ResponseEntity login(  @RequestBody @Valid LoginRequestDTO loginRequest){

        return new ResponseEntity(visitorServices.connexion(loginRequest),HttpStatus.OK);
    }

    @GetMapping("/articles")
    public ResponseEntity getAllArticles(@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "id") String sortBy){
        return  new ResponseEntity(visitorServices.getAllArticles(pageNo,pageSize,sortBy),HttpStatus.OK);
    }

    @GetMapping("/articles/{id}")
    public ResponseEntity getAllArticle(@PathVariable Long id) throws Exception {
        try{
            return  new ResponseEntity(visitorServices.getArticle(id),HttpStatus.OK);
        }catch(Exception e){
            return  new ResponseEntity(e,HttpStatus.BAD_REQUEST);
        }

    }



    @PostMapping("/initDB")
    public ResponseEntity   initDb(){
        try{
            visitorServices.initDatabase();
            return new ResponseEntity( HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity( e.getMessage() ,HttpStatus.BAD_REQUEST);
        }
    }
}
