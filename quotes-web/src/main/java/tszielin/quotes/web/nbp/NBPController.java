package tszielin.quotes.web.nbp;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tszielin.quotes.domain.NBPQuote;
import tszielin.quotes.domain.Provider;
import tszielin.quotes.web.CurrencyController;

/**
 * Web controller
 * 
 * @author Thomas Zielinski
 */
@Controller("nbpController")
public class NBPController extends CurrencyController<NBPQuote> {

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/nbp", method = RequestMethod.GET)
    public String home(Model model) {
        page(model, Provider.NBP);
        return Provider.NBP.name().toLowerCase();
    }
    
    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/nbp/{currency}", method = RequestMethod.GET)
    public String home(Model model, @PathVariable String currency) {
        page(model, currency, Provider.NBP);
        return "bycurrency";
    }
    
    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/nbp/on/{publicated}", method = RequestMethod.GET)
    public String home(Model model, @PathVariable @DateTimeFormat(pattern="yyyy-MM-dd") Date publicated) {
        page(model, publicated, Provider.NBP);
        return "bydate";
    }
}
