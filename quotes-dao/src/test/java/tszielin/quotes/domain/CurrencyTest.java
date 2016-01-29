package tszielin.quotes.domain;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class CurrencyTest {
    @Test
    public void testCurrency() {
        Currency currency = new Currency("XXX", "Test currency");
        assertNotNull(currency);
        assertNotNull(currency.getCode());
        assertNotNull(currency.getCode());
        assertNotNull(currency.getDescription());
    }
}
