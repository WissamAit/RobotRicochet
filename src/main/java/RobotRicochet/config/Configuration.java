package RobotRicochet.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {

    public static Properties fetchProperties() throws IOException {

        Properties prop = new Properties();
        String fileName = "application.properties";
        InputStream inputStream = Configuration.class.getClassLoader().getResourceAsStream(fileName);

        if (inputStream != null) {
            prop.load(inputStream);
        } else {
            throw new FileNotFoundException("property file '" + fileName + "' not found in the classpath");
        }


        return prop;
    }
}
