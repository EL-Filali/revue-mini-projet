package ma.revue.beans.articleStatus;

import ma.revue.beans.Article;

import javax.persistence.Entity;

@Entity
public class RefusCarNeConcernePasLaRevue extends StatusArticle {
    public RefusCarNeConcernePasLaRevue() {
        super.setNom("REFUS_CAR_NE_CONCERNE_PAS_LA_REVUS");
    }

    @Override
    public void modifier(Article article,Article newArticle) throws Exception {
        super.modifier(article, newArticle);
    }
}
