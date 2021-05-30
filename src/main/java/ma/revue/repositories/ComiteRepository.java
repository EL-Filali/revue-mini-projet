package ma.revue.repositories;

import ma.revue.beans.Comite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ComiteRepository extends JpaRepository<Comite,Long> {
}
