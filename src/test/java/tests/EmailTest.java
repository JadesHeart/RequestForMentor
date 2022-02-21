package tests;

import org.openqa.selenium.WebDriver;
import getchromedriver.GetChromeDriver;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.AuthorizationPage;
import pages.YandexMailPage;
import propertiesConstants.PropertiesReader;

/**
 * Класс с тестами
 */
public class EmailTest {
    WebDriver driver = GetChromeDriver.getchromedriver();                   // Инициализация драйвера
    AuthorizationPage authorizationPage = new AuthorizationPage(driver);    // Вызов пэйджи авторизации
    YandexMailPage yandexMailPage = new YandexMailPage(driver);             // Вызов пэйджи входящих
    PropertiesReader propertiesReader = new PropertiesReader();             // Вызов проперти

    /**
     * Расширяю экран и перехожу в mail.yandex.ru
     * @throws Exception
     */
    @BeforeTest
    public void startBrowser() throws Exception {
        driver.manage().window().maximize();
        driver.get(propertiesReader.getProperies("baseURL"));
    }

    /**
     * 1) Вызов метода для авторизации
     * 2) Записываю в numbersBefore число писем с темой "Simbirsoft theme" до отправления
     * 3) Открываю форму для написания письма
     * 4) Пишу письмо
     * 5) Отправляю письмо
     * 6) Возвращаюсь во входящие
     * 7) Обновляю страницу
     * 8) Записываю в numbersAfter новое число писем
     * 9) Нахожу раздницу до отправления и после
     * 10) Преобразую раздницу из инт в строку
     * 11) Проверяю Ассёртом что число писем увеличилось на 1
     * @throws Exception
     */
    @Test
    public void checkNumberEmails() throws Exception {
        authorizationPage.authorization();
        Integer numbersBefore = yandexMailPage.getNumberEmailsBefore();
        yandexMailPage.openMessageForme();
        yandexMailPage.writeMessage();
        yandexMailPage.sendMessage();
        yandexMailPage.backToMail();
        yandexMailPage.reloadPage();
        Integer numbersAfter = yandexMailPage.getNumberEmailsAfter();
        Integer differenceNumberEmails = numbersAfter - numbersBefore;
        String i = Integer.toString(differenceNumberEmails);
        Assert.assertEquals(i, "1", "Раздница между входящеми до отправки не равна 1");
    }

    /**
     * Закрываю браузер
     */
    @AfterTest
    public void closeBrowser() {
        driver.quit();
    }
}
