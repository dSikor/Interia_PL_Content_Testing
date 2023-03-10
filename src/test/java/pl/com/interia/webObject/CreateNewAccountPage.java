package pl.com.interia.webObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreateNewAccountPage {

    WebDriver cnap_driver;
    @FindBy(xpath="/html/body/div[1]/div/div/div/div/div[2]/div/form/div[1]/div[1]/input")
    WebElement userName;
    @FindBy(xpath="/html/body/div[1]/div/div/div/div/div[2]/div/form/div[1]/div[2]/input")
    WebElement userSurname;
    @FindBy(xpath="/html/body/div[1]/div/div/div/div/div[2]/div/form/div[1]/div[3]/div[1]/input")
    WebElement userBirthdayDay;
    @FindBy(xpath="/html/body/div[1]/div/div/div/div/div[2]/div/form/div[1]/div[3]/div[2]/div[1]/span")
    WebElement userBirthdayMonth;
    @FindBy(xpath="/html/body/div[1]/div/div/div/div/div[2]/div/form/div[1]/div[3]/div[3]/input")
    WebElement userBirthdayYear;
    @FindBy(xpath="/html/body/div[1]/div/div/div/div/div[2]/div/form/div[1]/div[5]/div[1]/input")
    WebElement accountName;
    @FindBy(name="password")
    WebElement userPassword;
    @FindBy(name="rePassword")
    WebElement repetedPassword;
    @FindBy(xpath="/html/body/div[1]/div/div/div/div/div[2]/div/form/div[2]/div[1]/div[1]/label/div/div")
    WebElement acceptAllConsent;
     public CreateNewAccountPage(WebDriver mdriver)
     {
      this.cnap_driver=mdriver;
      PageFactory.initElements(mdriver,this);
     }
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
        userBirthdayDay.sendKeys(Integer.toString(day));
    }

    public void setUserBirthdayMonth(String month)
    {
        Select dropdown = new Select(userBirthdayMonth);
       // dropdown.selectByVisibleText(month);
        dropdown.selectByVisibleText(month);
    }


    public void setUserBirthdayYear(int year)
    {
        userBirthdayYear.sendKeys(Integer.toString(year));
    }
    public void setAccountName(String account)
    {
        accountName.sendKeys(account);
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
