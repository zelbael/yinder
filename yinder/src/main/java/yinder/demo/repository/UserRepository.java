package yinder.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yinder.demo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}

