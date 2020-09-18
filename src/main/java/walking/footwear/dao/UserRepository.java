package walking.footwear.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import walking.footwear.model.User;

import java.util.ArrayList;
import java.util.Optional;
@RepositoryRestResource(collectionResourceRel = "user", path = "user")
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    User findByEmail(String email);

    Long countByEmail(String email);

    ArrayList<User> findByUsernameContainingIgnoreCase(String s1);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
