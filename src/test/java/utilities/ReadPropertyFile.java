package utilities;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Properties;

public class ReadPropertyFile {
    public static void main(String[] args) throws FileNotFoundException {
        FileReader reader = new FileReader("src/test/resources/configfiles/config.properties");
        Properties p = new Properties();
        try {
            p.load(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(p.getProperty("browser"));
        System.out.println(p.getProperty("testUrl"));
    }
}
