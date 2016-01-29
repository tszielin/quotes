package tszielin.quotes.domain;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Quotes (exchanges) provided by European Central Bank
 * @author Thomas Zielinski
 */
@Document(collection = "ecb-quotes")
public class ECBQuote extends Quote {
    private static final long serialVersionUID = -636141486839328842L;

    protected ECBQuote() {
    }

    /**
     * Create the {@code ECBQuote} object
     * @param rate the rate of the exchange
     * @param currency the currency
     * @param publicated date of publication
     */
    public ECBQuote(double rate, Currency currency, Date publicated) {
        super(rate, currency, publicated);
    }

    @Override
    public Provider getProvider() {
        return Provider.ECB;
    }
}
