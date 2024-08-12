package demo;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login {
    WebDriver driver;

    //Defining URL Separately
    String url = "https://www.saucedemo.com/";

    //using Page Factory Methods below

    @FindBy(id = "user-name")
    WebElement userName_Text_Box;

    @FindBy(id = "password")
    WebElement password_Text_Box;
    
    @FindBy(xpath = "//span[contains(text(),'Products')]")
    WebElement loginPage_Text;

    @FindBy(id = "login-button")
    WebElement login_Button;

    @FindBy(xpath="//button[@class = \"error-button\"]")
    WebElement error_message;

    //Initialising Webdriver & PageFactory
      public Login(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this); 
    }

//navigate to Login Method will check if current url is equalt to our Desired url or not If not then go to desired url
    public void navigateToLoginPage() {
        if (!this.driver.getCurrentUrl().equals(this.url)) {
            this.driver.get(this.url);
        }
}

//This Method will perform Login I am passing this Login Username & login Passwords as parameters
public boolean Performlogin(String Username , String Password) throws InterruptedException {

//Commenting Wait Statements to see Automation Properly
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    
    //wait.until(ExpectedConditions.visibilityOf(userName_Text_Box));
    Thread.sleep(1000);
    userName_Text_Box.sendKeys(Username);

    //wait.until(ExpectedConditions.visibilityOf(password_Text_Box));
    Thread.sleep(1000);
    password_Text_Box.sendKeys(Password);
    
   // wait.until(ExpectedConditions.visibilityOf(login_Button));
   Thread.sleep(1000);
    login_Button.click();

    Thread.sleep(1000);

    return this.VerifyUserLoggedIn();
}



//This Method will verify if user logged in or not 
public boolean VerifyUserLoggedIn(){
    try{
        return loginPage_Text.isDisplayed();
    }catch(Exception e){
        return false;
    }
}

}
