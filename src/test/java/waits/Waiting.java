package waits;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Класс для явного ожидания
 * Ждёт в течении 20 секунд что элемент появится на странице
 * Для этого нужно передать драйвер и локатор элемента
 */
public class Waiting {
    /**
     * Вызываю класс WebDriverWait и засовываю его в переменную wait
     * Передаю драйвер и время для проверки
     *
     * @param locator локатор элемента
     * @param driver  драйвер
     * @return Возвращает найденный элемент, если он появился на странице
     */
    public WebElement newMail(String locator, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
    }

}
