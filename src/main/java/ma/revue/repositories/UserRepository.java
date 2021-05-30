package ma.revue.repositories;

import ma.revue.beans.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public interface UserRepository extends JpaRepository<User,Long> {

    public User findByEmail(String email);






    @Modifying
    @Query("Update User a SET a.enabled = " +
            "  CASE a.enabled " +
            "    WHEN TRUE THEN FALSE" +
            "    ELSE TRUE END WHERE a.id=:id")
    public void disableOrEnableUser(@Param("id") Long id);

}
