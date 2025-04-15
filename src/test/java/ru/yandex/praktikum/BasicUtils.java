package ru.yandex.praktikum;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class BasicUtils {
    private WebDriver driver;

    public BasicUtils(WebDriver driver){
        this.driver = driver;
    }

    public void StartUp(){
        WebDriverManager.chromedriver().setup();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
    }

    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}