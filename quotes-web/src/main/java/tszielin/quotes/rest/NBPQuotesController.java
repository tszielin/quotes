package tszielin.quotes.rest;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tszielin.quotes.domain.NBPQuote;
import tszielin.quotes.domain.Quote;
import tszielin.quotes.repository.NBPQuoteRepository;
import tszielin.quotes.web.CurrencyController;

@Controller
public class NBPQuotesController implements Quoteable<NBPQuote> {
    @Autowired
    private NBPQuoteRepository repository;

    @Override
    public List<NBPQuote> quotes(Date publicated) {
        return repository.find(publicated, CurrencyController.CURRENCY_SORT);
    }
    
    @Override
    public List<NBPQuote> quotes(String currency) {
        return repository.find(currency, CurrencyController.PUBLICATED_SORT);
    }
    
    @Override
    public Quote quote(String currency, Date publicated) {
        return repository.find(currency, publicated);
    }
}
