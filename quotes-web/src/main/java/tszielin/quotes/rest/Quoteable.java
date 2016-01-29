package tszielin.quotes.rest;

import java.util.Date;
import java.util.List;

import tszielin.quotes.domain.Quote;

interface Quoteable<T extends Quote> {
    List<T> quotes(Date publicated);    
    List<T> quotes(String currency);
    Quote quote(String currency, Date publicated);
}
