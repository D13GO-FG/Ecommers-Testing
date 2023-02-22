package Steps;

import Pages.ProductsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ProductsSteps extends BaseSteps{
    ProductsPage productsPage;
    public ProductsSteps(WebDriver driver) {
        super(driver);
        productsPage = PageFactory.initElements(driver, ProductsPage.class);
    }

    public String getPageText(){
        return productsPage.getPageTitle().getText();
    }
}
