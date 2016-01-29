package tszielin.quotes.web;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.ui.Model;

import tszielin.quotes.aggregation.Aggregation;
import tszielin.quotes.domain.Provider;
import tszielin.quotes.domain.Quote;
import tszielin.quotes.repository.Quotes;
import tszielin.quotes.web.constans.ModelAttributesNames;

/**
 * Web controller
 * 
 * @author Thomas Zielinski
 */
abstract public class CurrencyController<Q extends Quote> extends BaseController {
    public final static Sort CURRENCY_SORT = new Sort(Direction.DESC, "currency");
    public final static Sort PUBLICATED_SORT = new Sort(Direction.DESC, "publicated").and(
            new Sort(Direction.ASC, "currency"));
    
    @Autowired
    private Aggregation<Q> aggregation;
    @Autowired
    private Quotes<Q> quotes;
    
    private void page(Model model, List<Q> list, Provider provider) {
        model.addAttribute(ModelAttributesNames.PROVIDERS, Provider.values());
        model.addAttribute(ModelAttributesNames.PROVIDER, provider);
        model.addAttribute(ModelAttributesNames.QUOTES, list != null ? list : Arrays.<Q>asList());
    }
    
    protected void page(Model model, Provider provider) {     
        page(model, aggregation.last(), provider);
    }
    
    protected void page(Model model, String currency, Provider provider) {     
        page(model, quotes.find(currency, CURRENCY_SORT), provider);
    }
    
    protected void page(Model model, Date publicated, Provider provider) {
        page(model, quotes.find(publicated, PUBLICATED_SORT), provider);
    }
}
