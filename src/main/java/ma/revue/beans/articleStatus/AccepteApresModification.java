package ma.revue.beans.articleStatus;

import ma.revue.beans.Article;

import javax.persistence.Entity;

@Entity

public class AccepteApresModification extends StatusArticle {

    public AccepteApresModification() {
        super.setNom("ACCEPTE_APRES_MODIFICATION");
    }




}
