package demo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Homepage {
    WebDriver driver;

    String url = "https://www.saucedemo.com/";

    // Define the elements you need
    @FindBy(xpath = "//div[@class = \"login_logo\"]") // Replace with an actual element if needed
    WebElement pageTitle;

    public Homepage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void navigateToHomePage() {
        if (!this.driver.getCurrentUrl().equals(this.url)) {
            this.driver.get(this.url);
        }
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public boolean isPageTitleDisplayed() {
        try {
            return pageTitle.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
