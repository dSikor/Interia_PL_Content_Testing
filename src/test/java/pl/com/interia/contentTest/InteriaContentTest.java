package pl.com.interia.contentTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class InteriaContentTest {
    WebDriver driver;
    List<String> newsTitles;
    @BeforeTest
    public void beforeTest(){
        System.setProperty("webdriver.chrome.driver", "C:/Selenium drivers/chromedriver.exe");
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.interia.pl/");
        newsTitles = new ArrayList<String>();
        newsTitles.add("Ukraina: Rosja po raz pierwszy ostrzelała Kijów rakietami S-400");
        newsTitles.add("Kissinger w Davos: Koncepcja neutralnej Ukrainy nie ma dłużej sensu ");
        newsTitles.add("Pierwsze takie spotkanie dowódców z USA i Ukrainy. Odbyło się w Polsce");
        newsTitles.add("Greta Thunberg zatrzymana przez niemiecką policję");
        newsTitles.add("Sensacyjny początek debiutu Bereszyńskiego w Napoli!");
        newsTitles.add("Media ujawniają: Gierasimow dopuścił się zbrodni wojennej w Czeczenii");
        newsTitles.add("Miedwiediew komentuje szczyt w Davos. \"Ten polski Duda\"");
        newsTitles.add("KO: Fundacja powiązana z politykami PiS dostała 5 mln zł i kupiła willę");
        newsTitles.add("Afganistan: Publiczna chłosta na stadionie. \"Setki gapiów na trybunach\"");
    }
    @AfterTest
    public void afterTest(){

    }
    @Test
    public void testInteria(){

        //******************************************************************//
        //Check if cookies message showed
        if(driver.findElement(By.className("rodo-popup-agree")).isDisplayed())
        {
            driver.findElement(By.className("rodo-popup-agree")).click();
            System.out.println("TEST 1 - Okno z informacją o plikach cookies");
            System.out.println("TEST 1 - OK ");
        }
        //******************************************************************//

        //******************************************************************//
        //Checking if the news is up to date

        List<WebElement> listOfTitleNews = driver.findElements(By.cssSelector("li.wiadspec-li > a > span.tile-span > span"));
        System.out.println("Number of elements:" +listOfTitleNews.size());
        System.out.println("First Title:" +newsTitles.get(8));
        System.out.println("First Title:" +listOfTitleNews.get(8).getText());

        if (listOfTitleNews.get(8).getText().matches(newsTitles.get(8))) {System.out.println("TEST 3 - Tytuł jest poprawny");
        }else {System.out.println("TEST 3 - Tytuł do dupy");}

        List<String> newsTitlesGetFromWeb=new ArrayList<String>();
       listOfTitleNews.forEach(e->{
           newsTitlesGetFromWeb.add(e.getText());
       });

        if (newsTitlesGetFromWeb.equals(newsTitles)){System.out.println("TEST 4 - Tytuł OK");}

        //*******************************************************************//
        // Checking log in and log out
        driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/header/nav/ul/li[1]/a")).click();
        driver.manage().window().maximize();
        driver.findElement(By.id("email")).sendKeys("cyberavr@gmail.com");
        driver.findElement(By.id("password")).sendKeys("123");
        driver.findElement(By.xpath("//*[@id=\"sitebar\"]/form/button")).click();

//        listOfTitleNews.forEach(System.out::println);


        //******************************************************************//


    }
}
