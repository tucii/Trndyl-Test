package com.trendyol.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    By popUpClose = By.className("fancybox-close");
    By popUpOverlay = By.className("fancybox-overlay");
    By loginButton = By.className("login-container");
    By email = By.id("email");
    By password = By.id("password");
    By loginSubmit = By.id("loginSubmit");

    //popup i kapatma
    public void closePopUp() {
        clickTo(popUpClose);
        waitForElementToBeInvisible(popUpOverlay);
    }

    //login olma
    public void login(){
        clickTo(loginButton);
        write(email,"trendyolcase@gmail.com");
        write(password,"1122trendyol");
        clickTo(loginSubmit);
        waitUntilUrlContains("e=login");
    }

    }








