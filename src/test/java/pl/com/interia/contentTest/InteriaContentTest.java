package pl.com.interia.contentTest;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
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

        newsTitles = new ArrayList<String>();
        newsTitlesGetFromWeb=new ArrayList<String>();

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
        driver=new ChromeDriver();
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
        driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/header/nav/ul/li[1]/a")).click();
        driver.manage().window().maximize();
        driver.findElement(By.id("email")).sendKeys("cyberavr@gmail.com");
        driver.findElement(By.id("password")).sendKeys("123");
        driver.findElement(By.xpath("//*[@id=\"sitebar\"]/form/button")).click();
        boolean isErrorMessageDisplayed = driver.findElement(By.className("form__error")).isDisplayed();
        Assert.assertTrue(isErrorMessageDisplayed);


        //******************************************************************//
    }
    @Test(priority = 4)
    public void registrationUser(){
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
