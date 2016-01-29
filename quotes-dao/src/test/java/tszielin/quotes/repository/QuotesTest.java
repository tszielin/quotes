package tszielin.quotes.repository;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import tszielin.quotes.domain.Quote;
import tszielin.quotes.test.BaseTest;

abstract public class QuotesTest<T extends Quote> extends BaseTest {
    @Autowired
    protected Quotes<T> repository;

    @Test
    public void testFindByCurrencyPublicated() {
        Assert.assertNotNull(repository);
        T stored = store();
        @SuppressWarnings("unchecked")
        T saved = (T)repository.find(stored.getCurrency().getCode(), stored.getPublicated());
        Assert.assertNotNull(saved);
        Assert.assertEquals(saved, stored);
        Assert.assertEquals(saved.getRate(), stored.getRate(), 0d);
        Assert.assertEquals(saved.getCurrency(), stored.getCurrency());
        Assert.assertEquals(saved.getProvider(), stored.getProvider());
        Assert.assertEquals(saved.getPublicated(), stored.getPublicated());
        repository.delete(stored);
    }

    @Test
    public void testFindByCurrency() {
        Assert.assertNotNull(repository);
        T stored = store();
        List<T> saved = repository.find(stored.getCurrency().getCode(), new Sort(Direction.DESC));
        Assert.assertNotNull(saved);
        Assert.assertTrue(!saved.isEmpty());
        Assert.assertTrue(saved.size() == 1);
        Assert.assertEquals(saved.get(0), stored);
        Assert.assertEquals(saved.get(0).getRate(), stored.getRate(), 0d);
        Assert.assertEquals(saved.get(0).getCurrency(), stored.getCurrency());
        Assert.assertEquals(saved.get(0).getProvider(), stored.getProvider());
        Assert.assertEquals(saved.get(0).getPublicated(), stored.getPublicated());
        repository.delete(stored);
    }

    @Test
    public void testExchangesByPublicated() {
        Assert.assertNotNull(repository);
        T stored = store();
        List<T> saved = repository.find(stored.getPublicated(), new Sort(Direction.DESC));
        Assert.assertNotNull(saved);
        Assert.assertTrue(!saved.isEmpty());
        Assert.assertTrue(saved.size() == 1);
        Assert.assertEquals(saved.get(0), stored);
        Assert.assertEquals(saved.get(0).getRate(), stored.getRate(), 0d);
        Assert.assertEquals(saved.get(0).getCurrency(), stored.getCurrency());
        Assert.assertEquals(saved.get(0).getProvider(), stored.getProvider());
        Assert.assertEquals(saved.get(0).getPublicated(), stored.getPublicated());
        repository.delete(stored);
    }

    abstract protected T store();
}
