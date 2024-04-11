package tn.esprit.com.foyer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.com.foyer.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

  User findByEmail(String email);

}
