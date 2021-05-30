package ma.revue.services;

import ma.revue.beans.Article;
import ma.revue.beans.Auteur;
import ma.revue.beans.Refree;
import ma.revue.beans.Review;
import ma.revue.beans.articleStatus.EnAttente;
import ma.revue.dto.ReviewDTO;
import ma.revue.repositories.ArticleRepository;
import ma.revue.repositories.AuteurRepository;
import ma.revue.repositories.RefreeRepository;
import ma.revue.repositories.ReviewRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuteurServices {

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private RefreeRepository refreeRepository;
    @Autowired
    private AuteurRepository auteurRepository;
    @Autowired
    private     ModelMapper modelMapper;
    @Autowired
    private ReviewRepository reviewRepository;

    public Page<Article> getArticles(Integer pageNo, Integer pageSize, String sortBy,String email){
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Article> articles=articleRepository.findByAuteur_Email(email,paging);
        return articles;
    }

    public Article getArticle(Long id,String email) throws Exception {
        Article article= articleRepository.findByAuteur_EmailAndId(email, id);
        if(article==null)
            return article;
        else
            throw new Exception("Aucun article avec cet id");
    }

    public List<ReviewDTO> getArticleReview(Long id,String email) throws Exception {
        Article article= articleRepository.findByAuteur_EmailAndId(email,id);
        if(article==null) {

            List<Review> reviews = article.getReviews();
            TypeToken<List<ReviewDTO>> typeToken = new TypeToken() {
            };
            return modelMapper.map(reviews, typeToken.getType());
        }

        else
            throw new Exception("Aucun articl avec cet id");
    }

    public void createArticle(Article article,String email){
        Auteur auteur=auteurRepository.findByEmail(email);
        article.setAuteur(auteur);
        Article article1=articleRepository.save(article);
        article1.setStatus(new EnAttente());
        articleRepository.save(article);
    }

    public void updateArticle(Article newArticle,String email) throws Exception {
        Article oldArticle = articleRepository.findByAuteur_EmailAndId(email,newArticle.getId());
        oldArticle.modifier(newArticle);
        articleRepository.save(oldArticle);
    }




}
