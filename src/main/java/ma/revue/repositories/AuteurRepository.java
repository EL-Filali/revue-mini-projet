package ma.revue.repositories;

import ma.revue.beans.Auteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface AuteurRepository extends JpaRepository<Auteur,Long> {
    public Auteur findByEmail( String email);
}
