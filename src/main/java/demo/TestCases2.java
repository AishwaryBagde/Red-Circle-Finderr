package demo;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class TestCases2 {
    private ChromeDriver driver;
    private WebDriverWait wait;

    public TestCases2() {
        System.out.println("Constructor: TestCases2");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    public void endTest() {
        System.out.println("End Test: TestCases2");
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }

    public void testCase01() {
        System.out.println("Start Test case: testCase01");

        // Add your test logic here

        System.out.println("End Test case: testCase01");
    }

    public void testCase02() throws InterruptedException {
        System.out.println("Start Test case: testCase02");

        // Add your test logic here

        System.out.println("End Test case: testCase02");
    }

    public void runRedCircleFinder() throws InterruptedException {
        RedCircleFinder redCircleFinder = new RedCircleFinder(driver);
        try {
            redCircleFinder.downloadRedImages();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteDownloadedFiles() {
        File directory = new File("red_images");

        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null && files.length > 0) {
                for (File file : files) {
                    if (file.isFile()) {
                        boolean deleted = file.delete();
                        if (deleted) {
                            System.out.println("Deleted file: " + file.getName());
                        } else {
                            System.out.println("Failed to delete file: " + file.getName());
                        }
                    }
                }
            } else {
                System.out.println("No files found to delete in directory: " + directory.getAbsolutePath());
            }
        } else {
            System.out.println("Directory does not exist: " + directory.getAbsolutePath());
        }
    }

    // Additional test case methods can be added here
}
