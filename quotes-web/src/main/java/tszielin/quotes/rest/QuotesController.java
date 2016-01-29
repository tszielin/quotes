package tszielin.quotes.rest;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tszielin.quotes.domain.Provider;
import tszielin.quotes.domain.Quote;

@RestController("restQuotes")
@RequestMapping("/api/quotes/{provider}")
public class QuotesController {
    @Autowired
    private NBPQuotesController nbpQuotes;
    @Autowired
    private ECBQuotesController ecbQuotes;
    @Autowired
    private FEDQuotesController fedQuotes;
    @Autowired
    private AggregationController controller;
        
    private Quoteable<? extends Quote> getContoller(Provider provider) {
        switch (provider) {
            case ECB:
                return ecbQuotes;
            case FED:
                return fedQuotes;
            default:
                return nbpQuotes;
        }
    }
    
    private Provider getProvider(String provider) {
        try {
            return provider != null ? Provider.valueOf(provider.toUpperCase()) : Provider.ECB;
        } catch (Exception ex) {
            return Provider.ECB;
        }
    }
    
    @RequestMapping(value="/on/{publicated}", method = RequestMethod.GET)
    public List<? extends Quote> quotes(@PathVariable String provider, 
            @PathVariable @DateTimeFormat(pattern="yyyy-MM-dd") Date publicated) {
        return getContoller(getProvider(provider)).quotes(publicated);
    }
    
    @RequestMapping(value="/{currency}", method = RequestMethod.GET)
    public List<? extends Quote> quotes(@PathVariable String provider, @PathVariable String currency) {
        return "all".equalsIgnoreCase(currency) ? controller.last(provider) :
            getContoller(getProvider(provider)).quotes(currency);
    }
        
    @RequestMapping(value="/{currency}/on/{publicated}", method = RequestMethod.GET)
    public Quote quote(@PathVariable String provider, @PathVariable String currency, 
            @PathVariable @DateTimeFormat(pattern="yyyy-MM-dd") Date publicated) {
        return getContoller(getProvider(provider)).quote(currency, publicated);
    }

    @RequestMapping(value="/list/first", method = RequestMethod.GET)
    public List<? extends Quote> first(@PathVariable String provider) {
        return controller.first(provider);
    }
    
    @RequestMapping(value="/list/last", method = RequestMethod.GET)
    public List<? extends Quote> last(@PathVariable String provider) {
        return controller.last(provider);
    }
}
