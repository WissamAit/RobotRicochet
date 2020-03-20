package RobotRicochet.config;

import org.junit.Test;

import java.io.IOException;
import java.util.Properties;

public class ConfigurationTest {



    @Test
    public void shouldReturnValuesAppPropFiles() throws IOException {
        Configuration configuration = new Configuration();
        Properties values = configuration.getValues();

        values.entrySet().forEach(System.out::println);
        String property = values.getProperty("file.plateau1");
        System.out.println(property);

    }

}