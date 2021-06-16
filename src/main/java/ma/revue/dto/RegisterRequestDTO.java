package ma.revue.dto;

import lombok.Data;
import ma.revue.beans.Auteur;
import ma.revue.beans.Profil;
import ma.revue.beans.User;

import javax.validation.constraints.Pattern;
@Data
public class RegisterRequestDTO {
    @Pattern(regexp = "[a-zA-Z0-9][.a-zA-Z0-9]*[a-zA-Z0-9]@[a-zA-Z0-9]*[.a-zA-Z0-9].[a-zA-Z0-9]{2,3}")
    protected String email;
    protected String password;
    @Pattern(regexp = "(.+212|0)(5|6)[0-9]{10}")
    private String numeroTel;
    @Pattern(regexp = "[a-zA-Z]{5,}")
    private String nom;
    @Pattern(regexp = "[a-zA-Z]{5,}")
    private String prenom;
    private String adresse;
    public Auteur toAuteur(){
        Profil profil=new Profil(prenom , nom ,numeroTel,adresse);
        Auteur user=new Auteur(email,password,profil) ;
        return user;
    }
}
