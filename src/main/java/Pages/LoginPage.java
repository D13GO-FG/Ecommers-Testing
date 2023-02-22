package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage extends BasePage{
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(how = How.ID, using = "user-name")
    private WebElement inputUserName;

    @FindBy(how = How.ID, using = "password")
    private WebElement inputPassword;

    @FindBy(how = How.ID, using = "login-button")
    private WebElement btnLogin;

    @FindBy(how = How.XPATH, using = "//h3[@data-test='error']")
    private WebElement msgError;

    public WebElement getInputUserName() {
        return inputUserName;
    }

    public WebElement getInputPassword() {
        return inputPassword;
    }

    public WebElement getBtnLogin() {
        return btnLogin;
    }

    public WebElement getMsgError() {
        return msgError;
    }
}
