import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class BasicTest {

    public WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver","/usr/bin/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-gpu");
        driver = new ChromeDriver(options);
    }

    @Test
    public void setWindowSize() {
        driver.manage().window().setSize(new Dimension(1600, 700));
    }

    @Test
    public void runTest() {
        driver.get("http://www.google.pl");
        driver.findElement(By.cssSelector("input.gsfi:nth-child(1)")).isDisplayed();
    }

    @Test
    public void takeScreeshot() throws IOException {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("target/screenshots/screen.png"));
    }

    @AfterClass
    public void driverQuit() {
        driver.quit();
    }
}
