package demo;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;

public class TestCases {
    ChromeDriver driver;
    WebDriverWait wait;

    public TestCases() {
        System.out.println("Constructor: TestCases");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    public void endTest() {
        System.out.println("End Test: TestCases");
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }

    public void testCase01() {
        System.out.println("Start Test case: testCase01");
        
        Homepage homepage = new Homepage(driver); 
        homepage.navigateToHomePage();

        //Storing Pagetitle to String & printing the same
        String pageTitle = homepage.getPageTitle(); 
        System.out.println("Page title is: " + pageTitle);

        //Storing isPageTitleDisplayed to String & printing the same
        boolean isPageTitleDisplayed = homepage.isPageTitleDisplayed();
        if (isPageTitleDisplayed) {
            System.out.println("Page title element is displayed.");
        } else {
            System.out.println("Page title element is not displayed.");
        }

        System.out.println("End Test case: testCase01");
    }

    public void testCase02() throws InterruptedException {
        System.out.println("Start Test case: testCase02");

        Login login = new Login(driver);
        login.navigateToLoginPage();

        //Storing Username && password to strings TO print later on
        String username = "standard_user";
        String password = "secret_sauce";


        //Printing if user logged in or not
        boolean isLoginSuccessful = login.Performlogin(username, password);
        if (isLoginSuccessful) {
            System.out.println("User successfully logged in with username: " + username + " and password: " + password);
        } else {
            System.out.println("Failed to log in with username: " + username + " and password: " + password);
        }

        System.out.println("End Test case: testCase02");
    }


    // public static void main(String[] args) {
    //     TestCases test = new TestCases();
    //     test.testCase01();
    //     test.endTest();
    // }
}
