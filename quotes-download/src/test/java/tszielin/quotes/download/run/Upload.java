package tszielin.quotes.download.run;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import tszielin.quotes.download.Uploadable;

class Upload {
    private ApplicationContext appContext;

    protected void upload(Class<?> cls) {
        appContext = new ClassPathXmlApplicationContext("META-INF/spring/quotes-download-context.xml");
        Object obj = appContext.getBean(cls);
        if (obj instanceof Uploadable) {
            ((Uploadable)obj).upload();
        }
    }
}
