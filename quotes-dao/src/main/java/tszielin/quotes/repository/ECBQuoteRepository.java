package tszielin.quotes.repository;

import org.springframework.stereotype.Repository;

import tszielin.quotes.domain.ECBQuote;

@Repository("ecb-quotes")
public interface ECBQuoteRepository extends Quotes<ECBQuote> {
}
