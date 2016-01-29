package tszielin.quotes.domain;

/**
 * Data provider
 * @author Thomas Zielinski
 */
public enum Provider {
    NBP("Narodowy Bank Polski"), 
    ECB("European Central Bank"), 
    FED("Federal Reserve System"); 

    private final String description;

    private Provider(final String description) {
        this.description = description;
    }

    public String description() {
        return description;
    }
    
    @Override
    public String toString() {
        return name() + " (" + this.description + ")";
    }
}
