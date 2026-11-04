package io.github.shchepetin.roseltorg.pages;

import io.github.shchepetin.roseltorg.config.TestConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

public abstract class BasePage {

    protected final WebDriver driver;
    protected final WebDriverWait wait;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, TestConfig.TIMEOUT);
    }

    protected WebElement waitVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected boolean isShownWithin(By locator, Duration timeout) {
        try {
            new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    protected void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    protected void type(By locator, String text) {
        WebElement field = waitVisible(locator);
        field.clear();
        field.sendKeys(text);
    }

    protected void clickAndSwitchToNewTab(By locator) {
        Set<String> tabsBefore = driver.getWindowHandles();
        click(locator);
        wait.until(ExpectedConditions.numberOfWindowsToBe(tabsBefore.size() + 1));
        Set<String> tabsAfter = driver.getWindowHandles();
        tabsAfter.removeAll(tabsBefore);
        driver.switchTo().window(tabsAfter.iterator().next());
    }
}
