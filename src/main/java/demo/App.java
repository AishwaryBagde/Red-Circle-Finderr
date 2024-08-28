package demo;
import java.net.MalformedURLException;

public class App {
    public void getGreeting() throws InterruptedException, MalformedURLException {
        TestCases2 test = new TestCases2(); // Initialize your test class

        try {
            test.testCase01();
            test.testCase02();
            test.runRedCircleFinder();

            // Delete downloaded files after processing
            //test.deleteDownloadedFiles();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            test.endTest(); // Ensure that the browser is closed after all tests
        }
    }

    public static void main(String[] args) throws InterruptedException, MalformedURLException {
        new App().getGreeting();
    }
}
