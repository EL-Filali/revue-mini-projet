package ma.revue.services;

import ma.revue.beans.Article;
import ma.revue.beans.Refree;
import ma.revue.beans.User;
import ma.revue.beans.articleStatus.EnAttente;
import ma.revue.beans.articleStatus.Evaluation;
import ma.revue.beans.articleStatus.ReEvaluation;
import ma.revue.repositories.ArticleRepository;
import ma.revue.repositories.AuteurRepository;
import ma.revue.repositories.RefreeRepository;
import ma.revue.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComiteServices {

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    RefreeRepository refreeRepository;

    @Autowired
    AuteurRepository auteurRepository;

    @Autowired
    UserRepository userRepository;

    public Page<User> getAllUsers(Integer pageNo,Integer pageSize, String sortBy){
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        return  userRepository.findAll(pageable);
    }

    public List<Refree> getAllRefrees(){
        return refreeRepository.findAll();
    }

    public Page<Article> getEvaluationArticles(Integer pageNo,Integer pageSize, String sortBy){
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        return articleRepository.findByStatus(new Evaluation(),pageable);
    }

    public Page<Article> getReEvaluationArticles(Integer pageNo,Integer pageSize, String sortBy){
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        return articleRepository.findByStatus(new ReEvaluation(),pageable);
    }

    public Page<Article> getEnAttenteArticles(Integer pageNo,Integer pageSize, String sortBy){
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        return articleRepository.findByStatus(new EnAttente(),pageable);
    }

    public void accepterArticle(Long id) throws Exception {
        Optional<Article> articleOptional=articleRepository.findById(id);
        if(articleOptional.isPresent()){
            Article article= articleOptional.get();
            article.accepter();
            articleRepository.save(article);
        }else
            throw new Exception("Aucun article Avec cet id");

    }

    public void DemanderDesModiSurArticle(Long id) throws Exception {
        Optional<Article> articleOptional=articleRepository.findById(id);
        if(articleOptional.isPresent()){
            Article article= articleOptional.get();
            article.demanderDesModification();
            articleRepository.save(article);
        }else
            throw new Exception("Aucun article Avec cet id");

    }

    public void RefuserArticle(Long id) throws Exception {
        Optional<Article> articleOptional=articleRepository.findById(id);
        if(articleOptional.isPresent()){
            Article article= articleOptional.get();
            article.refuser();
            articleRepository.save(article);
        }else
            throw new Exception("Aucun article Avec cet id");
    }



    public void EnableOrDisableUtilisateur(Long id) throws Exception {
        Optional<User> userOptional=userRepository.findById(id);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            user.setEnabled(false);
            userRepository.save(user);
        }else
            throw new Exception("Aucun utilisateur Avec cet id");
    }

    public void AffecteRefreeArticle(List<Long> refreesIds,Long articleID) throws Exception {
        Article article=articleRepository.findByIdAndStatus(articleID,new EnAttente());
        if(article==null)
            throw new Exception("Aucun article avec cet id");
        for (Long id:refreesIds) {

            Optional<Refree> optional=refreeRepository.findById(id);
            if(!optional.isPresent()){
                throw new Exception("Aucun refree avec id = "+id+" !");
            }
            Refree refree=optional.get();
            article.associeRefree(refree);
            articleRepository.save(article);
        }
    }

    public List<Article> getArticlesApresReviewing(){
        return articleRepository.findReviewdArticles();
    }
}
