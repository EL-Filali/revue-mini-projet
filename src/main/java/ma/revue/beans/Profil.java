package ma.revue.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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



}
