import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TP1 {
    WebDriver driver;
    ChromeOptions options;

    TP1() {
        Map<String, Object> prefs = new HashMap<String, Object>();
        // 1 = allow ; 2 = dismiss
        prefs.put("profile.default_content_setting_values.notifications", 2);

        options = new ChromeOptions();
        //TODO understand this line
        options.setExperimentalOption("prefs", prefs);
    }

    @BeforeMethod
    public void openEbay() {
        driver = new ChromeDriver(options);
        driver.get("https://www.ebay.fr/");
        driver.manage().window().maximize();
//        WebDriverWait wait = new WebDriverWait(driver, 10);
//        wait.until(ExpectedConditions.elementToBeClickable(By.id("gdpr-banner-accept"))).click();
    }

    @AfterMethod
    public void closeEbay() {
        driver.quit();
    }

    @Test
    public void FirstResultAddCart() {
        // Arrange
        String cssVacanceSport = "#gh-sbc tr td+td ul li";
        String cssFirstElem = ".srp-results.srp-grid > li:first-child";
        WebDriverWait wait = new WebDriverWait(driver, 5);

        // Act
        driver.findElement(By.id("gh-shop-a")).click();
        List<WebElement> l = driver.findElements(By.cssSelector(cssVacanceSport));
        l.get(2).findElement(By.cssSelector("a")).click();

        driver.findElement(By.cssSelector(cssFirstElem)).click();

        By elemCSS = By.id("isCartBtn_btn");
        wait.until(ExpectedConditions.elementToBeClickable(elemCSS)).click();
        WebElement cartUpdate = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("gh-cart-n")));
        String cartNumber = cartUpdate.getText();

        driver.findElement(By.cssSelector(".gh-cart-icon")).click();
        WebElement we = driver.findElement(By.cssSelector("[data-test-id=\"cart-bucket\"] div.image-display"));
        Boolean isNotEmptyCart = we != null;

        // Assert
        Assert.assertTrue(cartNumber.equals("1"), "Error number in cart = "+cartNumber);
        Assert.assertTrue(isNotEmptyCart , "Cart is empty");
    }
}
