package pl.com.interia.contentTest;

import org.testng.annotations.Test;
import pl.com.interia.webObject.*;

public class InteriaRegistrationTest extends RegistrationUser {

    @Test
    public void registrationUserTest(){
        driver.get(baseURL);

        CreateNewAccountPage cnap = new CreateNewAccountPage(driver);

        //Registration new user
        cnap.setUserName(userName);
        cnap.setUserSurname(userSurname);
        cnap.setUserPassword(password);

    }





}
