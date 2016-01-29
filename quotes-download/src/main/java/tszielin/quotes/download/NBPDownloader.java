package tszielin.quotes.download;

import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.configuration.HierarchicalConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import tszielin.quotes.domain.Currency;
import tszielin.quotes.domain.NBPParameter;
import tszielin.quotes.domain.NBPQuote;
import tszielin.quotes.download.reader.NBPReader;
import tszielin.quotes.repository.NBPParameterRepository;
import tszielin.quotes.repository.NBPQuoteRepository;

@Service("nbp-downloader")
public class NBPDownloader implements Uploadable {
    private static final Logger logger = LoggerFactory.getLogger(NBPDownloader.class);

    private final static String EURO = "euro";
    private final static String COUNTRY_NAME = "nazwa_kraju";
    private final static String CURRENCY_ID = "kod_waluty";
    private final static String CURRENCY_CODE = "symbol_waluty";
    private final static String CURRENCY_NAME = "nazwa_waluty";

    @Autowired
    private NBPParameterRepository parameters;
    @Autowired
    private NBPQuoteRepository quotes;

    private Map<String, Currency> currencies = new HashMap<>();

    @Scheduled(cron = "0 0/15 12-13 * * MON-FRI")
    public void upload() {
        boolean running = true;
        List<NBPQuote> list = new ArrayList<>();

        NBPParameter param = parameters.findAll().isEmpty() ? new NBPParameter() : parameters.findAll().get(0);
        while (running) {
            if (param.getPublicated().after(new java.util.Date())) {
                running = false;
                continue;
            }
            boolean exists = true;

            try {
                logger.info(param.getURL());
                list.addAll(new NBPReader(new URL(param.getURL())) {
                    private static final long serialVersionUID = -8637481591191856401L;

                    private Currency readCurrency(HierarchicalConfiguration hc) {
                        String code = hc.getString(CURRENCY_ID);
                        String description = hc.getString(COUNTRY_NAME, null) == null && hc.getInt(CURRENCY_CODE, -1) == -1 ? 
                                hc.getString(CURRENCY_NAME) : 
                                    hc.getString(COUNTRY_NAME) + " (" + hc.getInt(CURRENCY_CODE) + ")";
                        if (NBPDownloader.this.currencies.containsKey(code)) {
                            if (!"EUR".equals(code) && 
                                    !NBPDownloader.this.currencies.get(code).getDescription().equals(description)) {
                                setDescription(NBPDownloader.this.currencies.get(code), description);
                            }
                        } else {
                            NBPDownloader.this.currencies.put(code, new Currency(code, description));
                        }
                        return NBPDownloader.this.currencies.get(code);
                    }

                    @Override
                    public NBPQuote readExchange(HierarchicalConfiguration hc, DecimalFormat df, String tableId,
                            Date publicated) throws ParseException {

                        if (tableId == null || publicated == null) {
                            return null;
                        }

                        readCurrency(hc);
                        return new NBPQuote(hc.getInt("przelicznik"),
                                df.parse(hc.getString("kurs_sredni")).doubleValue(),
                                NBPDownloader.this.currencies.get(hc.getString(CURRENCY_ID)),
                                publicated, tableId);
                    }
                }.getData());
            } catch (Exception ex) {
                if (!(ex.getCause() instanceof FileNotFoundException)) {
                    logger.error(param.getURL(), ex);
                    running = false;
                    continue;
                }
                exists = false;
            }

            if (param != null) {
                param = param.next(exists);
            }
        }
        if (list != null && !list.isEmpty()) {
            if (!currencies.isEmpty()) {
                if (!currencies.get("EUR").getDescription().equals(EURO)) {
                    setDescription(currencies.get("EUR"), EURO);
                }
            }
            quotes.save(list);
        }
        parameters.save(param);
    }

    private void setDescription(Currency currency, String description) {
        try {
            Method method = Currency.class.getDeclaredMethod("setDescription", String.class);
            method.setAccessible(true);
            method.invoke(currency, description);
        } catch (Exception ex) {
            logger.warn(ex.getMessage());
        }
    }
}