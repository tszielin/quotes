package tszielin.quotes.rest;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tszielin.quotes.domain.FEDQuote;
import tszielin.quotes.domain.Quote;
import tszielin.quotes.repository.FEDQuoteRepository;
import tszielin.quotes.web.CurrencyController;

@Controller
public class FEDQuotesController implements Quoteable<FEDQuote>{
    @Autowired
    private FEDQuoteRepository repository;
        
    @Override
    public List<FEDQuote> quotes(Date publicated) {
        return repository.find(publicated, CurrencyController.CURRENCY_SORT);
    }
    
    @Override
    public List<FEDQuote> quotes(String currency) {
        return repository.find(currency, CurrencyController.PUBLICATED_SORT);
    }
    
    @Override
    public Quote quote(String currency, Date publicated) {
        return repository.find(currency, publicated);
    }
}
