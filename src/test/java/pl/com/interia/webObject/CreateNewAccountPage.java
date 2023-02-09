package pl.com.interia.webObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import javax.xml.xpath.XPath;

public class CreateNewAccountPage {

    WebDriver pdriver;
    CreateNewAccountPage(WebDriver mdriver)
    {
        pdriver=mdriver;
        PageFactory.initElements(mdriver,this);
    }

    @FindBy(xpath="//*[@id=\"095ovr\"]")
    WebElement userName;

    @FindBy(xpath="//*[@id=\"3jmdfe\"]")
    WebElement userSurname;
    @FindBy(name="birthdayDay")
    WebElement userBirthday;
    @FindBy(name="birthdayYear")
    WebElement userBirthdayYear;
    @FindBy(name="password")
    WebElement userPassword;
}
