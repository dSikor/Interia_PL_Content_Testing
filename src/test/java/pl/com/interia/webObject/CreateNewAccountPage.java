package pl.com.interia.webObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import javax.xml.xpath.XPath;

public class CreateNewAccountPage {

    WebDriver pdriver;
   public CreateNewAccountPage(WebDriver mdriver)
    {
        pdriver=mdriver;
        PageFactory.initElements(mdriver,this);
    }

    @FindBy(xpath="//*[@id=\"095ovr\"]")
    WebElement userName;

    @FindBy(xpath="//*[@id=\"3jmdfe\"]")
    WebElement userSurname;
    @FindBy(name="birthdayDay")
    WebElement userBirthdayDay;
    @FindBy(name="birthdayYear")
    WebElement userBirthdayYear;

//    @FindBy(name="birthdayYear")
//    WebElement userGender;

    @FindBy(xpath="//*[@id=\"d08sa\"]")
    WebElement accountName;

    @FindBy(name="password")
    WebElement userPassword;
    @FindBy(name="rePassword")
    WebElement repetedPassword;

    @FindBy(xpath="//*[@id=\"mainApp\"]/div/div/div/div/div[2]/div/form/div[2]/div[1]/div[1]/label/div/div")
    WebElement acceptAllConsent;

    public void setUserName(String name)
    {
        userName.sendKeys(name);
    }

    public void setUserSurname(String surname)
    {
        userSurname.sendKeys(surname);
    }
    public void setUserBirthdayDay(int day)
    {
//        userBirthdayDay.sendKeys(day);
    }
    public void setUserBirthdayYear(int year)
    {

    }
    public void setAccountName(String account)
    {

    }
    public void setUserPassword(String password)
    {
        userPassword.sendKeys(password);
    }
    public void setRepetedPassword(String rpassword)
    {
        repetedPassword.sendKeys(rpassword);
    }

public void selectAllConsent()
{
    acceptAllConsent.click();
}








}
