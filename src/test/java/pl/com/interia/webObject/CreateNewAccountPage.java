package pl.com.interia.webObject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class CreateNewAccountPage {

    WebDriver cnap_driver;

    @FindBy(xpath="//div[@class=\"register-form__inputs\"]//following::input[2]")
    WebElement userName;
    @FindBy(xpath="//div[@class=\"register-form__inputs\"]//following::input[3]")
    WebElement userSurname;
    @FindBy(xpath="//div[@class=\"register-form__inputs\"]//following::input[4]")
    WebElement userBirthdayDay;
    @FindBy(xpath="//div[@class=\"account-input-container account-select\"]//following::div[@class=\"icon-arrow-right-full\"][1]")
    WebElement userBirthdayMonth;
    @FindBy(xpath="//div[@class=\"register-form__inputs\"]//following::input[6]")
    WebElement userBirthdayYear;
    @FindBy(xpath="//div[@class=\"account-input-container account-select\"]//following::div[@class=\"icon-arrow-right-full\"][1]")
    WebElement userGender;
    @FindBy(xpath="//div[@class=\"register-form__inputs\"]//following::input[8]")
    WebElement accountName;
    @FindBy(id="password")
    WebElement userPassword;
    @FindBy(id="rePassword")
    WebElement repetedPassword;
    @FindBy(css="div[class=\"law-information__description law-information__description--highlighted\"]")
    WebElement acceptAllConsent;
    @FindBy(xpath = "//button[@class=\"btn\"]")
    WebElement createAccountButton;
     public CreateNewAccountPage(WebDriver mdriver)
     {
      this.cnap_driver=mdriver;
      PageFactory.initElements(mdriver,this);
     }
     public void setUserName(String name) {userName.sendKeys(name);}

    public void setUserSurname(String surname)
    {
        userSurname.sendKeys(surname);
    }
    public void setUserBirthdayDay(int day)
    {
        userBirthdayDay.sendKeys(Integer.toString(day));
    }

    public void setUserBirthdayMonth(String month)
    {
        userBirthdayMonth.click();
        WebElement dropdown =cnap_driver.findElement(By.xpath("//div[@class=\"account-input-container account-select account-input-container--active\"]//ul[@class=\"account-select__options visible\"]//span[text()='"+month+"']"));
        dropdown.click();
    }
    public void setUserGender(String gender)
    {
        userGender.click();
        WebElement dropdown =cnap_driver.findElement(By.xpath("//div[@class=\"account-input-container account-select account-input-container--active\"]//ul[@class=\"account-select__options visible\"]//span[text()='"+gender+"']"));
        dropdown.click();
    }

    public void setUserBirthdayYear(int year)
    {
        userBirthdayYear.sendKeys(Integer.toString(year));
    }
    public void setAccountName(String account)
    {
        accountName.sendKeys(account);
        accountName.sendKeys(Keys.ENTER);
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
    public void createAccount()
    {
        Actions actions = new Actions(cnap_driver);
        actions.moveToElement(createAccountButton).click().perform();
    }
}
