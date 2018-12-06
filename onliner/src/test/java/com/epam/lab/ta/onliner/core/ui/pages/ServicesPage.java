package com.epam.lab.ta.onliner.core.ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class ServicesPage extends AbstractPage{

    private final String SEARCH_QUERY = "IPhone X";
    private final String URL = "https://s.onliner.by/tasks";
    //div[@property='sections' and contains(@class,'service-form')] parent

    @FindBy(xpath = "//div[1][contains(@data-ng-repeat,'searchFilterSections.categories')][ancestor::div[@property='sections' and contains(@class,'service-form')]]//a")
    private List<WebElement> firstGroup;

    @FindBy(xpath = "//div[last()][contains(@data-ng-repeat,'searchFilterSections.categories')][ancestor::div[@property='sections' and contains(@class,'service-form')]]//a")
    private List<WebElement> lastGroup;

    @FindBy(xpath = "//a[contains(@href,'tasks') and contains(@ui-sref,'tasks')]")
    private List<WebElement> taskLink;
    @FindBy(xpath = "//a[contains(@href,'profiles') and contains(@ui-sref,'profiles')]")
    private List<WebElement> profilesLink;

    @CacheLookup
    @FindBy(xpath = "//div[@id='fast-search']//input[@name='query']")
    private WebElement searchBar;

    @CacheLookup
    @FindBy(xpath = "//iframe[@class='modal-iframe']")
    private WebElement searchModalFrame;

    @CacheLookup
    @FindBy(xpath = "//ul[@class='search__results']/li[1]//div[@class='product__details']//a[contains(text(),'Apple iPhone X 64GB')]")
    private WebElement iPhoneX;


    @Override
    public ServicesPage openPage() {
        getDriver().get(URL);
        return this;
    }

    public boolean isLoaded() {
        if(isElementPresent(taskLink) && isElementPresent(profilesLink))
        return true;
        else return false;
    }


    public ProductPage searchAndGoIPhoneXPage() {
        searchBar.click();
        searchBar.sendKeys(SEARCH_QUERY);
        getDriver().switchTo().frame(searchModalFrame);
        iPhoneX.click();
        return new ProductPage();
    }

    public SoftAssert checkFirstAndLastGroup() {
        SoftAssert softAssert = new SoftAssert();
        if(isElementPresent(firstGroup) && isElementPresent(lastGroup)){
            softAssert.assertTrue(firstGroup.get(0).isDisplayed());
            softAssert.assertTrue(lastGroup.get(0).isDisplayed());
            softAssert.assertFalse(firstGroup.get(0).getText().isEmpty());
            softAssert.assertFalse(lastGroup.get(0).getText().isEmpty());
        }
        else softAssert.assertTrue(false);
        return softAssert;
    }
}
