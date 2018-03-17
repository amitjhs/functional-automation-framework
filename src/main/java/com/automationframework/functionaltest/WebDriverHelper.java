package com.automationframework.functionaltest;

import org.openqa.selenium.*;

public class WebDriverHelper {

    protected WebDriver driver;

    public WebDriverHelper(final WebDriver driver) {
        this.driver = driver;
    }

    private static final int Wait_For_Element_Seconds = 10;
    private static final int Wait_For_Page_Seconds = 60;

    public boolean isElementPresent(final WebElement element) {
        try {
            element.getTagName();
        } catch (final NoSuchElementException ignored) {
            return false;
        } catch (final StaleElementReferenceException ignored) {
            return false;
        }
        return true;
    }

    public void clickOnLink(String linkText){
        driver.findElement(By.linkText(linkText)).click();
    }
}