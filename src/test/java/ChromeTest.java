import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class ChromeTest {
    private WebDriver driver;

    @BeforeSuite
    @Parameters({"hubURL"})
    public void before(String hubUrl) throws MalformedURLException {
        DesiredCapabilities ds = null;
        ds= DesiredCapabilities.chrome();
        ds.setBrowserName("chrome");
        ds.setPlatform(Platform.LINUX);
        driver=new RemoteWebDriver(new URL(hubUrl),ds);
    }

    @Test
    @Parameters({"url"})
    public void testGoogle(String Url) throws InterruptedException {
        TimeUnit time = TimeUnit.SECONDS;
        LocalDateTime date = LocalDateTime.now();
        int day = date.getDayOfMonth();
        driver.get(Url);
        driver.manage().window().maximize();
        System.out.println(driver.getTitle());
        driver.manage().deleteAllCookies();
        driver.findElement(By.xpath("//div//input[@name='ss']")).sendKeys("Mumbai");
        time.sleep(5);
        // code for check-in and check-out date
        driver.findElement(By.xpath("//button[@data-testid='date-display-field-start']")).click();
        driver.findElement(By.xpath("//span[text()=" + "'" + day + "'" + "]")).click();
        driver.findElement(By.xpath("//span[text()=" + "'" + (day + 1) + "'" + "]")).click();
        time.sleep(1);
        driver.findElement(By.xpath("//div//button[@type='submit']")).click(); //  click on search button
        // click on 4 & 5 stars
        driver.findElement(By.xpath("//div[text()='4 stars']")).click();
        driver.findElement(By.xpath("//div[text()='5 stars']")).click();
        ///
        time.sleep(5);
        List<WebElement> hotels = driver.findElements(By.xpath("//div[@data-testid='title']"));
        System.out.println();
        for (int i = 0; i < 5; i++) {
            hotels.get(i).click();
            time.sleep(5);
            System.out.println("Hotel " + (i + 1));
            System.out.println(hotels.get(i).getText());
            Set<String> window = driver.getWindowHandles();
            List<String> windows = new ArrayList<>(window);
            driver.switchTo().window(windows.get(1));
            String desc = driver.findElement(By.xpath("//p[@id='showMap2']")).getText();
            System.out.println(desc);
            driver.findElement(By.xpath("//a[text()='Facilities']")).click();
            time.sleep(5);
            List<WebElement> facilities = driver.findElements(By.xpath("//div[text()='Business Facilities']/ancestor::div[3]/ul//descendant::div[4]"));
            for (WebElement facility : facilities) {
                System.out.println(facility.getText());
            }
            System.out.println();
            driver.close();
            driver.switchTo().window(windows.get(0));
            windows.remove(1);
        }
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
