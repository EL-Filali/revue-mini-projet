package ma.revue.beans;






import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
@Getter
@Setter
public class Role {
    @Id @GeneratedValue
    private Long id;
    private String  role;

}
