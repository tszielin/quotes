package tszielin.quotes.download;

/**
 * Uploadability of a class is enabled by the class implementing the {@code Uploadable} interface. 
 * @author Thomas Zielinski
 */
public interface Uploadable {
    /**
     * Upload data from the source
     */
    void upload();
}
