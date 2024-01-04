package yinder.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yinder.demo.model.Businesses;

@Repository
public interface BusinessRepository extends JpaRepository<Businesses, Long> {
}
