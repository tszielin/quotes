package tszielin.quotes.domain;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Quotes (exchanges) provided by National Bank of Poland
 * @author Thomas Zielinski
 */
@Document(collection = "nbp-quotes")
public class NBPQuote extends Quote {
    private static final long serialVersionUID = 8096492504676465723L;
    
    private String tableId;
    private int scalar;
    
    protected NBPQuote() {
        super();
    }

    /**
     * Create the {@code NBPQuote} object
     * @param scalar the scalar
     * @param rate the rate of the exchange
     * @param currency the currency
     * @param publicated date of publication
     * @param tableId table identifier
     */
    public NBPQuote(int scalar, double rate, Currency currency, Date publicated, String tableId) {
        super(rate, currency, publicated);
        setScalar(scalar);
        setTableId(tableId);
    }

    public String getTableId() {
        return tableId;
    }

    protected void setTableId(String tableId) {
        this.tableId = tableId;
    }
    
    public int getScalar() {
        return scalar;
    }

    protected void setScalar(int scalar) {
        this.scalar = scalar;
    }
    
    @Override
    public Provider getProvider() {
        return Provider.NBP;
    }

    @Override
    public String toString() {
        return super.toString() + ", scalar=" + this.scalar + ", table=" + this.tableId;
    }
}
