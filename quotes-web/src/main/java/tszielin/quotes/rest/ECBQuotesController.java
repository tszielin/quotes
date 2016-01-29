package tszielin.quotes.rest;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tszielin.quotes.domain.ECBQuote;
import tszielin.quotes.domain.Quote;
import tszielin.quotes.repository.ECBQuoteRepository;
import tszielin.quotes.web.CurrencyController;

@Controller
public class ECBQuotesController implements Quoteable<ECBQuote>{
    @Autowired
    private ECBQuoteRepository repository;

    @Override
    public List<ECBQuote> quotes(Date publicated) {
        return repository.find(publicated, CurrencyController.CURRENCY_SORT);
    }
    
    @Override    
    public List<ECBQuote> quotes(String currency) {
        return repository.find(currency, CurrencyController.PUBLICATED_SORT);
    }
    
    @Override
    public Quote quote(String currency, Date publicated) {
        return repository.find(currency, publicated);
    }
}
