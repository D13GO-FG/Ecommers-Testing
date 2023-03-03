package TestsUI;

import Steps.LoginSteps;
import Steps.ProductsSteps;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class LoginTest extends BaseTests{
    @Test
    public void verifySuccessfulLogin(){
        LoginSteps loginSteps = new LoginSteps(driver);
        ProductsSteps productsSteps = new ProductsSteps(driver);
        loginSteps.typeUsername("standard_user");
        loginSteps.typePassword("secret_sauce");
        loginSteps.clickLogin();
        //fail("Forced error");
        assertEquals(productsSteps.getPageText(), "Product", "It's not right page.");
        System.out.println("Done");
    }
}
