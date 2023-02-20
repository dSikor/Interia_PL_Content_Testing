package pl.com.interia.webObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class RegistrationUser {

    public static WebDriver driver;
    public String baseURL="https://konto-pocztowe.interia.pl/#/nowe-konto/darmowe";
    public String userName="Franek";
    public String userSurname="Dolas";
    public int userDayBorn=23;
    public int userMonthBorn=11;
    public int userYearBorn=1991;
    public String accountName="dolek98";
    public String password="12345#$.,";
    public String gender="male";

    @BeforeClass
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "C:/Selenium drivers/chromedriver.exe");
        driver=new ChromeDriver();
    }
    @AfterClass
    public void tearDown(){
        driver.quit();
    }

}
