package tszielin.quotes.rest;

import java.util.List;

import tszielin.quotes.domain.Currency;

interface Currencable<T extends Currency> {
    List<T> currencies();
    Currency currency(String currency);
}
