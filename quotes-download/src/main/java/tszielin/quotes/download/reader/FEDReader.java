package tszielin.quotes.download.reader;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import tszielin.quotes.domain.Currency;
import tszielin.quotes.domain.FEDQuote;
import tszielin.quotes.download.QuoteDownloader.DateRange;

public class FEDReader {
    private final String url;
    private final Map<String, DateRange> ranges;
    private Map<String, Currency> currencies = new HashMap<>();

    public FEDReader(String url, Map<String, DateRange> ranges) {
        this.url = url;
        this.ranges = ranges;
    }

    public List<FEDQuote> getData() throws IOException {
        return parse(getTables());
    }

    private Map<String, String> getTables() throws IOException {
        Elements links = Jsoup.connect(url).get().select("a[href]");
        Map<String, String> map = new HashMap<>();
        if (!links.isEmpty()) {
            for (Element element : links) {
                if (element.toString().matches(".*\"dat00_.*\\.htm\".*")) {
                    map.put(element.text(), element.absUrl("href"));
                }
            }
        }
        return map;
    }
    
    private List<FEDQuote> parse(Map<String, String> map) throws IOException {
        List<FEDQuote> quotes = new ArrayList<>();
        if (map != null && !map.isEmpty()) {
            for (String link : map.values()) {
                Document doc = Jsoup.connect(link).get();
                String code = doc.select("h3").text().trim().replace("Historical Rates for the ", "").replace("*","");
                currencies.put(code, new Currency(code, code));
                Elements table = doc.select("table[class=statistics]");
                Elements rows = table.select("tr");
                for (Element row : rows) {
                    Elements date = row.select("th[headers=a1]");
                    if (!date.isEmpty()) {
                        try {
                            Date publicated = new SimpleDateFormat("dd-MMM-yy").parse(date.text().replace(' ', '0'));
                            if (ranges.containsKey(code)) {
                                if (publicated.getTime() <= ranges.get(code).getFinish().getTime() && 
                                        publicated.getTime() >= ranges.get(code).getStart().getTime()) {
                                    continue;
                                }
                            }
                            quotes.add(
                                    new FEDQuote(Double.parseDouble(row.select("td[headers=a2 a1 r1]").text().trim()),
                                            currencies.get(code), publicated)); 
                        } catch (NumberFormatException | ParseException ignored) {
                        }
                    }
                }
            }
        }
        return quotes;
    }
}
