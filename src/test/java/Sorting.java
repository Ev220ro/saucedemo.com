import Pages.LoginPageFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ru.yandex.qatools.htmlelements.element.Select;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class Sorting {
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
    public void shouldSortZA() {
        LoginPageFactory login = new LoginPageFactory(driver);
        login.login();

        Point expectedLocationElement = new Point(313, 192);
        WebElement checkedZaElement = driver.findElement(By.xpath("//*[@id=\"item_3_img_link\"]"));
        WebElement selectItem = driver.findElement(By.tagName("select"));
        ru.yandex.qatools.htmlelements.element.Select select = new Select(selectItem);
        select.selectByValue("za");
        Assertions.assertEquals(expectedLocationElement, checkedZaElement.getLocation());
        addScreenshot();
    }

    @Test
    public void shouldSortAZ() {
        LoginPageFactory login = new LoginPageFactory(driver);
        login.login();

        Point expectedLocationElement = new Point(313, 192);
        WebElement checkedAzElement = driver.findElement(By.xpath("//*[@id=\"item_4_img_link\"]"));
        WebElement selectItem = driver.findElement(By.tagName("select"));
        ru.yandex.qatools.htmlelements.element.Select select = new Select(selectItem);
        select.selectByValue("az");
        Assertions.assertEquals(expectedLocationElement, checkedAzElement.getLocation());
        addScreenshot();
    }

    @Test
    public void shouldSortByPriceDescending() {
        LoginPageFactory login = new LoginPageFactory(driver);
        login.login();

        Point expectedLocationElement = new Point(313, 192);
        WebElement checkedMaxElement = driver.findElement(By.xpath("//*[@id=\"item_5_img_link\"]"));
        WebElement selectItem = driver.findElement(By.tagName("select"));
        ru.yandex.qatools.htmlelements.element.Select select = new Select(selectItem);
        select.selectByValue("hilo");
        Assertions.assertEquals(expectedLocationElement, checkedMaxElement.getLocation());
        addScreenshot();
    }

    @Test
    public void shouldSortByPriceAscending() {
        LoginPageFactory login = new LoginPageFactory(driver);
        login.login();

        Point expectedLocationElement = new Point(313, 192);
        WebElement selectItem = driver.findElement(By.tagName("select"));
        WebElement checkedMinElement = driver.findElement(By.xpath("//*[@id=\"item_2_img_link\"]/img"));
        ru.yandex.qatools.htmlelements.element.Select select = new Select(selectItem);
        select.selectByValue("lohi");
        Assertions.assertEquals(expectedLocationElement, checkedMinElement.getLocation());
        addScreenshot();
    }


}
