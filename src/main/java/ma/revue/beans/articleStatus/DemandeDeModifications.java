package ma.revue.beans.articleStatus;

import ma.revue.beans.Article;
import ma.revue.beans.Review;

import javax.persistence.Entity;

@Entity
public class DemandeDeModifications extends StatusArticle {
    public DemandeDeModifications() {
        super.setNom("DEMANDE_DE_MODIFICATION");
    }

    @Override
    public void modifier(Article article,Article newArticle) throws Exception {
        article.setTitre(newArticle.getTitre());
        article.setContenue(newArticle.getContenue());
        article.setResume(newArticle.getResume());
        article.setStatus(new ReEvaluation());
    }
}
