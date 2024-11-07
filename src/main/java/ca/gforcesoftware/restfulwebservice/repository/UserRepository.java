package ca.gforcesoftware.restfulwebservice.repository;

import ca.gforcesoftware.restfulwebservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author gavinhashemi on 2024-11-07
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
