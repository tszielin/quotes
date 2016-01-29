package tszielin.quotes.repository;

import org.springframework.stereotype.Repository;

import tszielin.quotes.domain.FEDQuote;

@Repository("fed-quotes")
public interface FEDQuoteRepository extends Quotes<FEDQuote> {
}
