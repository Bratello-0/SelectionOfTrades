import org.example.selectionOfTrades.configs.Config;
import org.example.selectionOfTrades.models.parser.ParserSteam;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class TestMain {
    public static void main(String[] args) throws IOException {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(Config.class);

        ParserSteam parserSteam = context.getBean(ParserSteam.class);
    }
}
