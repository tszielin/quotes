package tszielin.quotes.aggregation;

import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.stereotype.Component;

import tszielin.quotes.domain.NBPQuote;

@Component
public class NBPAggregation extends Aggregation<NBPQuote> {
    @Override
    protected GroupOperation getGroupOperation() {
        return super.getGroupOperation().first("scalar").as("scalar").first("tableId").as("tableId");
    }
}
