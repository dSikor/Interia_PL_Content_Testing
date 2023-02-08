package pl.com.interia.contentTest;

import org.testng.annotations.Test;
import pl.com.interia.webObject.RegistrationUser;

public class InteriaRegistrationTest extends RegistrationUser {

    @Test

    public void registrationUserTest(){
        driver.get(baseURL);
    }





}
