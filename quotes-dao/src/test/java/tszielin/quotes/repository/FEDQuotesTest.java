package tszielin.quotes.repository;

import java.util.Date;

import tszielin.quotes.domain.Currency;
import tszielin.quotes.domain.FEDQuote;

public class FEDQuotesTest extends QuotesTest<FEDQuote> {

    @Override
    protected FEDQuote store() {
        return repository.save(new FEDQuote(1.9876, new Currency("XXX", "Dummy currency"), new Date(0L)));
    }
}
