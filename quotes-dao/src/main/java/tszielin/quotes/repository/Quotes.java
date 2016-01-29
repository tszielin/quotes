package tszielin.quotes.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import tszielin.quotes.domain.Quote;

public interface Quotes<T extends Quote> extends MongoRepository<T, String> {
    @Transactional(readOnly = true)
    @Query("{'currency.code': {$regex : '^?0$', $options: 'i'}, 'publicated':?1}")
    Quote find(String currency, Date publicated);

    @Transactional(readOnly = true)
    @Query("{'publicated':?0}")
    List<T> find(Date publicated, Sort sort);

    @Transactional(readOnly = true)
    @Query("{'currency.code': {$regex : '^?0$', $options: 'i'}}")
    List<T> find(String currency, Sort sort);
}