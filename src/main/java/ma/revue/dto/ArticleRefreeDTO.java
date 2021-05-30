package ma.revue.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter @Setter
public class ArticleRefreeDTO {
    private Long id;
    private String titre;
    private String resume;
    private List<String> motsclés;
}
