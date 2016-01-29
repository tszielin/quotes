package tszielin.quotes.web.ecb;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tszielin.quotes.domain.ECBQuote;
import tszielin.quotes.domain.Provider;
import tszielin.quotes.web.CurrencyController;

/**
 * Web controller
 * 
 * @author Thomas Zielinski
 */
@Controller("ecbController")
public class ECBController extends CurrencyController<ECBQuote> {

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/ecb", method = RequestMethod.GET)
    public String home(Model model) {
        page(model, Provider.ECB);
        return Provider.ECB.name().toLowerCase();
    }
    
    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/ecb/{currency}", method = RequestMethod.GET)
    public String home(Model model, @PathVariable String currency) {
        page(model, currency, Provider.ECB);
        return "bycurrency";
    }
    
    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/ecb/on/{publicated}", method = RequestMethod.GET)
    public String home(Model model, @PathVariable @DateTimeFormat(pattern="yyyy-MM-dd") Date publicated) {
        page(model, publicated, Provider.ECB);
        return "bydate";
    }
}
