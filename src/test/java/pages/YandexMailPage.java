package pages;

import waits.Waiting;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static propertiesConstants.PropertiesReader.getProperies;
import static waits.Waiting.newMail;

/**
 * Пэйджа для страницы со входящими письмами.
 */
public class YandexMailPage extends PageFactory {
    private WebDriver driver;                                   // Инициализация локальной переменной драйвера
    private Integer quantityShipment;                           // Переменная хранит колличество входящих
    String addresseeButtonConst = "//div[contains(@class, \"tst-field-to\")]/descendant::div[@class=\"composeYabbles\"]";
    String backToMailButtonConst = "//*[@class=\"ComposeDoneScreen-Actions\"]";
    String messageButtonLocator = "//a[@class=\"mail-ComposeButton js-main-action-compose\"]";
    String mailsLocator = "//span[@title=\"Simbirsoft theme\"]";
    @FindBy(xpath = "//a[@class=\"mail-ComposeButton js-main-action-compose\"]")
    private WebElement messageButton;
    @FindBy(xpath = "//div[contains(@class, \"tst-field-to\")]/descendant::div[@class=\"composeYabbles\"]")
    private WebElement addresseeButton;
    @FindBy(xpath = "//div[@class=\"compose-LabelRow-Content ComposeSubject-Content\"]/descendant::input")
    private WebElement themeMail;
    @FindBy(xpath = "//*[@id=\"cke_1_contents\"]/div")
    private WebElement textMessageButton;
    @FindBy(xpath = "//button[contains(@class,  \"Button2_view_default Button2_size_l\")]")
    private WebElement sendMailButton;
    @FindBy(xpath = "//*[@class=\"svgicon svgicon-mail--ComposeButton-Refresh\"]")
    private WebElement reloadButton;
    @FindBy(xpath = "//*[@class=\"ComposeDoneScreen-Actions\"]")
    private WebElement backToMailButton;

    /**
     * Конструктор для пэйджи
     *
     * @param driver веб драйвер
     */
    public YandexMailPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Метод сначала проверяет наличие писем с темой "Simbirsoft theme", после этого считает их всех
     *
     * @return возвращает количество писем
     * @throws Exception
     */
    public Integer getNumberEmailsBefore() throws Exception {
        newMail(mailsLocator, driver).isDisplayed(); // проверяю что загрузились письма
        quantityShipment = driver.findElements(By.xpath(mailsLocator)).size(); // считаю количество писем
        return quantityShipment; // возвращает количество писем
    }

    /**
     * Проверяет есть ли кнопка написания письма, если есть то кликает на неё
     *
     * @throws Exception
     */
    public void openMessageForme() throws Exception {
        newMail(messageButtonLocator, driver).isDisplayed(); // проверка на наличие
        messageButton.click();// клик
    }

    /**
     * Проверяет наличие поля для ввода почты куда отправить, если есть, то заполняет кому отправить, тему отправления и текст письма
     *
     * @throws Exception
     */
    public void writeMessage() throws Exception {
        newMail(addresseeButtonConst, driver).isDisplayed();// проверка на наличие поля
        addresseeButton.sendKeys(getProperies("emailLogin")); // ввод почты куда отправить
        themeMail.click();
        themeMail.sendKeys(getProperies("simbirsoftTheme")); // ввод темы письма
        textMessageButton.click();
        textMessageButton.sendKeys("Найдено " + quantityShipment + " писем\\ьма"); // текст письма. переменная - число входящих писем с темой "Simbirsoft theme"
    }

    /**
     * Отправляет письмо
     *
     * @throws Exception
     */
    public void sendMessage() {
        sendMailButton.click();
    }

    /**
     * Возвращает ко входящим письмам
     *
     * @throws Exception
     */
    public void buttonClickToBackToMail() throws Exception {
        newMail(backToMailButtonConst, driver).isDisplayed();
        backToMailButton.click();
    }

    /**
     * перезагружает список входящих
     */
    public void reloadPage() {
        reloadButton.click();
    }

    /**
     * Записывает xPath для только что отправленного письма
     * Проверяет что оно пришло по xPath
     * Если пришло, то считает общее число писем с темой "Simbirsoft theme"
     *
     * @return Возвращает число писем с темой "Simbirsoft theme"
     * @throws Exception
     */
    public Integer getNumberEmailsAfter() throws Exception {
        String xPathNewMail = "//*[text() = \"Найдено " + quantityShipment + " писем\\ьма\"]"; // составляет xPath
        newMail(xPathNewMail, driver).isDisplayed();// проверяет наличие нового письма
        return quantityShipment = driver.findElements(By.xpath(mailsLocator)).size(); // считает общее число писем
    }
}