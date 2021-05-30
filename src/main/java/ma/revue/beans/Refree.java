package ma.revue.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import java.util.List;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Refree extends User{


    public Refree(Long id, String email, String password, boolean enabled, Profil profil, Role role, List<Review> reviews) {
        super(id, email, password, enabled, profil, role);
        this.reviews = reviews;
    }

    @OneToMany(mappedBy = "refree")
    List<Review> reviews;



    @PrePersist
    protected void prePresist(){
        super.role.setRole("REFREE");
        enabled=true;
    }
}
