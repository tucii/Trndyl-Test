package com.trendyol.test.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class BasePage {
    WebDriver driver;


    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForElementToBeClickable(By by) {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public void waitForElementToBeVisible(By by) {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void waitForElementToBeInvisible(By by) {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }


    public void waitUntilUrlContains (String urlPart) {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.urlContains(urlPart));
    }


    public void write(By by, String text) {
        waitForElementToBeClickable(by);
        findElement(by).sendKeys(text);
        waitForLoad();
    }

    public void clickTo(By by) {
        waitForElementToBeClickable(by);
        findElement(by).click();
        waitForLoad();
    }

    public WebElement findElement(By by) {
        waitForElementToBeClickable(by);
        return driver.findElement(by);
    }

    public void waitForLoad() {
        ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
                    }
                };
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(pageLoadCondition);
    }

    public void isImagesLoaded(List<WebElement> images) {

        for (WebElement image : images){
            Object result = ((JavascriptExecutor) driver).executeScript(
                    "return arguments[0].complete && "+
                            "typeof arguments[0].naturalWidth != \"undefined\" && "+
                            "arguments[0].naturalWidth > 0", image);
            boolean loaded = false;
            if (result instanceof Boolean) {
                loaded = (Boolean) result;
                if (!loaded) {
                    System.out.println("Resim Yuklenemedi .... " + image);
                }
            }
        }
        System.out.println("Resim kontrolü bitti. ");

    }

}

