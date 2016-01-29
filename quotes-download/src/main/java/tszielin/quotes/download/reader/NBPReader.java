package tszielin.quotes.download.reader;

import java.net.URL;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalConfiguration;

import tszielin.quotes.domain.NBPQuote;

abstract public class NBPReader extends XMLReader<NBPQuote> {    
    private static final long serialVersionUID = -975979802727931187L;
    private static final DecimalFormat DECIMAL_FORMATTER = new DecimalFormat("0.0000");

    public NBPReader(URL url) throws ConfigurationException {
        super(url);
        DecimalFormatSymbols dfs = DECIMAL_FORMATTER.getDecimalFormatSymbols();
        dfs.setDecimalSeparator(',');
        DECIMAL_FORMATTER.setDecimalFormatSymbols(dfs);
    }

    public List<NBPQuote> getData() throws ParseException {
        List<NBPQuote> exchanges = new ArrayList<>();
        Set<String> currencies = new TreeSet<>();
        String tableId = getString("numer_tabeli").trim();
        if (tableId.matches("^\\d+\\/.*")) {
          if (tableId.matches("^\\d{1}?\\/.*")) {
            tableId = "00" + tableId;
          }
          else {
            if (tableId.matches("^\\d{2}?\\/.*")) {
              tableId = "0" + tableId;
            }
          }
        }
        Date publicated = DATE_FORMATTER.parse(getString("data_publikacji"));
        List<HierarchicalConfiguration> list = configurationsAt("pozycja");
        if (list != null && !list.isEmpty()) {            
            for (HierarchicalConfiguration hc : list) {
                NBPQuote exchange = readExchange(hc, DECIMAL_FORMATTER, tableId, publicated);
                if (exchange != null) {
                    if (!currencies.contains(exchange.getCurrency().getCode())) {
                        currencies.add(exchange.getCurrency().getCode());
                        exchanges.add(exchange);
                    }
                }
            }
        }
        return exchanges;
    }

    abstract public NBPQuote readExchange(HierarchicalConfiguration hc, DecimalFormat df, 
            String tableId, Date publicated) throws ParseException;
}
