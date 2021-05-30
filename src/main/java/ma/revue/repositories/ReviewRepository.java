package ma.revue.repositories;

import ma.revue.beans.Article;
import ma.revue.beans.Refree;
import ma.revue.beans.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ReviewRepository extends JpaRepository<Review,Long> {
    @Query("Select r from Review r where r.submitedAt is null and r.refree=:refree ")
     List<Review> getEmptyReviews(@Param("refree")Refree refree);
    @Query("Select r from Review r where r.submitedAt is not null and r.refree=:refree ")
     List<Review> getNotEmptyReviews(@Param("refree")Refree refree);
     int countByArticleAndCommentaireIsNull(Article article);

}
