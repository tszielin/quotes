package tszielin.quotes.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import tszielin.quotes.domain.User;
import tszielin.quotes.repository.UserRepository;
import tszielin.quotes.web.constans.ModelAttributesNames;

/**
 * Web controller
 * 
 * @author Thomas Zielinski
 * @since 2015-11-29
 */
@Controller
public class HomeController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);
    @Autowired
    private UserRepository users;    

    @RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "logout", required = false) String logout) {
        LOGGER.debug("Main screen");
        ModelAndView model = new ModelAndView("login");        
        if (logout != null) {
            model.addObject(ModelAttributesNames.MESSAGE, "You've been logged out successfully.");
        }
        return model;
    }
    
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
        LOGGER.debug("Register a new user.");
        return "register";
    }
    
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView register(
            @RequestParam(value = "email", required = true) String email,
            @RequestParam(value = "password", required = true) String password,
            @RequestParam(value = "firstname", required = true) String firstname,
            @RequestParam(value = "lastname", required = true) String lastname) {

        LOGGER.debug("Register a new user.");
        ModelAndView model = new ModelAndView("login");
        User user = new User(firstname, lastname, email, password);
        try {
            if (users.find(user.getEmail()) != null) {
                model.setViewName("register");
                model.addObject("email", user.getEmail());
                model.addObject("firstname", user.getFirstName());
                model.addObject("lastname", user.getLastName());
                throw new UsernameNotFoundException("User '" + user.getEmail() + "' exists.");
            }
            users.save(user);
            LOGGER.debug("An user {} stored.", user);
            putMessage(model.getModel(), "Log in with your credentials.");
        } catch (Exception ex) {
            putError(model.getModel(), ex.getMessage());
        }
        return model;
    }
}
