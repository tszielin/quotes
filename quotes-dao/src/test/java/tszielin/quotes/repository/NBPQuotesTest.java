package tszielin.quotes.repository;

import java.util.Date;

import tszielin.quotes.domain.Currency;
import tszielin.quotes.domain.NBPQuote;

public class NBPQuotesTest extends QuotesTest<NBPQuote> {

    @Override
    protected NBPQuote store() {
        return repository.save(new NBPQuote(100, 123.9876, new Currency("XXX", "Dummy currency"), new Date(0L), "1"));
    }
}
