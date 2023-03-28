package faculty.project.configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

    private Properties prop;

    public static Config getInstance(){
        return instance;
    }
    private static Config instance = new Config();

    private Config(){
        loadConfig();
    }

    public void loadConfig(){
        prop = new Properties();
        try (InputStream input = new
                FileInputStream("config.properties")) {
            prop.load(input);
            System.out.println(prop.getProperty("db.username"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public String getDataBaseOpenMode() {
        return prop.getProperty("db.openmode");
    }
}
