package com.trendyol.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.Random;

public class BoutiquePage extends BasePage{

    public BoutiquePage(WebDriver driver) {
        super(driver);
    }

    By heroBannerSelector = By.cssSelector("a.butik-img-size");
    By tabSelector = By.cssSelector("a.tab-header");
    By boutiqueImages = By.cssSelector(".boutique-large-list-tmpl-result .butik-large-image img");
    By boutiqueFancyBoxButton = By.cssSelector("#boutiqueFancy .boutique-button");
    List <WebElement> tabs = driver.findElements(tabSelector);
    int totalTabCount = tabs.size();

    //tab kontrolu
    public void checkTabsandImage() {
        for (int i = 1; i <= totalTabCount; i++) {
            By nextTabSelector = By.cssSelector(".tabLink#item" + i + " > a");
            WebElement nextTabLink = driver.findElement(nextTabSelector);
            nextTabLink.click();


            List<WebElement> currentTabImage = driver.findElements(boutiqueImages);

            System.out.println("------------------------------------------------------");
            System.out.println(i + "numarali butigin resimleri kontrol ediliyor...");
            System.out.println("------------------------------------------------------");

            isImagesLoaded(currentTabImage);
        }
    }

    public void clicktoRandomTab(){
        int randomTabNumber = new Random().nextInt(totalTabCount - 1) + 1;
        By randomTab = By.cssSelector(".tabLink#item" + randomTabNumber);
        clickTo(randomTab);


        List<WebElement> images = driver.findElements(boutiqueImages);

        System.out.println("------------------------------------------------------");
        System.out.println(randomTabNumber + " numaralı tab random secildi ve butik resimleri kontrol ediliyor...");
        System.out.println("------------------------------------------------------");

        isImagesLoaded(images);
    }

    //Random bir butiğe git
    public void clickToRandomBoutique(){
        List<WebElement> heroBanners = driver.findElements(heroBannerSelector);
        int heroBannerCount = heroBanners.size();
        int randomBoutiqueNumber = new Random().nextInt(heroBannerCount - 1) + 1;
        heroBanners.get(randomBoutiqueNumber).click();

        try {
            clickTo(boutiqueFancyBoxButton);
            clickToRandomBoutique();
        }
        catch(Exception e) {
            System.out.println("Butik acik");
        }
    }

}





