package GuruDemoTc;

import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;

public class uploadresume {
    @Test
    public void demo() {

        // Setup WebDriver
        WebDriverManager.chromedriver().setup();
        
        Proxy proxy = new Proxy();
        proxy.setHttpProxy("proxyHost:proxyPort");
        ChromeOptions options = new ChromeOptions();
        options.setProxy(proxy);
        options.addArguments("--ignore-certificate-errors");
        WebDriver driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        driver.get("https://www.naukri.com/");

        try {
            // Wait until the Login button is clickable
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Login']"))).click();

            // Wait for email input to be visible and enter the email
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Enter your active Email ID / Username']"))).sendKeys("ashureetu070@gmail.com");

            // Wait for password input to be visible and enter the password
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Enter your password']"))).sendKeys("Ashumom1@");

            // Wait until the Login button is clickable and click
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Login']"))).click();

            // Wait for profile link to be clickable
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='view-profile-wrapper']"))).click();

            // Wait for the file upload input to be clickable and upload the resume
            WebElement ele = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='file']"))); // Updated XPath
            ele.sendKeys("C:\\Users\\HP\\Downloads\\Ashwini K.QA.PDF.pdf"); // Ensure this path is correct
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser after the test
            driver.quit();
        }
    }
}
