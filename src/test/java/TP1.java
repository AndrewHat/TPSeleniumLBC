import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
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
    public void openLBC() {
        driver = new ChromeDriver(options);
        driver.get("https://www.leboncoin.fr/");
        driver.manage().window().maximize();
        driver.findElement(By.id("didomi-notice-agree-button")).click();
    }

    @AfterMethod
    public void closeLBC() {
        driver.quit();
    }

    @Test
    public void FirstResultSearch() {
        // Arrange

        // Act
        driver.findElement(By.id("text-categories-desktop")).click();
        driver.findElement(By.id("cta-categories_category_emploi-desktop")).click();
        driver.findElement(By.id("cta-categories_category_offres_d_emploi-desktop-child")).click();
        driver.findElement(By.id("input-keywords-desktop")).sendKeys("QA");

        // Assert
    }
}
