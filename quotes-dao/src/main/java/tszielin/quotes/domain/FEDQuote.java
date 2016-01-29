package tszielin.quotes.domain;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Quotes (exchanges) provided by Federal Reserve System
 * @author Thomas Zielinski
 */
@Document(collection = "fed-quotes")
public class FEDQuote extends Quote {
    private static final long serialVersionUID = -4998012374977063981L;

    protected FEDQuote() {
    }

    /**
     * Create the {@code FEDQuote} object
     * @param rate the rate of the exchange
     * @param currency the currency
     * @param publicated date of publication
     */
    public FEDQuote(double rate, Currency currency, Date publicated) {
        super(rate, currency, publicated);
    }

    @Override
    public Provider getProvider() {
        return Provider.FED;
    }
}
