package ru.yandex.praktikum.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPage {

    private WebDriver driver;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    //локатор поля "Имя"
    private By nameField = By.xpath(".//input[@placeholder = '* Имя']");

    //локатор поля "Фамилия"
    private By surnameField = By.xpath(".//input[@placeholder = '* Фамилия']");

    //локатор поля "Адрес"
    private By adressField = By.xpath(".//input[@placeholder = '* Адрес: куда привезти заказ']");

    //локатор поля "Станция метро"
    private By stationField = By.className("select-search__input");

    //локатор списка станций
    private By stationList = By.className("select-search__select");

    //локатор строки с названием станции
    private By stationRow = By.className("select-search__row");

    //локатор поля "Телефон"
    private By phoneNumberField = By.xpath(".//input[@placeholder = '* Телефон: на него позвонит курьер']");

    //локатор кнопки "Далее"
    private By aheadButton = By.xpath(".//button[text() = 'Далее']");

    //метод заполняет поле "Имя"
    public void fillnameField(String name){
        driver.findElement(nameField).sendKeys(name);
    }

    //метод заполняет поле "Фамилия"
    public void fillsurnameField(String surname){
        driver.findElement(surnameField).sendKeys(surname);
    }

    //метод заполняет поле "Адрес"
    public void filladressField (String adress){
        driver.findElement(adressField).sendKeys(adress);
    }

    //метод заполняет поле "Станция метро"
    public void fillstationField (String station){
        driver.findElement(stationField).sendKeys(station);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until(ExpectedConditions.visibilityOfElementLocated(stationList));
        driver.findElement(stationRow).click();
    }

    //метод заполняет поле "Телефон"
    public void fillPhoneNumberField (String phone){
        driver.findElement(phoneNumberField).sendKeys(phone);
    }

    //метод кликает на кнопку "Заказать" в нижней части страницы
    public void clickAheadButton(){
        driver.findElement(aheadButton).click();
    }

    //шаг с заполнением всех полей на странице
    public void fillOrderInfo(String name, String surname, String adress, String station, String phone){
        fillnameField(name);
        fillsurnameField(surname);
        filladressField(adress);
        fillstationField(station);
        fillPhoneNumberField(phone);
    }
}
