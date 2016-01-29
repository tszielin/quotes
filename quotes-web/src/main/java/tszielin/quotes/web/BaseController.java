package tszielin.quotes.web;

import java.util.Map;

import org.springframework.ui.Model;

import tszielin.quotes.web.constans.ModelAttributesNames;

public class BaseController {
    protected void putMessage(Map<String, Object> model, String message) {
        if (message != null && !message.trim().isEmpty()) {
            model.put(ModelAttributesNames.MESSAGE, message);
        }
    }

    protected void putMessage(Model model, String message) {
        if (message != null && !message.trim().isEmpty()) {
            model.addAttribute(ModelAttributesNames.MESSAGE, message);
        }
    }

    protected void putError(Map<String, Object> model, Throwable cause) {
        model.put(ModelAttributesNames.ERROR, cause.getMessage());
    }

    protected void putError(Model model, Throwable cause) {
        model.addAttribute(ModelAttributesNames.ERROR, cause.getMessage());
    }
    
    protected void putError(Map<String, Object> model, String error) {
        if (error != null && !error.trim().isEmpty()) {
            model.put(ModelAttributesNames.ERROR, error);
        }
    }

    protected void putError(Model model, String error) {
        if (error != null && !error.trim().isEmpty()) {
            model.addAttribute(ModelAttributesNames.ERROR, error);
        }
    }
}
