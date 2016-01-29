package tszielin.quotes.download.reader;

import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalConfiguration;

import tszielin.quotes.domain.ECBQuote;

abstract public class ECBReader extends XMLReader<ECBQuote> {    
    private static final long serialVersionUID = 5950882782100991686L;

    public ECBReader(URL url) throws ConfigurationException {
        super(url);
    }

    public List<ECBQuote> getData() throws ParseException {
        List<ECBQuote> exchanges = new ArrayList<>();
        List<HierarchicalConfiguration> list = configurationsAt("Cube.Cube");
        if (list != null && !list.isEmpty()) {
            for (HierarchicalConfiguration hc : list) {
                Date publicated = DATE_FORMATTER.parse(hc.getString("[@time]"));
                List<HierarchicalConfiguration> items = hc.configurationsAt("Cube");
                for (HierarchicalConfiguration item : items) {
                    ECBQuote exchange = readExchange(item, null, null, publicated);
                    if (exchange != null)
                        exchanges.add(exchange);
                }
            }
        }
        return exchanges;
    }

    abstract public ECBQuote readExchange(HierarchicalConfiguration hc, DecimalFormat df, 
            String tableId, Date publicated) throws ParseException;
}
