package tszielin.quotes.aggregation;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregationOptions;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.sort;

import java.util.ArrayList;
import java.util.List;

import org.bson.BasicBSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.GenericTypeResolver;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;

import tszielin.quotes.domain.Currency;
import tszielin.quotes.domain.Quote;

/**
 * Aggregate the records in database.
 * @author Thomas Zielinski
 * @param <T>
 */
public class Aggregation<Q extends Quote> {
    @Autowired
    private MongoTemplate template;

    private final Class<Q> cls;

    /**
     * Create an object
     */
    @SuppressWarnings("unchecked")
    public Aggregation() {
        this.cls = (Class<Q>)GenericTypeResolver.resolveTypeArgument(getClass(), Aggregation.class);
    }
    
    /**
     * Create a group of operations
     * @return the group of operations
     */
    protected GroupOperation getGroupOperation() {
        return group("currency").first("id").as("id").first("currency").as("currency").first("publicated").as("publicated").first("rate").as("rate");
    }
    
    /**
     * Get the list of first {@code Quote}s stored in database
     * @return the list of first {@code Quote}s
     */
    public List<Q> first() {
        return aggregate(Direction.ASC);
    }
    
    /**
     * Get the list of last {@code Quote}s stored in database
     * @return the list of last {@code Quote}s
     */
    public List<Q> last() {
        return aggregate(Direction.DESC);
    }

    /**
     * Create an aggregation and get the list of {@code Quote}s
     * @param direction sort direction
     * @return the list of {@code Quote}s
     */
    private List<Q> aggregate(Direction direction) {
        return template.aggregate(
                newAggregation(
                        sort(direction, "publicated"), 
                        getGroupOperation()).withOptions(newAggregationOptions().allowDiskUse(true).build()), 
                cls, cls).getMappedResults();
    }
    
    /**
     * Get the list of {@code Currency}
     * @return the list of {@code Currency}
     */
    public List<Currency> currencies() {
        List<Currency> list = new ArrayList<>();
        List<?> result = template.getCollection(template.getCollectionName(cls)).distinct("currency");
        if (result != null) {
            for (Object obj : result) {
                if (obj instanceof BasicBSONObject) {
                    list.add(new Currency(
                            ((BasicBSONObject)obj).getString("code"), ((BasicBSONObject)obj).getString("description")));
                }
            }
        }
        return list;
    }
}
