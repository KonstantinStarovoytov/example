package com.epam.lab.ta.onliner.service;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class PageCompositeElementChecker {

    private static SoftAssert softAssert = new SoftAssert();

    public static SoftAssert checkMenuPartsPresent(List<String> menuButtons, String pathToButtons, WebDriver driver){
        for(int i =0; i<=menuButtons.size()-1; i++){
            String menuItem= String.format(pathToButtons,menuButtons.get(i));
            List<WebElement> menuItemWebElement= driver.findElements(By.xpath(menuItem));
            softAssert.assertTrue(menuItemWebElement.size()>0);
        }
            return softAssert;
    }

    public static SoftAssert checkNewsBlockVisibilityAndText(List<String> newsBlockNames, List<WebElement> newsBlockLink){
        SoftAssert softAssert = new SoftAssert();
        for(int i =0; i<=newsBlockLink.size()-1; i++){
            softAssert.assertTrue(newsBlockLink.get(i).isDisplayed());
            softAssert.assertFalse(newsBlockLink.get(i).getText().isEmpty());
        }
        return softAssert;
    }

    public static boolean checkNewsBlocksCount(List<String> newsBlockNames, List<WebElement> newsBlockLink){
        return newsBlockNames.size() == newsBlockLink.size();
    }

}
