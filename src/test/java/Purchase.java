import Pages.LoginPageFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;

public class Purchase {
    private WebDriver driver;

    @BeforeMethod
    public static void checkBrowserShim() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setUp() throws IOException {
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Attachment
    private byte[] addScreenshot() {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File("C:\\Users\\momo5\\Desktop\\TestScreen\\screen.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Test
    public void shouldBuyLabsBoltTshirt() throws IOException {

        LoginPageFactory login = new LoginPageFactory(driver);
        login.login();

        WebElement labsBoltTshirtButtonBuy = driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt"));
        labsBoltTshirtButtonBuy.click();
        addScreenshot();
        WebElement cartLink = driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a"));
        cartLink.click();
        addScreenshot();
        WebElement checkoutLink = driver.findElement(By.id("checkout"));
        checkoutLink.click();
        addScreenshot();
        WebElement firstNameInput = driver.findElement(By.id("first-name"));
        firstNameInput.sendKeys("Иван");
        WebElement lastNameInput = driver.findElement(By.id("last-name"));
        lastNameInput.sendKeys("Иванов");
        WebElement postalCodeInput = driver.findElement(By.id("postal-code"));
        postalCodeInput.sendKeys("Санкт-Петербург, Невский пр., д.1");
        addScreenshot();
        WebElement buttonContinue = driver.findElement(By.id("continue"));
        buttonContinue.click();
        addScreenshot();
        WebElement finishButton = driver.findElement(By.id("finish"));
        finishButton.click();
        addScreenshot();

    }

}
