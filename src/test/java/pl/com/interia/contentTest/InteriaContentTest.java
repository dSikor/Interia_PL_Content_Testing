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
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pl.com.interia.webObject.CreateNewAccountPage;
import pl.com.interia.webObject.LoginUserPage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.time.Duration;
import java.util.Set;
import java.util.Iterator;

public class InteriaContentTest {
    String homePageAddress = "https://www.interia.pl/";
    String loginUserPage = "https://poczta.interia.pl/logowanie/";
    String registrationNewUserPage = "https://konto-pocztowe.interia.pl/#/nowe-konto/darmowe";
    String driverPath="C:/Selenium drivers/chromedriver.exe";
    WebDriver driver;
    LoginUserPage objLoginUserPage;
    CreateNewAccountPage objCreateNewAccountPage;
    List<String> newsTitles;
    List<String> newsTitlesGetFromWeb;
    Workbook workbook;
    Sheet sheet;

    Set w;

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
        assertTrue(newsTitles.isEmpty());
//        if (newsTitles.isEmpty()){
//            System.out.println("TEST 2 - Newsy zgodnie z plikem exel");
//            System.out.println("TEST 2 - Tytuł OK");
//        }
//        else {
//            System.out.println("TEST 2 - Tytuł NIEZGODNE!!!");
//        }
//        //*******************************************************************//
    }
    @Test(priority = 3)
    public void canNotLoginWithRandomCredential(){
        // Checking log in and log out
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get(loginUserPage);
        driver.manage().window().maximize();
        objLoginUserPageFalse =new LoginUserPage(driver);
        objLoginUserPageFalse.setUserEmail("sdasdas@interia.pl");
        objLoginUserPageFalse.setUserPassword("dsasadasd");
        objLoginUserPageFalse.logIn();
        WebElement errorPage = driver.findElement(By.className("form__error"));
        String errorMessage = errorPage.getText();
        driver.quit();
        assertEquals(errorMessage, "Błędny e-mail lub hasło");
        //******************************************************************//
    }
    @Test(priority = 4)
    public void canLoginWithCorrectCredential(){
        driver.quit();
        options.addArguments("--remote-allow-origins=*");
        driver=new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get(loginUserPage);
        driver.findElement(By.className("rodo-popup-agree")).click();
        driver.manage().window().maximize();
        objLoginUserPageCorrect =new LoginUserPage(driver);
        objLoginUserPageCorrect.setUserEmail("adam.jan44@interia.pl");
        objLoginUserPageCorrect.setUserPassword("Janowo45#@1m");
        objLoginUserPageCorrect.logIn();
        String correctPartAddress="https://poczta.interia.pl/next";
        String expectedUrl= driver.getCurrentUrl();
        boolean isUrlConsist=expectedUrl.contains(correctPartAddress);
        driver.quit();
        Assert.assertTrue(isUrlConsist);
    }
    @Test(priority = 5)
    public void registrationUser() {
        options.addArguments("--remote-allow-origins=*");
        driver=new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get(registrationNewUserPage);
        driver.findElement(By.className("rodo-popup-agree")).click();
        objCreateNewAccountPage=new CreateNewAccountPage(driver);
        objCreateNewAccountPage.setUserName("Franek");
        objCreateNewAccountPage.setUserSurname("Kimono");
        objCreateNewAccountPage.setUserBirthdayDay(22);
        objCreateNewAccountPage.setUserBirthdayMonth("Grudzień");
        objCreateNewAccountPage.setUserBirthdayYear(1977);
        objCreateNewAccountPage.setAccountName("franek.kimon8889");
        objCreateNewAccountPage.setUserPassword("dsadsadsad22312321");
        objCreateNewAccountPage.setRepetedPassword("dsadsadsad22312321");
        objCreateNewAccountPage.setUserGender("Mężczyzna");
        objCreateNewAccountPage.selectAllConsent();
        objCreateNewAccountPage.createAccount();
    }
}
