package com.trendyol.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasketPage extends BasePage{


    public BasketPage(WebDriver driver) {
        super(driver);
    }

    By basketButton = By.cssSelector("#myBasketListItem .icon-container");
    By basketProductPrice = By.cssSelector(".priceInfo .new-price");


    //Sepete gider
    public void clickToBasketButton() {
        System.out.println("Sepetime gidiliyor...");
        clickTo(basketButton);
    }

    //Sepetteki ürünün fiyatini tutar (Assert için)
    public String getBasketProductPrice() {
        waitForElementToBeVisible(basketProductPrice);
        return driver.findElement(basketProductPrice).getText();
    }
}

