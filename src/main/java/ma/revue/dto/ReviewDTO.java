package ma.revue.dto;

import lombok.Getter;
import lombok.Setter;
import ma.revue.beans.Article;
import ma.revue.beans.StatusReview;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter @Setter
public class ReviewDTO {
    private Long id ;
    private ArticleRefreeDTO article;

    private String commentaire;
    private StatusReview status;


}
