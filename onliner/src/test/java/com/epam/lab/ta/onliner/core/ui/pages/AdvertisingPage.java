package com.epam.lab.ta.onliner.core.ui.pages;

import com.epam.lab.ta.onliner.core.ui.elements.MenuBar;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class AdvertisingPage extends AbstractPage {

    private final String URL = "https://b2breg.onliner.by/advertising";

    @FindBy(xpath = "//header//a[contains(@href,'7755080')]")
    private List<WebElement> advertisingHeaderPhones;

    @FindBy(xpath = "//a[ancestor::div[@class='content']]")
    private List<WebElement> generalLinks;


    public AdvertisingPage() {
    }

    @Override
    public AdvertisingPage openPage() {
        getDriver().get(URL);
        return this;
    }

    public boolean isLoaded() {
        return isElementPresent(advertisingHeaderPhones);
    }

    public SoftAssert checkLinks() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(generalLinks.size() == 4);
        for (int i = 0; i<=generalLinks.size()-1; i++){
            softAssert.assertTrue(generalLinks.get(i).getAttribute("href").contains("https://"));
            softAssert.assertFalse(generalLinks.get(i).getText().isEmpty());
        }
        return softAssert;

    }
}
