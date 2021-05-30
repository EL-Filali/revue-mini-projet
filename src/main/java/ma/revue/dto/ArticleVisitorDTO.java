package ma.revue.dto;

import lombok.Getter;
import lombok.Setter;
import ma.revue.beans.Auteur;

import javax.persistence.ElementCollection;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.List;
@Getter @Setter
public class ArticleVisitorDTO {

    private Long id;
    private String titre;
    private Auteur auteur;
    private List<Auteur> coAuteurs;
    private String resume;
    private List<String> motsclés;
}
