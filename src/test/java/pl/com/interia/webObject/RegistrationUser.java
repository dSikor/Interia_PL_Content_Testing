package pl.com.interia.webObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class RegistrationUser {

    public static WebDriver driver;
    public String baseURL="https://konto-pocztowe.interia.pl/#/nowe-konto";
    public String userName="";
    public String userSurname="";
    public int userDayBorn=0;
    public int userMonthBorn=0;
    public int userYearBorn=0;
    public String accountName="";
    public String password="";

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
