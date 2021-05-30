package ma.revue.beans.articleStatus;

import ma.revue.beans.Article;

import javax.persistence.Entity;

@Entity
public class EnAttente extends StatusArticle {
    public EnAttente() {
        super.setNom("EN_ATTENTE");
    }
    @Override
    public void modifier(Article article, Article newArticle) throws Exception {
        article.setTitre(newArticle.getTitre());
        article.setContenue(newArticle.getContenue());
        article.setResume(newArticle.getResume());
    }
}
