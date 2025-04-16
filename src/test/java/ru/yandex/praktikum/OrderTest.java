package ru.yandex.praktikum;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.praktikum.pageobject.MainPage;
import ru.yandex.praktikum.pageobject.OrderPage;
import ru.yandex.praktikum.pageobject.RentInfoPage;
import java.time.Duration;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;


@RunWith(Parameterized.class)
public class OrderTest {
    private WebDriver driver;
    private BasicUtils utils;

    private String Name;
    private String surname;
    private String adress;
    private String station;
    private String phone;
    private String dateOrder;
    private int periodOfOrder;
    private String color;
    private String comment;

    public OrderTest(String Name, String surname, String adress, String station, String phone, String dateOrder, int periodOfOrder, String color, String comment) {
        this.Name = Name;
        this.surname = surname;
        this.adress = adress;
        this.station = station;
        this.phone = phone;
        this.dateOrder = dateOrder;
        this.periodOfOrder = periodOfOrder;
        this.color = color;
        this.comment = comment;
    }

    @Parameterized.Parameters(name = "Тест {0}")
    public static Object[][] setOrder() {
        return new Object[][]{
                {"Мария", "Иванова", "Уличная", "Черкизовская", "82345678912", "15.04.2025", 1, "чёрный жемчуг", "Т"},
                {"МарияМарияМария", "ИвановаИвановаИ", "Уличн", "Серпуховская", "+71234567891", "20.04.2025", 7, "серая безысходность", "Тутбудеткомментарий"},
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
    public void OrderInfoTestWithUpperOrderButton() {
        MainPage objMainPage = new MainPage(driver);
        driver.get(objMainPage.MAINPAGE_URL);
        objMainPage.clickUpperOrderButton();
        OrderPage objOrderPage = new OrderPage(driver);

        //заполняем страницу "Для кого самокат"
        objOrderPage.fillOrderInfo(Name, surname, adress, station, phone);

        //кликаем на кнопку "Далее"
        objOrderPage.clickAheadButton();


        RentInfoPage objRentInfoPage = new RentInfoPage(driver);

        //заполняем страницу "Про аренду"
        objRentInfoPage.fillRentInfo(dateOrder, periodOfOrder, color, comment);

        //кликаем на кнопку "Заказать" для оформления заказа
        objRentInfoPage.clickLowerOrderButtton();

        //кликаем на кнопку "Да" в появившемся окне
        objRentInfoPage.clickYesButton();

        //проверяем, что появилось сообщение об успешном заказе
        String textOrderSuccess = objRentInfoPage.getOrderSuccessText();
        assertThat("Сообщение об успешном заказе отсутствует", textOrderSuccess, containsString("Заказ оформлен"));

    }

    @Test
    public void OrderInfoTestWithLowerOrderButton() {
        MainPage objMainPage = new MainPage(driver);
        driver.get(objMainPage.MAINPAGE_URL);
        objMainPage.clickLowerOrderButton();
        OrderPage objOrderPage = new OrderPage(driver);

        //заполняем страницу "Для кого самокат"
        objOrderPage.fillOrderInfo(Name, surname, adress, station, phone);

        //кликаем на кнопку "Далее"
        objOrderPage.clickAheadButton();

        RentInfoPage objRentInfoPage = new RentInfoPage(driver);

        //заполняем страницу "Про аренду"
        objRentInfoPage.fillRentInfo(dateOrder, periodOfOrder, color, comment);

        //кликаем на кнопку "Заказать" для оформления заказа
        objRentInfoPage.clickLowerOrderButtton();

        //кликаем на кнопку "Да" в появившемся окне
        objRentInfoPage.clickYesButton();

        //проверяем, что появилось сообщение об успешном заказе
        String textOrderSuccess = objRentInfoPage.getOrderSuccessText();
        assertThat("Сообщение об успешном заказе отсутствует", textOrderSuccess, containsString("Заказ оформлен"));
    }

    @After
    public void tearDown() {
        utils.tearDown();
    }
}