package ma.revue.repositories;

import ma.revue.beans.Refree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreeRepository extends JpaRepository<Refree,Long> {
}
