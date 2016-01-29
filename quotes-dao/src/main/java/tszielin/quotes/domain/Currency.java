package tszielin.quotes.domain;

import java.io.Serializable;

import org.springframework.data.mongodb.core.index.TextIndexed;

/**
 * Information about a currency. Contains
 * <ul>
 *  <li>code
 *  <li>description
 * </ul> 
 * @author Thomas Zielinski
 */
public class Currency implements Serializable {
    private static final long serialVersionUID = -6852421775849118565L;
    @TextIndexed
    private String code;
    private String description;
    
    /**
     * Create an object
     */
    protected Currency() {
    }

    /**
     * Create an object
     * @param code currency code
     * @param description currency description
     */
    public Currency(String code, String description) {
        setCode(code);
        setDescription(description);
    }
    
    /**
     * Get a currency code
     * @return the currency code
     */
    public String getCode() {
        return code;
    }

    /**
     * Set a currency code
     * @param code the currency code
     */
    protected void setCode(String code) {
        this.code = code;
    }

    /**
     * Get a currency description
     * @return the currency description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set a currency description
     * @param code the currency description
     */
    protected void setDescription(String description) {
        this.description = description;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((this.code == null) ? 0 : this.code.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Currency other = (Currency)obj;
        if (this.code == null) {
            if (other.code != null)
                return false;
        } else if (!this.code.equals(other.code))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "[code=" + this.code + ", description=" + this.description + "]";
    }   
}
