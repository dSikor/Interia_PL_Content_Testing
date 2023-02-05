package pl.com.interia.contentTest;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InteriaContentTest {
    WebDriver driver;
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

        System.setProperty("webdriver.chrome.driver", "C:/Selenium drivers/chromedriver.exe");
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.interia.pl/");
    }
    @AfterTest
    public void afterTest(){

    }
    @Test(priority = 1)
    public void showCookiesInfoTest(){
        //******************************************************************//
        //Check if cookies message showed
        if(driver.findElement(By.className("rodo-popup-agree")).isDisplayed())
        {
            driver.findElement(By.className("rodo-popup-agree")).click();
            System.out.println("TEST 1 - Okno z informacją o plikach cookies");
            System.out.println("TEST 1 - OK ");
        }
        //******************************************************************//
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
        if (newsTitles.isEmpty()){
            System.out.println("TEST 2 - Newsy zgodnie z plikem exel");
            System.out.println("TEST 2 - Tytuł OK");
        }
        //*******************************************************************//
    }
    @Test(priority = 3)
    public void logInTest(){
        // Checking log in and log out
        driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/header/nav/ul/li[1]/a")).click();
        driver.manage().window().maximize();
        driver.findElement(By.id("email")).sendKeys("cyberavr@gmail.com");
        driver.findElement(By.id("password")).sendKeys("123");
        driver.findElement(By.xpath("//*[@id=\"sitebar\"]/form/button")).click();
        //******************************************************************//
    }
}
