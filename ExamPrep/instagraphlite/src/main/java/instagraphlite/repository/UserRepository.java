package instagraphlite.repository;

import instagraphlite.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUsername(String username);

    User findByUsername(String username);

    @Query("SELECT DISTINCT u FROM User u JOIN FETCH u.posts p  ORDER BY SIZE(p) DESC," +
            " u.id")
    List<User> findAllByPostCountDescThenByUserId();
}
