package ma.revue.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
@Getter
@Setter
@Entity  @AllArgsConstructor
@NoArgsConstructor
public class Auteur extends User{

    @OneToMany
    private List<Article> articles;

    @ManyToMany(cascade={CascadeType.ALL})
    private List<Article> contributions;

    public Auteur(Long id, String email, String password, boolean enabled, Profil profil, Role role) {
        super(id, email, password, enabled, profil, role);
    }
    @PrePersist
    protected void prePresist(){
        super.role.setRole("AUTEUR");
        enabled=true;
    }

}
