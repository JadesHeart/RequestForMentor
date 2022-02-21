package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import propertiesConstants.PropertiesReader;
import waits.Waiting;

/**
 * Пэйджа для авторизации в почте
 */
public class AuthorizationPage extends PageFactory {
    private WebDriver driver;
    PropertiesReader propertiesReader = new PropertiesReader();
    Waiting waiting = new Waiting();
    String passwordLocator = "//*[@id=\"passp-field-passwd\"]";
    @FindBy(xpath = "//a[contains(@class,\"Enter with-shadow\")]")
    private WebElement buttonLogs;
    @FindBy(xpath = "//*[@id=\"passp-field-login\"]")
    private WebElement inputLogin;
    @FindBy(xpath = "//*[@id=\"passp-field-passwd\"]")
    private WebElement inputPassword;

    /**
     * Конструктор для пэйджи
     *
     * @param driver веб драйвер
     */
    public AuthorizationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Вводит логин, затем пароль от почты.
     */
    public void authorization() throws Exception {
        buttonLogs.click();
        inputLogin.sendKeys(propertiesReader.getProperies("emailLogin"));
        inputLogin.sendKeys(Keys.ENTER);
        if (waiting.newMail(passwordLocator, driver).isDisplayed()) {
            inputPassword.sendKeys(propertiesReader.getProperies("emailPassword"));
            inputPassword.sendKeys(Keys.ENTER);
        }
    }
}

