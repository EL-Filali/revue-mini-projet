package ma.revue.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Profil {
    @Id @GeneratedValue
    private Long id;
    private String numeroTel;
    private String nom;
    private String prenom;
    private String adresse;


    public Profil(String prenom, String nom, String numeroTel, String adresse) {
        this.prenom=prenom;
        this.nom=nom;
        this.numeroTel=numeroTel;
        this.adresse=adresse;
    }
}
