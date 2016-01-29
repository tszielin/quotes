package tszielin.quotes.web.fed;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tszielin.quotes.domain.FEDQuote;
import tszielin.quotes.domain.Provider;
import tszielin.quotes.web.CurrencyController;

/**
 * Web controller
 * 
 * @author Thomas Zielinski
 */
@Controller("fedController")
public class FEDController extends CurrencyController<FEDQuote> {

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/fed", method = RequestMethod.GET)
    public String home(Model model) {
        page(model, Provider.FED);
        return Provider.FED.name().toLowerCase();
    }
    
    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/fed/{currency}", method = RequestMethod.GET)
    public String home(Model model, @PathVariable String currency) {
        page(model, currency, Provider.FED);
        return "bycurrency";
    }
    
    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/fed/on/{publicated}", method = RequestMethod.GET)
    public String home(Model model, @PathVariable @DateTimeFormat(pattern="yyyy-MM-dd") Date publicated) {
        page(model, publicated, Provider.FED);
        return "bydate";
    }
}
