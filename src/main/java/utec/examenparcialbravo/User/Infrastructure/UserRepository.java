package utec.examenparcialbravo.User.Infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import utec.examenparcialbravo.User.Domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

}
