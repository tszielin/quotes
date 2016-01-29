package tszielin.quotes.download.reader;

import java.lang.reflect.Method;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.XMLConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tszielin.quotes.domain.Currency;
import tszielin.quotes.domain.Quote;

abstract public class XMLReader<T extends Quote> extends XMLConfiguration {    
    private static final long serialVersionUID = -3905737490204700745L;
    private static final Logger LOGGER = LoggerFactory.getLogger(XMLReader.class);
    protected static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd");

    static {
        XMLConfiguration.setDefaultListDelimiter(';');
    }
    
    public XMLReader(URL url) throws ConfigurationException {
        super();
        setAttributeSplittingDisabled(true);
        setDelimiterParsingDisabled(true);
        load(url);
    }
    
    protected void setPublicated(Currency currency, Date publicated) {
        try {
            Method method = Currency.class.getDeclaredMethod("setDate", Date.class);
            method.setAccessible(true);
            method.invoke(currency, publicated);
        }
        catch(Exception ex) {
            LOGGER.warn(ex.getMessage());
        }
    }

    abstract public List<T> getData() throws ParseException;

    abstract public Quote readExchange(HierarchicalConfiguration hc, DecimalFormat df, 
            String tableId, Date publicated) throws ParseException;
}
