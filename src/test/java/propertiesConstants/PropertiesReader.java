package propertiesConstants;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * класс проперти
 */
public class PropertiesReader {

    public String getProperies(String propertiKey) throws IOException {
        FileReader reader = new FileReader("src/test/java/constant.properties"); // путь к файлу с пропертями
        Properties properties = new Properties(); // создаёт новый проперти
        properties.load(reader); // соединяет его с файлом
        return properties.getProperty(propertiKey); // возвращает значение по ключу propertiKey
    }
}
