package com.epam.lab.ta.onliner.core.ui.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.epam.lab.ta.onliner.common.exception.UnknownDriverTypeException;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Driver {

    private Driver(){}
    private static final String DEFAULT_WEB_DRIVER = "DEFAULT_WEB_DRIVER";
    private static final int IMPLICIT_WAIT_TIMEOUT = 30;
    private static final int PAGELOAD_TIMEOUT = 10;

    private static WebDriverTypes defaultDriverType = WebDriverTypes.CHROME;


    private static Map<String, WebDriver> instances;

    static {
        instances = new HashMap<String, WebDriver>();
    }

    public static WebDriver getWebDriverInstance(String name, WebDriverTypes type)  {
        WebDriver driver;
        if (!instances.containsKey(name)) {
            switch (type) {
                case FIREFOX: {
                    driver = new FirefoxDriver();
                    break;
                }
                case IE: {
                    System.setProperty("webdriver.ie.driver", "src/test/resources/driverbinaries/IEDriverServer.exe");
                    driver = new InternetExplorerDriver();
                    break;
                }
                case CHROME: {
                    System.setProperty("webdriver.chrome.driver", "src/test/resources/driverbinaries/chromedriver.exe");
                    driver = new ChromeDriver();
                    break;
                }
                default:
                    throw new UnknownDriverTypeException("Unknown web driver specified: " + type);
            }
            driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(PAGELOAD_TIMEOUT,TimeUnit.SECONDS);
            driver.manage().window().maximize();
            instances.put(name, driver);
        } else {
            driver = instances.get(name);
        }
        return driver;
    }

    public static WebDriver getWebDriverInstance(String name) throws Exception {
        return getWebDriverInstance(name, defaultDriverType);
    }

    public static WebDriver getWebDriverInstance() {
        return getWebDriverInstance(DEFAULT_WEB_DRIVER, defaultDriverType);
    }

    public static void setDefaultWebDriverType(WebDriverTypes type) {
        defaultDriverType = type;
    }

}