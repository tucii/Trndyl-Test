package com.trendyol.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

public class ProductPage extends BasePage {

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    By productImages = By.cssSelector(".product-box");
    By productCount = By.cssSelector(".product-count > .count");
    By addToBasket = By.className("add-to-basket-text");
    By selectSize = By.cssSelector(".variant-titlebox span");
    By firstSize = By.cssSelector("[data-original-index=\"1\"]");
    By productPrice = By.cssSelector(".product-info-priceBox .sale-price");

    public List<WebElement> scrollToLastProduct(boolean isScroll) {
        int productsSize = driver.findElements(productImages).size();
        int productCounter = Integer.parseInt(driver.findElement(productCount).getText());
        System.out.println("Toplam urun sayisi: " + productCounter);
        while (productCounter > productsSize && isScroll) {

            System.out.println("Ürünlerin hepsinin List e eklenmesi için aşağıya scroll yapılıyor...");
            productsSize = driver.findElements(productImages).size();
            WebElement scrollToElement = driver.findElements(productImages).get(productsSize - 1);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", scrollToElement);
            System.out.println("Guncel urun sayisi:" + productsSize);
        }

        List<WebElement> allBoutiqueImages = driver.findElements(productImages);
        isImagesLoaded(allBoutiqueImages);
        System.out.println("Urunlerin resim kontrolleri yapiliyor...");
        System.out.println("Scroll tamamlandi");
        System.out.println("Bulunan ürün sayısı: " + productsSize);

        return allBoutiqueImages;
    }

    public void getRandomProduct(List<WebElement> allBoutiqueImages) {
        System.out.println("Rastgele bir butik ürününe tıklanıyor... ");
        int allBoutiqueImageCount = allBoutiqueImages.size();
        int randomBoutiqueImage = new Random().nextInt(allBoutiqueImageCount - 1) + 1;
        allBoutiqueImages.get(randomBoutiqueImage).click();
    }

    public String getProductPrice() {
        waitForElementToBeVisible(productPrice);
        return driver.findElement(productPrice).getText();
    }

    public void addToBasket(boolean isScroll) {
        String basketText = driver.findElement(addToBasket).getText();
        System.out.println(basketText);
        System.out.println(driver.findElements(selectSize).size());

        if (basketText.equals("Tükendi")) {
            driver.navigate().back();
            List<WebElement> products = scrollToLastProduct(isScroll);
            getRandomProduct(products);
            addToBasket(false);
            return;
        }

        if (driver.findElements(selectSize).size() > 0) {
            clickTo(addToBasket);
            clickTo(firstSize);
        } else {
            clickTo(addToBasket);
        }
    }
}