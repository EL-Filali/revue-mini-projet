package ma.revue.beans.articleStatus;

import ma.revue.beans.Article;

import javax.persistence.Entity;

@Entity
public class ReEvaluation extends StatusArticle {
    public ReEvaluation() {
        super.setNom("RE_EVALUATION");
    }

    @Override
    public void demandeDeModification(Article article) throws Exception {
        article.setStatus(new DemandeDeModifications());
    }

    @Override
    public void accepter(Article article) throws Exception {
        article.setStatus(new AccepteApresModification());
    }

    @Override
    public void refuser(Article article) throws Exception {
        article.setStatus(new Refus());
    }
}
