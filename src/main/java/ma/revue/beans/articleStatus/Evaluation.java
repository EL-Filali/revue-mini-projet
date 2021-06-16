package ma.revue.beans.articleStatus;

import ma.revue.beans.Article;

import javax.persistence.Entity;

@Entity
public class Evaluation extends StatusArticle {
    public Evaluation() {
        super.setNom("EVALUATION");
    }

    @Override
    public void modifier(Article article, Article newArticle) throws Exception {
        super.modifier(article, newArticle);
    }

    @Override
    public void demandeDeModification(Article article) throws Exception {
       article.setStatus(new DemandeDeModifications());
    }

    @Override
    public void accepter(Article article) throws Exception {
        article.setStatus(new Accepte());
    }

    @Override
    public void refuser(Article article) throws Exception {
        article.setStatus(new RefusCarNeConcernePasLaRevue());
    }


}
