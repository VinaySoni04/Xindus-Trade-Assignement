package XindusTrade.Assignment.Repositories;

import XindusTrade.Assignment.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    // JpaRepository is an interface provided by Spring Data JPA.
    // Spring Data JPA simplifies the implementation of data access layers by providing a set of abstractions
    // and utility methods to interact with databases using the Java Persistence API (JPA).
    User findByUsername(String username); // Interface automatically will provide implementation
}
