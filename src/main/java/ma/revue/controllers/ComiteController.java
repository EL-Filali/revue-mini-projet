package ma.revue.controllers;

import ma.revue.beans.Comite;
import ma.revue.services.ComiteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comite")
public class ComiteController {

    @Autowired
    ComiteServices comiteServices;


    @GetMapping("/users")
    public ResponseEntity getAllUsers( @RequestParam(defaultValue = "0") Integer pageNo,
                                       @RequestParam(defaultValue = "10") Integer pageSize,
                                       @RequestParam(defaultValue = "id") String sortBy){

        return new ResponseEntity(comiteServices.getAllUsers(pageNo,pageSize,sortBy),HttpStatus.OK);

    }
    @GetMapping("/refrees")
    public ResponseEntity getAllRefree(){
        return new ResponseEntity(comiteServices.getAllRefrees(),HttpStatus.OK);

    }
    @GetMapping("/articles/status=EVALUATION")
    public ResponseEntity getEvaluationArticles( @RequestParam(defaultValue = "0") Integer pageNo,
                                                 @RequestParam(defaultValue = "10") Integer pageSize,
                                                 @RequestParam(defaultValue = "id") String sortBy){
        return new ResponseEntity(comiteServices.getEvaluationArticles(pageNo,pageSize,sortBy),HttpStatus.OK);

    }
    @GetMapping("/articles/status=RE_EVALUATION")
    public ResponseEntity getReEvaluationArticles(@RequestParam(defaultValue = "0") Integer pageNo,
                                                  @RequestParam(defaultValue = "10") Integer pageSize,
                                                  @RequestParam(defaultValue = "id") String sortBy){
        return new ResponseEntity(comiteServices.getReEvaluationArticles(pageNo,pageSize,sortBy),HttpStatus.OK);

    }
    @GetMapping("/articles/status=EN_ATTENTE")
    public ResponseEntity getEnAttenteArticles( @RequestParam(defaultValue = "0") Integer pageNo,
                                                @RequestParam(defaultValue = "10") Integer pageSize,
                                                @RequestParam(defaultValue = "id") String sortBy){
        return new ResponseEntity(comiteServices.getEnAttenteArticles(pageNo,pageSize,sortBy),HttpStatus.OK);
    }
    @PutMapping("/articles/{id}/status=ACCEPTE")
    public ResponseEntity accepterArticle(@PathVariable Long id){
        try{
            comiteServices.accepterArticle(id);
            return new ResponseEntity(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/articles/{id}/status=DEMANDE_DE_MODIFICATIONS")
    public ResponseEntity DemanderDesModiSurArticle(@PathVariable Long id){
        try{
            comiteServices.DemanderDesModiSurArticle(id);
            return new ResponseEntity(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/articles/{id}/status=REFUS")
    public ResponseEntity RefuserArticle(@PathVariable Long id){
        try{
            comiteServices.RefuserArticle(id);
            return new ResponseEntity(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/articles/{id}/status=REFUSER_ARTICLE_CAR_NE_CONCERNE_PAS")
    public ResponseEntity refuserArticleCarNeCancernePas(@PathVariable Long id){
        try{
            comiteServices.refuserArticleCarNeCancernePas(id);
            return new ResponseEntity(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/users/{id}")
    public ResponseEntity EnableOrDisableUtilisateur(@PathVariable Long id){
        try{
            comiteServices.EnableOrDisableUtilisateur(id);
            return new ResponseEntity(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/articles/{id}/reviews")
    public ResponseEntity AffecteRefreeArticle(@PathVariable Long id,@RequestBody List<Long> refreesIds){
        try{
            comiteServices.AffecteRefreeArticle(refreesIds,id);
            return new ResponseEntity(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/articles/{id}/reviewed")
    public ResponseEntity getArticlesApresReviewing(){
        return new ResponseEntity(comiteServices.getArticlesApresReviewing(),HttpStatus.OK);
    }
}
