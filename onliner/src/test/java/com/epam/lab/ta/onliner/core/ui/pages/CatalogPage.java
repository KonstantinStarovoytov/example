package com.epam.lab.ta.onliner.core.ui.pages;

import com.epam.lab.ta.onliner.business_objects.MainMenu;
import com.epam.lab.ta.onliner.business_objects.NewsBlock;
import com.epam.lab.ta.onliner.service.PageCompositeElementChecker;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

public class CatalogPage extends AbstractPage{

    private final String URL = "https://catalog.onliner.by/";
    private final String SEARCH_QUERY = "IPhone X";
    private  List<String> menuItems = new ArrayList<>();
    private final String PATH_TO_MENU_BUTTONS = "//span[contains(text(),'%s')][ancestor::ul[@class='b-main-navigation']]";
    //region Locators
    @FindBy(xpath = "//div[@class='catalog-navigation']/h1[contains(@class,'catalog')]")
    private List<WebElement> catalogTitle;

    @FindBy(xpath = "//div[@id='fast-search']//input[@name='query']")
    private WebElement searchBar;

    @FindBy(xpath = "//ul[@class='search__results']/li[1]//div[@class='product__details']//a[contains(text(),'Apple iPhone X 64GB')]")
    private WebElement iPhoneX;

    @FindBy(xpath = "//iframe[@class='modal-iframe']")
    private WebElement searchModalFrame;

    @FindBy(xpath = "//a[ancestor::div[preceding-sibling::a[@href='https://www.onliner.by']] and contains(@class,'title')]")
    private List<WebElement> newsBlockLinks;
    // endregion

    @Override
    public CatalogPage openPage() {
        getDriver().get(URL);
        return this;
    }

    public boolean isLoaded() {
        return isElementPresent(catalogTitle);
    }

    public ProductPage searchAndGoIPhoneXPage() {
        searchBar.click();
        searchBar.sendKeys(SEARCH_QUERY);
        getDriver().switchTo().frame(searchModalFrame);
        iPhoneX.click();
        return new ProductPage();
    }

    public SoftAssert isMenuBarElementsLoad(MainMenu menu) {
        return PageCompositeElementChecker.checkMenuPartsPresent(menu.getButtons(),PATH_TO_MENU_BUTTONS,getDriver());
    }

    public SoftAssert checkNewsBlocksVisabilityAndText(NewsBlock newsBlock) {
        return PageCompositeElementChecker.checkNewsBlockVisibilityAndText(newsBlock.getElements(),newsBlockLinks);
    }

    public boolean checkNewsCount(NewsBlock newsBlock) {
        return PageCompositeElementChecker.checkNewsBlocksCount(newsBlock.getElements(), newsBlockLinks);
    }
}
