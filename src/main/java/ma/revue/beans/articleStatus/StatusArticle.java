package ma.revue.beans.articleStatus;

import lombok.Getter;
import lombok.Setter;
import ma.revue.beans.Article;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public abstract class  StatusArticle {


  @Id
  private String nom;


  public void  modifier(Article article, Article newArticle) throws Exception {
    throw new Exception("Modification de l'article n'est pas autorisé");
  }

  public void demandeDeModification(Article article) throws Exception {
    throw new Exception("Opération non permise");
  }
  public void accepter(Article article) throws Exception {
    throw  new Exception("Opération non permise");
  }

  public void refuser(Article article) throws Exception{
    throw new Exception("Operation non permise");
  }
  public void refuserNeConcernePasLaRevus(Article article) throws Exception{
    throw new Exception("Operation non permise");
  }

}
