package tszielin.quotes.web.spring;

import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringContextTest {

    @Test
    public void contextTest() throws Exception {
        try (AbstractApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] { 
                        "META-INF/spring/quotes-web-context.xml",
                        "META-INF/spring/quotes-security.xml"
                        })) {
        }
    }
}
