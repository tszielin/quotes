package tszielin.quotes.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import tszielin.quotes.domain.NBPParameter;

public class ParameterTest {
    private NBPParameter parameter;
    
    @Before
    public void setUp() {
        parameter = new NBPParameter();
    }

    @Test
    public void testTableId() {
        assertEquals(1, parameter.getTableId());
        assertNotEquals(0, parameter.getTableId());
    }

    @Test
    public void testPublicated() {
        assertNotNull(parameter.getPublicated());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(parameter.getPublicated());
        assertEquals(2, calendar.get(Calendar.DAY_OF_MONTH));
        assertEquals(Calendar.JANUARY, calendar.get(Calendar.MONTH));
        assertEquals(2002, calendar.get(Calendar.YEAR));
    }

    @Test
    public void testURL() {
        assertNotNull(parameter.getURL());
        assertEquals("http://www.nbp.pl/kursy/xml/a001z020102.xml", parameter.getURL());
    }

    @Test
    public void testNext() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(parameter.getPublicated());
        NBPParameter next = parameter.next(false);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        assertEquals(calendar.getTime(), parameter.getPublicated());
        assertEquals(1, next.getTableId());
        assertEquals("http://www.nbp.pl/kursy/xml/a001z020103.xml", next.getURL());
        next = next.next(true);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        assertEquals(calendar.getTime(), parameter.getPublicated());
        assertEquals(2, next.getTableId());
        assertEquals("http://www.nbp.pl/kursy/xml/a002z020104.xml", next.getURL());
    }
}
