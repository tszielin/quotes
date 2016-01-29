package tszielin.quotes.download;

import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import tszielin.quotes.aggregation.ECBAggregation;
import tszielin.quotes.domain.Currency;
import tszielin.quotes.domain.ECBQuote;
import tszielin.quotes.download.reader.ECBCurrencyReader;
import tszielin.quotes.download.reader.ECBReader;
import tszielin.quotes.repository.ECBQuoteRepository;

@Service("ecb-downloader")
public class ECBDownloader extends QuoteDownloader {
    private static final Log logger = LogFactory.getLog(ECBDownloader.class);

    private static final String URL = "https://www.ecb.europa.eu/stats/eurofxref/eurofxref-hist.xml";

    @Autowired
    private ECBQuoteRepository quotes;
    
    @Autowired
    private ECBAggregation aggregation;
    
    protected ECBAggregation getAggregation() {
        return aggregation;
    }

    @Scheduled(cron = "0 45 16 * * MON-FRI")
    public void upload() {
        final Map<String, DateRange> map = getDateRange();
                
        List<ECBQuote> list = null;
        Map<String, Currency> currencies = new ECBCurrencyReader().getCurrencies();
        try {
            list = new ECBReader(new URL(URL)) {
                private static final long serialVersionUID = -8637481591191856401L;

                @Override
                public ECBQuote readExchange(HierarchicalConfiguration hc, DecimalFormat df, String tableId,
                        Date publicated) throws ParseException {
                    if (publicated == null) {
                        return null;
                    }
                    String code = hc.getString("[@currency]");
                    if (map.containsKey(code)) {
                        if (publicated.getTime() <= map.get(code).getFinish().getTime() && 
                                publicated.getTime() >= map.get(code).getStart().getTime()) {
                            return null;
                        }
                    }
                    return new ECBQuote(hc.getDouble("[@rate]"), currencies.get(code), publicated);
                }
            }.getData();
        } catch (Exception ex) {
            logger.error(URL, ex);
        }
        if (list != null && !list.isEmpty()) {
            quotes.save(list);
        }
    }
}
