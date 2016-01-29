package tszielin.quotes.download.reader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import tszielin.quotes.domain.Currency;

public class ECBCurrencyReader {
    private static final String URL = "https://www.ecb.europa.eu/stats/exchange/eurofxref/html/index.en.html";
    
    private Map<String, Currency> currencies = new HashMap<>();
    
    public ECBCurrencyReader() {
        try {
            buildCurrencies();
        }
        catch(IOException ignored) {            
        }
    }

    private void buildCurrencies() throws IOException {
        Elements table = Jsoup.connect(URL).get().select("table[class=ecb-forexTable fullWidth]");
        Elements rows = table.select("tr");
        for (Element row : rows) {
            String code = null;
            String description = null;
            for(Node node : row.childNodes()) {
                if (node instanceof Element) {
                    if (((Element)node).hasAttr("id")) {
                        code = ((Element)node).text();
                    }
                    if (((Element)node).hasClass("alignLeft")) {
                        description = ((Element)node).text();
                    }
                    if (code != null && description != null) {
                        currencies.put(code, new Currency(code, description));
                        break;
                    }
                }
            }
        }
        currencies.put("LTL", new Currency("LTL", "Lithuanian litas"));
        currencies.put("LVL", new Currency("LVL", "Latvian lats"));
        currencies.put("ISK", new Currency("ISK", "Icelandic krona"));
        currencies.put("EEK", new Currency("EEK", "Estonian kroon"));
        currencies.put("CYP", new Currency("CYP", "Cyprus pound"));
        currencies.put("SIT", new Currency("SIT", "Slovenian tolar"));
        currencies.put("ROL", new Currency("ROL", "Romanian leu"));
        currencies.put("MTL", new Currency("MTL", "Maltese lira"));
        currencies.put("TRL", new Currency("TRL", "Turkish lira"));
        currencies.put("SKK", new Currency("SKK", "Slovak koruna"));
    }
    

    public Map<String, Currency> getCurrencies() {
        return currencies;
    }    
}
