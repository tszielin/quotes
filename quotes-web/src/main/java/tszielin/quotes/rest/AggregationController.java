package tszielin.quotes.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tszielin.quotes.aggregation.Aggregation;
import tszielin.quotes.aggregation.ECBAggregation;
import tszielin.quotes.aggregation.FEDAggregation;
import tszielin.quotes.aggregation.NBPAggregation;
import tszielin.quotes.domain.Currency;
import tszielin.quotes.domain.Provider;
import tszielin.quotes.domain.Quote;

@RestController("restCurrencies")
@RequestMapping("/api/currencies/{provider}")
public class AggregationController {
    @Autowired
    private NBPAggregation nbpAggregation;
    @Autowired
    private ECBAggregation ecbAggregation;
    @Autowired
    private FEDAggregation fedAggregation;
        
    private Aggregation<? extends Quote> getAggregation(Provider provider) {
        switch (provider) {
            case ECB:
                return ecbAggregation;
            case FED:
                return fedAggregation;
            default:
                return nbpAggregation;
        }
    }
    
    private Provider getProvider(String provider) {
        try {
            return provider != null ? Provider.valueOf(provider.toUpperCase()) : Provider.ECB;
        } catch (Exception ex) {
            return Provider.ECB;
        }
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public List<Currency> currencies(@PathVariable String provider) {
        return getAggregation(getProvider(provider)).currencies();
    }
    
    @RequestMapping(value="/list/first", method = RequestMethod.GET)
    public List<? extends Quote> first(@PathVariable String provider) {
        return getAggregation(getProvider(provider)).first();
    }
    
    @RequestMapping(value="/list/last", method = RequestMethod.GET)
    public List<? extends Quote> last(@PathVariable String provider) {
        return getAggregation(getProvider(provider)).last();
    }
    
    @RequestMapping(value="/{currency}", method = RequestMethod.GET)
    public Currency currency(@PathVariable String provider, @PathVariable String currency) {
        List<Currency> list = currencies(provider);
        if (list != null) {
            for (Currency c : list) {
                if (c.getCode().equalsIgnoreCase(currency)) {
                    return c;
                }
            }
        }
        return null;
    }
}
