package ma.revue.beans.articleStatus;

import ma.revue.beans.Article;

import javax.persistence.Entity;

@Entity
public class Refus extends StatusArticle {
    public Refus() {
        super.setNom("REFUS");
    }

    @Override
    public void modifier(Article article,Article newArticle) throws Exception {
        super.modifier(article, newArticle);
    }
}
