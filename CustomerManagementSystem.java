package demoGuru99;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.asserts.SoftAssert;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
public class CustomerManagementSystem {

	public static void main (String []args)
	{
		ExtentHtmlReporter htmlreport=new ExtentHtmlReporter("extentReports.html");
		ExtentReports extent=new ExtentReports();
		extent.attachReporter(htmlreport);

		ExtentTest test1 = extent.createTest("Customer Management System","This is a test to validate add customer and tarrif plan");
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		SoftAssert softassert = new SoftAssert();

		// Test 1: User adds a customer successfully
		test1.log(Status.INFO,"Staring Test Case");
		driver.get("https://demo.guru99.com/telecom/index.html");
		driver.navigate().to("https://demo.guru99.com/telecom/addcustomer.php");

		driver.findElement(By.xpath("//label[@for='done']")).click();
		driver.findElement(By.xpath("//input[@id='fname']")).sendKeys("Ashwini");
		driver.findElement(By.xpath("//input[@id='lname']")).sendKeys("Reetu");
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("ashureetu070@gmail.com");
		driver.findElement(By.xpath("//textarea[@id='message']")).sendKeys("Banaglore");
		driver.findElement(By.xpath("//input[@id='telephoneno']")).sendKeys("8105904270");
		driver.findElement(By.xpath("//input[@name='submit']")).click();

		String actualetitle = driver.findElement(By.xpath("//h1[normalize-space()='Access Details to Guru99 Telecom']")).getText();
		String expectedtitle = "Access Details to Guru99 Telecom";
		softassert.assertEquals(actualetitle, expectedtitle, "succesfull massage should match");

		//Customer ID:714670

		// Test 2: User adds a tariff plan for the customer
		driver.get("https://demo.guru99.com/telecom/assigntariffplantocustomer.php");
		driver.findElement(By.xpath("//input[@id='customer_id']")).sendKeys("714670"); 
		driver.findElement(By.xpath("//input[@name='submit']")).click();

		String actualemsg = driver.findElement(By.xpath("//h1[normalize-space()='Add Tariff Plan to Customer']")).getText();
		String expectedmsg = "Add Tariff Plan to Customer";
		softassert.assertEquals(actualemsg, expectedmsg, "succesfull massage should match");
		test1.pass("passed keyboard extent key");

		driver.close();
		driver.quit();
		test1.pass("closed the browser");

		test1.info("test completed");
		extent.flush();
	}
}


