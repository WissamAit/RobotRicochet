package robotricochet.config;

import org.junit.Test;

import java.util.Properties;

public class ConfigurationTest {



    @Test
    public void shouldReturnValuesAppPropFiles() {
        Properties values = Configuration.fetchProperties();

        values.entrySet().forEach(System.out::println);
        String property = values.getProperty("file.plateau1");
        System.out.println(property);

    }

}