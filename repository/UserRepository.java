package application.repository;

import application.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findUserByFullname(String fullname);

    Optional<User> findUserByUsername(String username);
}
