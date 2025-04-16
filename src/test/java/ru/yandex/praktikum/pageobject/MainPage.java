package ru.yandex.praktikum.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import static org.junit.Assert.assertTrue;


public class MainPage {

    private WebDriver driver;
    public final String MAINPAGE_URL = "https://qa-scooter.praktikum-services.ru/";

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    //локатор кнопки "да все привыкли" в предупреждении о куках
    public By acceptCookies = By.xpath(".//button[text() = 'да все привыкли']");

    //локатор кнопки "Заказать" в шапке страницы
    public By upperOrderButton = By.xpath(".//div[@class='Header_Nav__AGCXC']/button[@class='Button_Button__ra12g']");

    //локатор кнопки "Заказать" в нижней части страницы
    public By lowerOrderButton = By.xpath(".//div[@class='Home_FinishButton__1_cWm']/button");

    //локатор строк с вопросами
    public By qestions = By.className("accordion__item");

    //локатор строк с ответами
    public By answers = By.xpath(".//div[@class='accordion__panel']/p");


    //метод кликает на кнопку "да все привыкли" в предупреждении о куках
    public void clickAcceptCookies() {
        driver.findElement(acceptCookies).click();
    }
    //метод кликает на кнопку "Заказать" в шапке страницы
    public void clickUpperOrderButton() {
        driver.findElement(upperOrderButton).click();
    }

    //метод кликает на кнопку "Заказать" в нижней части страницы
    public void clickLowerOrderButton() {
        driver.findElement(acceptCookies).click();
        driver.findElement(lowerOrderButton).click();
    }

    //метод получает строку с ответом в соответствии с индексом строки
    public WebElement getAnswer(int index) {
        List<WebElement> questions = driver.findElements(qestions);

        //Проверка, что строка с таким индексом представлена на странице
        assertTrue("Строки с таким номером нет в списке", (questions.size() >= index));

        // кликам на нужной строке
        questions.get(index).click();

        // получаем строку с ответом
      return questions.get(index).findElement(answers);
    }
}
