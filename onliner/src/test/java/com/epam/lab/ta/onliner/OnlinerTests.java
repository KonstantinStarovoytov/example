package com.epam.lab.ta.onliner;

import com.epam.lab.ta.onliner.business_objects.MainMenu;
import com.epam.lab.ta.onliner.business_objects.NewsBlock;
import com.epam.lab.ta.onliner.core.ui.driver.Driver;
import com.epam.lab.ta.onliner.core.ui.pages.AdvertisingPage;
import com.epam.lab.ta.onliner.core.ui.pages.CatalogPage;
import com.epam.lab.ta.onliner.core.ui.pages.MainPage;
import com.epam.lab.ta.onliner.core.ui.pages.ServicesPage;
import com.epam.lab.ta.onliner.service.CSVReader;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Iterator;

public class OnlinerTests {

    private static final String MENU_MODELS = "src\\test\\resources\\mainmenu.csv";
    private static final String NEWS_BLOCKS = "src\\test\\resources\\newsblocks.csv";
    private static final String BASE_URL = "https://www.onliner.by/";

    @DataProvider(name = "menuButtons")
    public Iterator<Object> menuProvider() {
        return CSVReader.getCSVIterator(MENU_MODELS);
    }

    @DataProvider(name = "newsBlocks")
    public Iterator<Object> newsBlocksProvider() {
        return CSVReader.getCSVIteratorBlocks(NEWS_BLOCKS);
    }

    @Test(description = "open catalog page")
    public void testOpenCatalogPage() {
        Boolean isCatalogPageOpen = new MainPage(BASE_URL)
                .openPage()
                .openCatalog()
                .isLoaded();
        Assert.assertTrue(isCatalogPageOpen);
    }

    @Test(description = "is menu bar loaded correct", dataProvider = "menuButtons")
    public void testMenuBarLoad(MainMenu menu)  {
        new CatalogPage()
                .openPage()
                .isMenuBarElementsLoad(menu)
                .assertAll();
    }

    @Test(description = "is main news block visible", dataProvider = "newsBlocks")
    public void testNewsBlocksOnCatalogPage(NewsBlock newsBlock){
       CatalogPage catalogPage = new CatalogPage()
               .openPage();
       Assert.assertTrue(catalogPage.checkNewsCount(newsBlock));
       catalogPage
               .checkNewsBlocksVisabilityAndText(newsBlock)
               .assertAll();

    }

    @Test(description = "search IPhone X on catalog page")
    public void testCanSearchIPhoneXOnCatalogPage() {
        Boolean isIPhoneXPageOpen = new CatalogPage()
                .openPage()
                .searchAndGoIPhoneXPage()
                .isIPhoneXPageLoaded();
        Assert.assertTrue(isIPhoneXPageOpen);
    }

    @Test(description = "open service page")
    public void testOpenServicePage(){
        Boolean isServicePageOpen = new MainPage(BASE_URL)
                .openPage()
                .openService()
                .isLoaded();
        Assert.assertTrue(isServicePageOpen);
    }

    @Test(description = "search IPhone X on service page")
    public void testCanSearchIPhoneXOnServicePage() {
        Boolean isIPhoneXPageOpen = new ServicesPage()
                .openPage()
                .searchAndGoIPhoneXPage()
                .isIPhoneXPageLoaded();
        Assert.assertTrue(isIPhoneXPageOpen);
    }

    @Test(description = "check first and last service group for visibility and text")
    public void testGroupVisibleAndHasText() {
        new ServicesPage()
                .openPage()
                .checkFirstAndLastGroup()
                .assertAll();
    }

    @Test(description = "open advertising page")
    public void testOpenAdvertisingPage(){
        Boolean isAdvertisingPageOpen = new MainPage(BASE_URL)
                .openPage()
                .openAdvertising()
                .isLoaded();
        Assert.assertTrue(isAdvertisingPageOpen);

    }

    @Test(description = "check 4 links in advertising page")
    public void testCheckLinksInGeneralBlock(){
        new AdvertisingPage()
                .openPage()
                .checkLinks()
                .assertAll();
    }

    @AfterClass(description = "stop Browser")
    public void stopBrowser() {
        Driver.getWebDriverInstance().quit();
    }
}

