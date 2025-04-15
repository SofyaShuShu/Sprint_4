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

    private final int INDEXOfROW;
    private final String EXPECTED_STRING;

    private static final String EXPECTED_STRING_1 = "Сутки — 400 рублей. Оплата курьеру — наличными или картой.";
    private static final String EXPECTED_STRING_2 = "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.";
    private static final String EXPECTED_STRING_3 = "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.";
    private static final String EXPECTED_STRING_4 = "Только начиная с завтрашнего дня. Но скоро станем расторопнее.";
    private static final String EXPECTED_STRING_5 = "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.";
    private static final String EXPECTED_STRING_6 = "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.";
    private static final String EXPECTED_STRING_7 = "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.";
    private static final String EXPECTED_STRING_8 = "Да, обязательно. Всем самокатов! И Москве, и Московской области.";

    private static final int INDEXOfROW1 = 0;
    private static final int INDEXOfROW2 = 1;
    private static final int INDEXOfROW3 = 2;
    private static final int INDEXOfROW4 = 3;
    private static final int INDEXOfROW5 = 4;
    private static final int INDEXOfROW6 = 5;
    private static final int INDEXOfROW7 = 6;
    private static final int INDEXOfROW8 = 7;


    public ImportantQuestionsTest(int INDEXOfROW, String EXPECTED_STRING) {
        this.INDEXOfROW = INDEXOfROW;
        this.EXPECTED_STRING = EXPECTED_STRING;
    }

    @Parameterized.Parameters(name = "Тест строки с индексом {0}")
    public static Object[][] setRows() {
        return new Object[][]{
                {INDEXOfROW1, EXPECTED_STRING_1},
                {INDEXOfROW2, EXPECTED_STRING_2},
                {INDEXOfROW3, EXPECTED_STRING_3},
                {INDEXOfROW4, EXPECTED_STRING_4},
                {INDEXOfROW5, EXPECTED_STRING_5},
                {INDEXOfROW6, EXPECTED_STRING_6},
                {INDEXOfROW7, EXPECTED_STRING_7},
                {INDEXOfROW8, EXPECTED_STRING_8},
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

        WebElement answerElement = objMainPage.getAnswer(INDEXOfROW);
        String actualText = answerElement.getText();
        assertEquals("Неверный текст в строке с индексом " + INDEXOfROW, EXPECTED_STRING, actualText);
    }

    @After
    public void tearDown() {
        utils.tearDown();
    }
}
