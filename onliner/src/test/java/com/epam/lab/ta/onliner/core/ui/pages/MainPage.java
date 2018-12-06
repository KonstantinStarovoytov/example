package com.epam.lab.ta.onliner.core.ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends AbstractPage {

    @FindBy(xpath = "//ul[@class='b-main-navigation']//a[contains(@href,'catalog')]")
    private WebElement catalog;

    @FindBy(xpath = "//ul[@class='b-main-navigation']//a[contains(@href,'s.onliner')]")
    private WebElement service;

    @FindBy(xpath = "//footer//a[contains(@href,'advertising')]")
    private WebElement advertising;

    private String URL;

    public MainPage(String URL) {
        super();
        this.URL = URL;
    }

    @Override
    public MainPage openPage() {
        getDriver().get(URL);
        return this;
    }

    public CatalogPage openCatalog() {
        catalog.click();
      return new CatalogPage();
    }

    public ServicesPage openService() {
        service.click();
        return new ServicesPage();
    }

    public boolean isLoaded() {
        return true;
    }


    public AdvertisingPage openAdvertising() {
        advertising.click();
        return new AdvertisingPage();
    }
}
