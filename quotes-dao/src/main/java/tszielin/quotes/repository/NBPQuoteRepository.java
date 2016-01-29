package tszielin.quotes.repository;

import org.springframework.stereotype.Repository;

import tszielin.quotes.domain.NBPQuote;

@Repository("nbp-quotes")
public interface NBPQuoteRepository extends Quotes<NBPQuote> {
}
