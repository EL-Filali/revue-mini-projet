package ma.revue.beans;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.revue.beans.articleStatus.EnAttente;
import ma.revue.beans.articleStatus.StatusArticle;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter  @AllArgsConstructor
@NoArgsConstructor
public class Article {


    @Id
    @GeneratedValue
    private Long id;
    private String titre;
    @ManyToOne
    private Auteur auteur;
    @ManyToMany
    private List<Auteur> coAuteurs;
    @Column(columnDefinition="TEXT")
    private String resume;
    @ElementCollection
    private List<String> motsclés;
    private Date createdAt;
    private Date updatedAt;
    @Column(columnDefinition="TEXT")
    private String contenue;
    @JsonIgnore
    @OneToMany(cascade = {CascadeType.ALL})
    private List<Review> reviews;
    @ManyToOne
    private StatusArticle status;

    @PrePersist
    protected void prePersist(){
        createdAt=new Date();
        updatedAt=new Date();
        status=new EnAttente();
    }

    @PreUpdate
    protected  void preUpdate(){
        createdAt=new Date();
    }

    public void modifier(Article newArticle) throws Exception {
        status.modifier(this,newArticle);
    }

    public void associeRefree(Refree refree) throws Exception {
        if(reviews.size()<=3){
            Review review=new Review();
            review.setRefree(refree);
            reviews.add(review);
        }else {
            throw new Exception("Max de reviews associe est atteint");
        }
    }

    public void refuser() throws Exception {
        status.refuser(this);
    }

    public void demanderDesModification() throws Exception {
        status.demandeDeModification(this);
    }

    public void accepter() throws Exception {
        status.accepter(this);
    }

    public void refuserCarNeConcernePas() throws Exception {
        status.refuserNeConcernePasLaRevus(this);
    }

}
