package tszielin.quotes.spring;

import java.sql.SQLException;

import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringContextTest {

    @Test
    public void contextTest() throws SQLException {
        try (AbstractApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] { "META-INF/spring/quotes-dao-context.xml" })) {
        }
    }
}
