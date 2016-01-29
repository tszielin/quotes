package tszielin.quotes.download;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import tszielin.quotes.aggregation.FEDAggregation;
import tszielin.quotes.domain.FEDQuote;
import tszielin.quotes.download.reader.FEDReader;
import tszielin.quotes.repository.FEDQuoteRepository;

@Service("fed-downloader")
public class FEDDownloader extends QuoteDownloader {
    private static final Log logger = LogFactory.getLog(FEDDownloader.class);

    private static final String URL = "http://www.federalreserve.gov/releases/h10/hist/default.htm#slref";

    @Autowired
    private FEDQuoteRepository quotes;
    
    @Autowired
    private FEDAggregation aggregation;
    
    protected FEDAggregation getAggregation() {
        return aggregation;
    }
    
    
    @Scheduled(cron = "0 0 7 * * TUE")
    public void upload() {
        List<FEDQuote> list = null;        
        FEDReader reader = new FEDReader(URL, getDateRange());
        try {
            list = reader.getData();
        } catch (Exception ex) {
            logger.error(URL, ex);
        }
        if (list != null && !list.isEmpty()) {
            quotes.save(list);
        }
    }
}
