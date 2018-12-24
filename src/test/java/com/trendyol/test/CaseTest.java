package com.trendyol.test;


import com.trendyol.test.pages.BasketPage;
import com.trendyol.test.pages.BoutiquePage;
import com.trendyol.test.pages.HomePage;
import com.trendyol.test.pages.ProductPage;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class CaseTest extends BaseTest {

    @Test
    public void TrendyolCase() {
        driver.get("https://www.trendyol.com");

        //login ve popup kapatma
        HomePage homePage = new HomePage(driver);
        homePage.closePopUp();
        homePage.login();

        //Tab ve tabin resim kontrolleri, random tab ve butik secimi
        BoutiquePage boutiquePage = new BoutiquePage(driver);
        boutiquePage.checkTabsandImage();
        boutiquePage.clicktoRandomTab();
        boutiquePage.clickToRandomBoutique();

        //Butikteki tum urunler icin scroll islemi,resim kontrolu, random urun secimi ve secilen urunun fiyatinin tutulmasi
        ProductPage productPage = new ProductPage(driver);
        List<WebElement> products = productPage.scrollToLastProduct(true);
        productPage.getRandomProduct(products);
        productPage.addToBasket(false);
        String productPrice = productPage.getProductPrice();

        //Urunu sepete atma
        BasketPage basketPage = new BasketPage(driver);
        basketPage.clickToBasketButton();
        String basketProductPrice = basketPage.getBasketProductPrice();

        //Sepete atilan urunun fiyat dogrulamasi
        assertThat("Sepete atilan urunun fiyati ayni degil: ", productPrice, containsString(basketProductPrice));
    }
}
