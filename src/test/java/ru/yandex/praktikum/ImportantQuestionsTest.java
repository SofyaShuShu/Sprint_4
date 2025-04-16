package ru.yandex.praktikum;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.praktikum.pageobject.MainPage;
import java.time.Duration;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ImportantQuestionsTest {
    private WebDriver driver;
    private BasicUtils utils;

    private final int INDEX_OF_ANSWER;
    private final String EXPECTED_ANSWER;

    private static final String COST_ANSWER = "Сутки — 400 рублей. Оплата курьеру — наличными или картой.";
    private static final String SCOOTER_COUNT_ANSWER = "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.";
    private static final String RENTAL_TIME_ANSWER = "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.";
    private static final String ORDER_TIME_ANSWER = "Только начиная с завтрашнего дня. Но скоро станем расторопнее.";
    private static final String ORDER_EXTENSION_ANSWER = "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.";
    private static final String CHARGING_ANSWER = "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.";
    private static final String CANCELLATION_ORDER_ANSWER = "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.";
    private static final String MOSCOW_REGION_ORDER_ANSWER = "Да, обязательно. Всем самокатов! И Москве, и Московской области.";

    private static final int INDEX_OF_COST_ANSWER = 0;
    private static final int INDEX_OF_SCOOTER_COUNT_ANSWER = 1;
    private static final int INDEX_OF_RENTAL_TIME_ANSWER = 2;
    private static final int INDEX_OF_ORDER_TIME_ANSWER = 3;
    private static final int INDEX_OF_ORDER_EXTENSION_ANSWER = 4;
    private static final int INDEX_OF_CHARGING_ANSWER = 5;
    private static final int INDEX_OF_CANCELLATION_ORDER_ANSWER = 6;
    private static final int INDEX_OF_MOSCOW_REGION_ORDER_ANSWER = 7;


    public ImportantQuestionsTest(int INDEX_OF_ANSWER, String EXPECTED_ANSWER) {
        this.INDEX_OF_ANSWER = INDEX_OF_ANSWER;
        this.EXPECTED_ANSWER = EXPECTED_ANSWER;
    }

    @Parameterized.Parameters(name = "Тест строки с индексом {0}")
    public static Object[][] setRows() {
        return new Object[][]{
                {INDEX_OF_COST_ANSWER, COST_ANSWER},
                {INDEX_OF_SCOOTER_COUNT_ANSWER, SCOOTER_COUNT_ANSWER},
                {INDEX_OF_RENTAL_TIME_ANSWER, RENTAL_TIME_ANSWER},
                {INDEX_OF_ORDER_TIME_ANSWER, ORDER_TIME_ANSWER},
                {INDEX_OF_ORDER_EXTENSION_ANSWER, ORDER_EXTENSION_ANSWER},
                {INDEX_OF_CHARGING_ANSWER, CHARGING_ANSWER},
                {INDEX_OF_CANCELLATION_ORDER_ANSWER, CANCELLATION_ORDER_ANSWER},
                {INDEX_OF_MOSCOW_REGION_ORDER_ANSWER, MOSCOW_REGION_ORDER_ANSWER},
        };
    }

    @Before
    public void StartUp() {
        driver = new ChromeDriver();
        //driver = new FirefoxDriver();
        utils = new BasicUtils(driver);
        utils.StartUp();
    }

    @Test
    public void testRows() {
        MainPage objMainPage = new MainPage(driver);
        driver.get(objMainPage.MAINPAGE_URL);
        objMainPage.clickAcceptCookies();

        WebElement answerElement = objMainPage.getAnswer(INDEX_OF_ANSWER);
        String actualText = answerElement.getText();
        assertEquals("Неверный текст в строке с индексом " + INDEX_OF_ANSWER, EXPECTED_ANSWER, actualText);
    }


    @After
    public void tearDown() {
        utils.tearDown();
    }
}
