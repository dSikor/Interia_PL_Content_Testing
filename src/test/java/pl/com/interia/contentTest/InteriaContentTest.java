package pl.com.interia.contentTest;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pl.com.interia.webObject.CreateNewAccountPage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.time.Duration;

public class InteriaContentTest {
    String homePageAddress = "https://www.interia.pl/";
    String loginUserPage = "https://poczta.interia.pl/logowanie/";
    String registrationNewUserPage = "https://konto-pocztowe.interia.pl/#/nowe-konto/darmowe";
    String driverPath="C:/Selenium drivers/chromedriver.exe";
    WebDriver driver;
    CreateNewAccountPage objCreateNewAccountPage;
    List<String> newsTitles;
    List<String> newsTitlesGetFromWeb;
    Workbook workbook;
    Sheet sheet;
    @BeforeTest
    public void beforeTest() throws IOException {

        newsTitles = new ArrayList<>();
        newsTitlesGetFromWeb=new ArrayList<>();

        try (FileInputStream file = new FileInputStream(new File("C:\\Users\\Damiano\\IdeaProjects\\InteriaPLContentTest\\src\\test\\java\\pl\\com\\interia\\contentTest\\News.xlsx")))
        {
            workbook = new XSSFWorkbook(file);
        }
        sheet = workbook.getSheetAt(0);
        for (Row row : sheet) {
            newsTitles.add(row.getCell(0).toString());
        }
        newsTitles.remove(0);

        System.setProperty("webdriver.chrome.driver", driverPath);

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver=new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get(homePageAddress);
    }
    @Test(priority = 1)
    public void showCookiesInfoTest(){
        //******************************************************************//
        //Check if cookies message showed
        boolean isCookieWindowsDisplayed=driver.findElement(By.className("rodo-popup-agree")).isDisplayed();
        Assert.assertTrue(isCookieWindowsDisplayed);
        driver.findElement(By.className("rodo-popup-agree")).click();
    }
    @Test(priority = 2)
    public void checkNewsUpToDateTest(){
        //******************************************************************//
        //Checking if the news is up to date
        List<WebElement> listOfTitleNews = driver.findElements(By.cssSelector("li.wiadspec-li > a > span.tile-span > span"));
        listOfTitleNews.forEach(e->{
            newsTitlesGetFromWeb.add(e.getText());
        });
        newsTitles.removeAll(newsTitlesGetFromWeb);
        Assert.assertTrue(newsTitles.isEmpty());
//        if (newsTitles.isEmpty()){
//            System.out.println("TEST 2 - Newsy zgodnie z plikem exel");
//            System.out.println("TEST 2 - Tytu?? OK");
//        }
//        else {
//            System.out.println("TEST 2 - Tytu?? NIEZGODNE!!!");
//        }
//        //*******************************************************************//
    }
    @Test(priority = 3)
    public void canNotLoginWithRandomCredential(){
        // Checking log in and log out
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get(loginUserPage);
//        driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/header/nav/ul/li[1]/a")).click();
        driver.manage().window().maximize();
        driver.findElement(By.id("email")).sendKeys("sdasdas@interia.pl");
        driver.findElement(By.id("password")).sendKeys("dsasadasd");
        driver.findElement(By.xpath("//*[@id=\"sitebar\"]/form/button")).click();
        WebElement errorPage = driver.findElement(By.className("form__error"));
        String errorMessage = errorPage.getText();
        assertEquals(errorMessage, "B????dny e-mail lub has??o");


        //******************************************************************//
    }
    @Test(priority = 4)
    public void canLoginWithCorrectCredential(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get(loginUserPage);
        driver.manage().window().maximize();
        driver.findElement(By.id("email")).sendKeys("adam.jan44@interia.pl");
        driver.findElement(By.id("password")).sendKeys("Janowo45#@1m");
        driver.findElement(By.xpath("//*[@id=\"sitebar\"]/form/button")).click();
        String actualUrl="https://poczta.interia.pl/logowanie/";
        String expectedUrl= driver.getCurrentUrl();
        Assert.assertEquals(expectedUrl,actualUrl);
    }
    @Test(priority = 5)
    public void registrationUser(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get(registrationNewUserPage);
        objCreateNewAccountPage=new CreateNewAccountPage(driver);
        objCreateNewAccountPage.setUserName("Franek");
        objCreateNewAccountPage.setUserSurname("Kimono");
        objCreateNewAccountPage.setAccountName("franek.kimono333");
        objCreateNewAccountPage.setUserPassword("dsadsadsad22312321");
        objCreateNewAccountPage.setRepetedPassword("dsadsadsad22312321");
        objCreateNewAccountPage.setUserBirthdayDay(22);
        objCreateNewAccountPage.setUserBirthdayYear(1977);
    }
}
