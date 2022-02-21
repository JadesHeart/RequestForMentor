package getchromedriver;

import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Save data for WebDriver
 */
public class GetChromeDriver {
    /**
     * Directory chromedriver: \webDriver\chromedriver\chromedriver.exe
     * For ChromeVersion 97.0.4692.71
     *
     * @return new ChromeDriver
     */
    public static ChromeDriver getchromedriver() {
        String path = System.getProperty("user.dir");
        String chromeDriverPath = path + "\\webDriver\\chromedriver\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        return new ChromeDriver();
    }
}
