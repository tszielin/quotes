package tszielin.quotes.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

/**
 * Base class for all DAOs. Contains mongo's primary key {@code _id}. 
 * @author Thomas Zielinski
 */
public class Base implements Serializable {
    private static final long serialVersionUID = -5303876151400530468L;
        
    @Id
    private String id;
    
    /**
     * Create an object
     */
    protected Base() {
    }

    /**
     * Set a primary key
     * @param id the primary key
     */
    protected void setId(String id) {
        this.id = id;
    }
    
    /**
     * Get a primary key
     * @return the primary key
     */
    public String getId() {
        return this.id;
    }

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        return true;
    }
}
