package pl.com.interia.webObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginUserPage {
    WebDriver cnap_driver;

    public LoginUserPage(WebDriver mdriver)
    {
        this.cnap_driver=mdriver;
        PageFactory.initElements(mdriver,this);
    }

    @FindBy(id="email")
    WebElement userEmail;

    @FindBy(id="password")
    WebElement userPassword;


    @FindBy(xpath = "//button[@class=\"btn\"]")
    WebElement logInButton;

    public void setUserEmail(String email) {
        userEmail.sendKeys(email);
    }
    public WebElement getUserEmail() {
        return userEmail;
    }
    public void setUserPassword(String password) {
        userPassword.sendKeys(password);
    }

    public void logIn()
    {
        logInButton.click();
//        Actions actions = new Actions(cnap_driver);
//        actions.moveToElement(logInButton).click().perform();
    }
}
