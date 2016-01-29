package tszielin.quotes.domain;

import java.util.Date;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;

/**
 * Base class for the quotes
 * @author Thomas Zielinski
 */
public abstract class Quote extends Base {
    private static final long serialVersionUID = 8467936384517700174L;    
    private double rate;
    @Indexed
    private Currency currency;
    @Indexed
    private Date publicated;

    /**
     * Create an object
     */
    protected Quote() {
    }

    /**
     * Create the {@code Quote} object
     * @param rate the rate of the exchange
     * @param currency the currency
     * @param publicated date of publication
     */
    public Quote(double rate, Currency currency, Date publicated) {
        setRate(rate);
        setCurrency(currency);
        setPublicated(publicated);
    }    

    /**
     * Get a rate of the exchange
     * @return the rate of the exchange
     */
    public double getRate() {
        return rate;
    }

    /**
     * Set a rate of the exchange
     * @param rate the rate of the exchange
     */
    protected void setRate(double rate) {
        this.rate = rate;
    }

    /**
     * Get a data provider
     * @return the data [rpvoder
     */
    @Transient
    abstract public Provider getProvider();
    
    /**
     * Get an information about currency
     * @return the information about currency
     */
    public Currency getCurrency() {
        return currency;
    }

    /**
     * Set an information about currency
     * @param the information about currency
     */
    protected void setCurrency(Currency currency) {
        this.currency = currency;
    }
    
    /**
     * Get a date of publication
     * @return the date of publication
     */
    public Date getPublicated() {
        return publicated;
    }

    /**
     * Set a date of publication
     * @param publicated the date of publication
     */
    protected void setPublicated(Date publicated) {
        this.publicated = publicated;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((this.currency == null) ? 0 : this.currency.hashCode());
        result = prime * result + ((this.publicated == null) ? 0 : this.publicated.hashCode());
        long temp = Double.doubleToLongBits(this.rate);
        result = prime * result + (int)(temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Quote other = (Quote)obj;
        if (this.currency == null) {
            if (other.currency != null)
                return false;
        } else if (!this.currency.equals(other.currency))
            return false;
        if (this.publicated == null) {
            if (other.publicated != null)
                return false;
        } else if (!this.publicated.equals(other.publicated))
            return false;
        if (Double.doubleToLongBits(this.rate) != Double.doubleToLongBits(other.rate))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "provider=" + getProvider() + ", rate=" + this.rate + 
                ", currency=" + this.currency + ", publicated=" + this.publicated;
    }
}
