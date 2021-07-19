package security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import security.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

  boolean existsByEmail(String email);

  User findByName(String name);

}
