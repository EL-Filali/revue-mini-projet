package ma.revue.beans;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Review {
    @Id @GeneratedValue
    private Long id ;
    private String commentaire;
    @Enumerated(EnumType.ORDINAL)
    private StatusReview status;
    @ManyToOne
    @JoinColumn(name = "id_refree")
    private Refree refree;
    @ManyToOne
    private Article article;
    private Date createdAt;
    private Date submitedAt;

    @PrePersist
    protected void prePresist(){
        createdAt=new Date();
    }

    public Review( Refree refree, Article article) {
        this.id = id;
        this.refree = refree;
        this.article = article;
    }
}
