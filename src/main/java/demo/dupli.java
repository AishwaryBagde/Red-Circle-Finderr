package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

public class dupli {
    private WebDriver driver;

    public dupli(WebDriver driver) {
        this.driver = driver;
    }

    public void downloadRedImages() throws IOException, InterruptedException {
        System.out.println("Start downloading red images from Google");

        // Navigate to Google Images
        driver.get("https://www.google.com/");

        // Find the search box and enter the query
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("red");
        searchBox.submit();

        // Click on the "Images" tab
        WebElement imagesTab = driver.findElement(By.xpath("(//div[contains(text(),'Images')])[1]"));
        imagesTab.click();


        // Scroll down to load more images
        JavascriptExecutor js = (JavascriptExecutor) driver;
        for (int i = 0; i < 5; i++) { // Scroll 5 times
            js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
            Thread.sleep(2000); // Wait for new images to load
        }

        // Wait for the images to load (adjust the wait time as needed)
        new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("img")));

        // Ensure the directory exists
        File dir = new File("red_images");
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // Get the list of image elements
        List<WebElement> images = driver.findElements(By.cssSelector("img"));
        ////div[@style="position:relative"]

        int count = 1;
        for (WebElement image : images) {
            String src = image.getAttribute("src");
            System.out.println("Found image URL: " + src);

            if (src != null && src.startsWith("http")) { // Check if URL is valid
                try {
                    System.out.println("Downloading: " + src);
                    downloadImage(src, "red_images/image_" + count + ".jpg");
                    count++;
                    if (count >= 100) { // Limit the number of images to download
                        break;
                    }
                } catch (Exception e) {
                    System.err.println("Failed to download image: " + src + " - " + e.getMessage());
                }
            } else {
                System.out.println("Skipping invalid or missing image URL");
            }
        }

        System.out.println("End downloading red images from Google");
    }

    private void downloadImage(String urlString, String fileName) throws IOException {
        try (BufferedInputStream in = new BufferedInputStream(new URL(urlString).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
