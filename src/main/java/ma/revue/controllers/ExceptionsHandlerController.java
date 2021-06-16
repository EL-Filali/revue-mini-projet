package ma.revue.controllers;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import ma.revue.exceptions.LoginException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class ExceptionsHandlerController  {



    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {UsernameNotFoundException.class})
    public String handleValidationsExceptions(UsernameNotFoundException ex) {
        Map<String, String> errors = new HashMap<>();
        return ex.getMessage();
    }

   @ResponseStatus(HttpStatus.BAD_REQUEST)
   @ExceptionHandler(value={MethodArgumentNotValidException.class})
   public ResponseEntity handleValidationExceptions(
           MethodArgumentNotValidException ex, WebRequest request) {
       Map<String, String> errors = new HashMap<>();
       ex.getBindingResult().getAllErrors().forEach((error) -> {
           String fieldName = ((FieldError) error).getField();
           String errorMessage = error.getDefaultMessage();
           errors.put(fieldName, errorMessage);
       });
       return  new ResponseEntity(errors,HttpStatus.BAD_REQUEST);
   }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value={LoginException.class})
    public ResponseEntity handleLoginExceptions(
            LoginException ex,WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        errors.put("message", ex.getMessage());
        return  new ResponseEntity(errors,HttpStatus.BAD_REQUEST);
    }


     @ExceptionHandler(value={NoSuchElementException.class})
     public ResponseEntity handleLoginExceptions(
             NoSuchElementException ex,WebRequest request) {
         Map<String, String> errors = new HashMap<>();
         errors.put("message", "Invalid JWT Signature");
         return  new ResponseEntity(errors,HttpStatus.BAD_REQUEST);
     }



}
