package tszielin.quotes.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tszielin.quotes.domain.User;

@Repository("users")
public interface UserRepository extends MongoRepository<User, String> {
    @Transactional(readOnly = true)
    @Query("{'email':?0}")
    User find(String email);

    @Transactional(readOnly = true)
    @Query("{'firstName':?0, 'lastName':?1}")
    User find(String firstName, String lastName);
}
