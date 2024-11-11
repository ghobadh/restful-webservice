package ca.gforcesoftware.restfulwebservice.repository;

import ca.gforcesoftware.restfulwebservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author gavinhashemi on 2024-11-07
 */
public interface UserRepository extends JpaRepository<User, Long> {
    /*
    IMPORTANT NOTE
    When I use JpaRepsoitory, the name of the any method I add in the interface , it should be matched
    with the field name of the object which
    I am using. For example, I added method findByEmail(). Since email field exists in User class file, this method
    will be implemented without any issue automatically by spring framework. However, if I rename the
    method to findByEmailAddress () , there will be
    runtime time error for unsatisfactory of constructor of the controller (misleading error)
     */
    Optional<User> findByEmail(String email);
}
