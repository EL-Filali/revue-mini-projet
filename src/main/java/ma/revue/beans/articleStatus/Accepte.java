package ma.revue.beans.articleStatus;

import javax.persistence.Entity;

@Entity
public class Accepte extends StatusArticle {

    public Accepte() {
        super.setNom("ACCPETE");
    }
}
