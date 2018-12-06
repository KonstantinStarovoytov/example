package com.epam.lab.ta.onliner.core.ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductPage extends AbstractPage{

    @FindBy(xpath = "//div[@class='catalog-masthead']//h1[contains(text(),'Apple iPhone X 64GB')]")
    private List<WebElement> iPhoneXPageTitle;

    @Override
    public ProductPage openPage() {
        return this;
    }

    public Boolean isIPhoneXPageLoaded(){
       return isLoad(iPhoneXPageTitle);
    }

    public Boolean isLoad(List<WebElement> elementForCheck) {
        return elementForCheck.size() > 0;
    }
}
