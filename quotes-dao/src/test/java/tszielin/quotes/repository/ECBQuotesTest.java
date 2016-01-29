package tszielin.quotes.repository;

import java.util.Date;

import tszielin.quotes.domain.Currency;
import tszielin.quotes.domain.ECBQuote;

public class ECBQuotesTest extends QuotesTest<ECBQuote> {

    @Override
    protected ECBQuote store() {
        return repository.save(new ECBQuote(1.9876, new Currency("XXX", "Dummy currency"), new Date(0L)));
    }
}
