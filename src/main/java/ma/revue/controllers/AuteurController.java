package ma.revue.controllers;

import ma.revue.beans.Article;
import ma.revue.services.AuteurServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/auteur")
public class AuteurController {

    @Autowired
    AuteurServices auteurServices;
    @GetMapping("/articles")
    public ResponseEntity getArticles(@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "id") String sortBy, Principal principal){
        return new ResponseEntity(  auteurServices.getArticles(pageNo,pageSize,sortBy,principal.getName()),HttpStatus.OK);
    }
    @GetMapping("/articles/{id}")
    public ResponseEntity getArticles(@PathVariable Long id ,Principal principal) throws Exception {
        try{
            return new ResponseEntity(  auteurServices.getArticle(id,principal.getName()),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity( e.getMessage() ,HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping("/articles/{id}/reviews")
    public ResponseEntity getArticleReview(@PathVariable Long id,Principal principal){
        try{
            return new ResponseEntity(  auteurServices.getArticle(id, principal.getName()),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity( e.getMessage() ,HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/articles")
    public ResponseEntity postArticle(@RequestBody Article article,Principal principal){
        try{
            auteurServices.createArticle(article,principal.getName());
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity( e.getMessage() ,HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/articles")
    public ResponseEntity updateArticle(@RequestBody Article article,Principal principal){
        try{
            auteurServices.updateArticle(article,principal.getName());
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity( e.getMessage() ,HttpStatus.BAD_REQUEST);
        }
    }


}
