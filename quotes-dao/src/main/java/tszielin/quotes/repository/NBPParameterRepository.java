package tszielin.quotes.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import tszielin.quotes.domain.NBPParameter;

@Repository("nbp-parameters")
public interface NBPParameterRepository extends MongoRepository<NBPParameter, String> {
}
