package ma.revue.beans;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.PrePersist;
@Entity
public class Comite extends User{

    public Comite(Long id, String email, String password, boolean enabled, Profil profil, Role role) {
        super(id, email, password, enabled, profil, role);
    }

    public Comite() {
        super();
    }

    @PrePersist
    protected void prePresist(){
        super.role.setRole("COMITE");
        enabled=true;
    }
}
