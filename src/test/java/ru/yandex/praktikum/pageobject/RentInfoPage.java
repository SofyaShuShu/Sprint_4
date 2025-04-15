package ru.yandex.praktikum.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

public class RentInfoPage {
    private WebDriver driver;

    public RentInfoPage(WebDriver driver) {
        this.driver = driver;
    }

    //локатор поля "Когда привезти самокат"
    private By calendar = By.xpath(".//div[@class = 'react-datepicker__input-container']/input");

    //локатор поля "Cрок аренды"
    private By periodOfOrderField = By.className("Dropdown-root");

    //локатор строки в списке выбора срока аренды
    private By periodOfOrderRow = By.className("Dropdown-option");

    //локатор поля "Цвет самоката" - выбор черного цвета
    private By colorFieldBlack = By.xpath(".//div[@class='Order_Checkboxes__3lWSI']/label/input[@id ='black']");
    //локатор поля "Цвет самоката" - выбор серого цвета
    private By colorFieldGrey = By.xpath(".//div[@class='Order_Checkboxes__3lWSI']/label/input[@id ='grey']");

    //локатор поля "Комментарий"
    private By commentField = By.xpath(".//div[@class='Input_InputContainer__3NykH']/input[@class='Input_Input__1iN_Z Input_Responsible__1jDKN']");

    //локатор кнопки "Заказать" в нижней части страницы
    private By lowerOrderButtton = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[text() = 'Заказать']" );

    //локатор окна заголовка окна "Хотите оформить заказ?"
    private By orderConfirmationWindow = By.className("Order_ModalHeader__3FDaJ");

    //локатор кнопки "Да" в окне "Хотите оформить заказ?"
    private By yesButton = By.xpath(".//button[text() = 'Да']");

    //локатор модального окна "Заказ оформлен"
    private By orderSuccessWindow = By.className("Order_Modal__YZ-d3");

    //метод заполняет поле "Когда привезти самокат"
    public void fillcalendar (String dateOrder){
        driver.findElement(calendar).sendKeys(dateOrder + Keys.ENTER);
    }

    //метод заполняет поле "Срок аренды"
    public void fillPeriodOfOrderField(int periodOfOrder){
        driver.findElement(periodOfOrderField).click();
        List<WebElement> orderPeriod = driver.findElements(periodOfOrderRow);

        //проверяем, что значение periodOfOrder не превышает длину списка на странице
        assertTrue("Такого значения нет в списке", (orderPeriod.size() >= periodOfOrder));

        //если значение periodOfOrder корректно, устанавливаем нужный элемент в списке
            if(periodOfOrder == 1) {
                orderPeriod.get(0).click();
            }
            else if (periodOfOrder == 2) {
                orderPeriod.get(1).click();
            }
            else if (periodOfOrder == 3) {
                orderPeriod.get(2).click();
            }
            else if (periodOfOrder == 4) {
                orderPeriod.get(3).click();
            }
            else if (periodOfOrder == 5) {
                orderPeriod.get(4).click();
            }
            else if (periodOfOrder == 6) {
                orderPeriod.get(5).click();
            }
            else if (periodOfOrder == 7) {
                orderPeriod.get(6).click();
            }
    }

    //метод заполняет поле "Цвет самоката"
    public void fillColorField(String color){
        if(color.equals("чёрный жемчуг")){
            driver.findElement(colorFieldBlack).click();
        }
        else{
            driver.findElement(colorFieldGrey).click();
        }
    }
    //метод заполняет поле "Комментарий"
    public void fillCommentField(String comment){
        driver.findElement(commentField).sendKeys(comment);
    }

    //метод кликает на кнопку "Заказать" внизу страницы
    public void clickLowerOrderButtton(){
        driver.findElement(lowerOrderButtton).click();
    }

    //метод кликает на кнопку "Да" в окне "Хотите оформить заказ?"
    public void clickYesButton(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until(ExpectedConditions.visibilityOfElementLocated(orderConfirmationWindow));
        driver.findElement(yesButton).click();
    }

    //метод получает текст окна об успешном заказе
    public String getOrderSuccessText(){
        return driver.findElement(orderSuccessWindow).getText();
    }

    //шаг с заполнением всех полей на странице
    public void fillRentInfo(String dateOrder, int periodOfOrder, String color, String comment){
        fillcalendar(dateOrder);
        fillPeriodOfOrderField(periodOfOrder);
        fillColorField(color);
        fillCommentField(comment);
    }
}
