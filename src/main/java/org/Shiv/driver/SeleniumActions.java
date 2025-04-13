package org.Shiv.driver;

import io.opentelemetry.internal.shaded.jctools.queues.MessagePassingQueue;
import org.Shiv.utils.PropertiesUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

import static java.util.Optional.ofNullable;
import static org.Shiv.driver.DriverManager.driver;
import static org.Shiv.driver.DriverManager.getDriver;

public class SeleniumActions {
    static long timeout = Long.parseLong (PropertiesUtils.getPropertyValue ("explicitTimeout.seconds"));
    public static WebElement find (By locator) {
        return getDriver ().findElement (locator);
    }

    public static List<WebElement> finds (By locator) {
        return getDriver ().findElements (locator);
    }

    public static void click (By locator) {
        waitForElementClickable (locator);
        waitForElementVisibility (locator);
        HighlightedActions.highlightElement (locator);
        find (locator).click ();
    }

    public static void scrollIntoView (By locator) {
        Actions action = new Actions (DriverManager.getDriver ());
        waitForElementVisibility (locator);
        HighlightedActions.highlightElement (locator);
        action.scrollToElement (find (locator));
    }
    public static void switchToTheNewTabOpened () {
        String currentWindowHandle = driver.getWindowHandle ();
        driver.switchTo ()
            .window (currentWindowHandle);
        Set<String> allWindowHandles = driver.getWindowHandles ();
        for (String windowhandles : allWindowHandles) {
            if (!windowhandles.equalsIgnoreCase (currentWindowHandle)) {
                driver.switchTo ()
                    .window (windowhandles);
                break;
            }
        }
    }

    public static void scrollIntoViewElement (WebElement locator) {
        Actions action = new Actions (DriverManager.getDriver ());
        waitForElementVisibility (locator);
        HighlightedActions.highlightWebElement (locator);
        action.scrollToElement (locator);
    }

    public static void alternateClick (By locator) {
        Actions action = new Actions (DriverManager.getDriver ());
        waitForElementVisibility (locator);
        HighlightedActions.highlightElement (locator);
        action.moveToElement (find (locator))
            .click ()
            .perform ();
    }

    public static void sendKeys (By locator, String keys) {
        waitForElementClickable (locator);
        find (locator).sendKeys (keys);
    }

    public static void hover (By locator) {
        Actions action = new Actions (DriverManager.getDriver ());
        waitForElementVisibility (locator);
        action.moveToElement (find (locator))
            .perform ();
    }

    public static void sleep (long millis) {
        try {
            Thread.sleep (millis);
        } catch (InterruptedException e) {
            throw new RuntimeException (e);
        }
    }

    public static void dropdownByText (By locator, String str) {
        waitForElementVisibility (locator);
        Select select = new Select (find (locator));
        HighlightedActions.highlightElement (locator);
        select.selectByVisibleText (str);
    }

    public static boolean isDisplayed (By locator) {
        waitForElementVisibility (locator);
        HighlightedActions.highlightElement (locator);
        return find (locator).isDisplayed ();
    }

    public static boolean isDisplayedWithoutWait (By locator) {
        try {
            return find (locator).isDisplayed ();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public static void verifyIsDisplayed (By locator) {
        waitForElementVisibility (locator);
        HighlightedActions.highlightElement (locator);
        find (locator).isDisplayed ();
    }

    public static void waitForElementClickable (By locator) {
        WebDriverWait wait = new WebDriverWait (getDriver (), Duration.ofSeconds (timeout));
        wait.until (ExpectedConditions.elementToBeClickable (locator));
    }

    public static void waitForElementVisibility (By locator) {
        WebDriverWait wait = new WebDriverWait (getDriver (), Duration.ofSeconds (timeout));
        wait.until (ExpectedConditions.visibilityOfElementLocated (locator));
    }

    public static void waitForElementVisibility (WebElement locator) {
        WebDriverWait wait = new WebDriverWait (getDriver (), Duration.ofSeconds (timeout));
        wait.until (ExpectedConditions.visibilityOf (locator));
    }

    public static String getText (By locator) {
        WebDriverWait wait = new WebDriverWait (getDriver (), Duration.ofSeconds (timeout));
        wait.until (ExpectedConditions.visibilityOfElementLocated (locator));
        return find (locator).getText ();
    }

    public static String getAttribute (By locator, String value) {
        WebDriverWait wait = new WebDriverWait (getDriver (), Duration.ofSeconds (timeout));
        wait.until (ExpectedConditions.visibilityOfElementLocated (locator));
        return find (locator).getAttribute (value);
    }

    public static String getPageTitle () {
        return driver.getTitle ();
    }

    public static void clear (By locator) {
        WebDriverWait wait = new WebDriverWait (getDriver (), Duration.ofSeconds (timeout));
        wait.until (ExpectedConditions.visibilityOfElementLocated (locator));
        find (locator).clear ();
    }
}
