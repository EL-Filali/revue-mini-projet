package ma.revue.repositories;

import ma.revue.beans.Article;
import ma.revue.beans.articleStatus.Accepte;
import ma.revue.beans.articleStatus.StatusArticle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional
public interface ArticleRepository extends JpaRepository<Article,Long> {
    Page<Article> findByAuteur_EmailAndStatus(String email,StatusArticle statusArticle,Pageable pageable);
    Page<Article> findByStatus(StatusArticle statusArticle, Pageable pageable);
    Page<Article> findByAuteur_Email(String email,Pageable pageable);
    Article findByAuteur_EmailAndId(String email,Long id);
    Article findByAuteur_EmailAndIdAndStatus(String email,Long id,Accepte accepte);
    Article findByIdAndStatus(Long id,StatusArticle statusArticle);
    @Query("select a from Review r,Article a  where count(r.commentaire)>3 and a.status='EN_ATTENTE' ")
    List<Article> findReviewdArticles();
}
