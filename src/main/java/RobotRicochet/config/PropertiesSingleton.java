package RobotRicochet.config;

import java.io.IOException;
import java.util.Properties;

public class PropertiesSingleton {

    private PropertiesSingleton(){}

    private static class SingletonHolder {


         final Properties instance;
            try {
                instance = Configuration.fetchProperties();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    public static Properties getInstance() {
        return SingletonHolder.instance;
    }
}
