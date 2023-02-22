package Steps;

import Pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginSteps extends BaseSteps{
    LoginPage loginPage;
    public LoginSteps(WebDriver driver) {
        super(driver);
        loginPage = PageFactory.initElements(driver, LoginPage.class);
    }

    public void typeUsername(String username){
        WebElement usernameInput = new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(loginPage.getInputUserName()));
        //loginPage.getInputUserName().sendKeys(username);
        usernameInput.sendKeys(username);
    }

    public void typePassword(String password){
        WebElement passwordInput = new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(loginPage.getInputPassword()));
        //loginPage.getInputPassword().sendKeys(password);
        passwordInput.sendKeys(password);
    }

    public void clickLogin(){
        loginPage.getBtnLogin().click();
    }

    public String getTextError(){
        return loginPage.getMsgError().getText();
    }



}
