package robotricochet.config;

import java.util.Properties;

public class PropertiesSingleton {

    private static class SingletonHolder {
        public static final Properties instance = Configuration.fetchProperties();
    }

    public static Properties getInstance() {
        return SingletonHolder.instance;
    }
}
