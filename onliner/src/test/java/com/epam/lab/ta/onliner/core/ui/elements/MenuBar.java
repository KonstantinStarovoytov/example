package com.epam.lab.ta.onliner.core.ui.elements;

import com.epam.lab.ta.onliner.core.ui.pages.AbstractPage;
import com.epam.lab.ta.onliner.core.ui.pages.CatalogPage;
import com.epam.lab.ta.onliner.core.ui.pages.ServicesPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MenuBar extends AbstractPage{

    private WebElement menuBar;


    private String catalogPath= "/li/a[contains(@href,'catalog')]";
    private WebElement catalog = menuBar.findElement(By.xpath(catalogPath));

    private By service = By.partialLinkText("s.onliner");

    public MenuBar(WebElement menuBar) throws Exception {
        super();
        this.menuBar = menuBar;
    }

    public CatalogPage clickCatalog() throws Exception {
        catalog.click();
        return new CatalogPage();
    }

    public ServicesPage clickService(){
        WebElement catalog = menuBar.findElement(this.service);
        return new ServicesPage();
    }


    @Override
    public AbstractPage openPage() {
        return null;
    }
}
