package Steps;

import Pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class LoginSteps extends BaseSteps{
    LoginPage loginPage;
    public LoginSteps(WebDriver driver) {
        super(driver);
        loginPage = PageFactory.initElements(driver, LoginPage.class);
    }

    public void typeUsername(String username){
        loginPage.getInputUserName().sendKeys(username);
    }

    public void typePassword(String password){
        loginPage.getInputPassword().sendKeys(password);
    }

    public void clickLogin(){
        loginPage.getBtnLogin().click();
    }

    public String getText(){
        return loginPage.getMsgError().getText();
    }

}
