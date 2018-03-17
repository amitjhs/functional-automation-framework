package com.automationframework.functionaltest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class BrowserFactory {

    private static WebDriver _webDriver;

    public WebDriver launchBrowser(String _browser) {

        WebDriver _driver = null;

        switch (BrowserTypes.valueOf(_browser)) {
            case IE:
                _driver = getWebDriver(BrowserTypes.IE);
                break;

            case FireFox:
                _driver = getWebDriver(BrowserTypes.FireFox);
                break;

            case Chrome:
                _driver = getWebDriver(BrowserTypes.Chrome);
                break;

            case Edge:
                _driver = getWebDriver(BrowserTypes.Edge);
                break;

            default:
                _driver = getWebDriver(BrowserTypes.FireFox);
        }
        return _driver;
    }

    private static FirefoxDriver firefoxDriver;
    private static InternetExplorerDriver ieDriver;
    private static ChromeDriver chromeDriver;
    private static SafariDriver safariDriver;
    private static DesiredCapabilities capability = null;

    private void getFirefoxDriver() {
        if (_webDriver == null) {
            String Path = getClass().getClassLoader().getResource("browsers/geckodriver.exe").getPath();
            System.setProperty("webdriver.gecko.driver", Path);
            _webDriver = new FirefoxDriver();
        }
    }

    private void getIEDriver() {
        if (_webDriver == null) {
            String Path = getClass().getClassLoader().getResource("browsers/IEDriverServer.exe").getPath();
            System.setProperty("webdriver.ie.driver", Path);
            DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
            capabilities.setCapability("unexpectedAlertBehaviour", "ignore");
            _webDriver = new InternetExplorerDriver(capabilities);
        }
    }

    private void getEdgeDriver() {
        if (_webDriver == null) {
            String Path = getClass().getClassLoader().getResource("browsers/MicrosoftWebDriver.exe").getPath();
            System.setProperty("webdriver.edge.driver", Path);
            _webDriver = new EdgeDriver();

        }
    }

    private void getChromeDriver() {
        if (_webDriver == null) {
            String Path = getClass().getClassLoader().getResource("browsers/chromedriver.exe").getPath();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("chrome.switches", "--disable-extensions");
            System.setProperty("webdriver.chrome.driver", Path);
            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            capabilities.setCapability("chrome.switches", Arrays.asList("--start-maximized"));
            _webDriver = new ChromeDriver(options);
        }
    }

    private void getSafariDriver() {
        if (safariDriver == null) {
            DesiredCapabilities capabilities = DesiredCapabilities.safari();
            capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, "ignore");

            _webDriver = new SafariDriver(capabilities);
        }
    }

    private WebDriver getWebDriver(BrowserTypes driverType) {

        if (_webDriver != null) {
            return _webDriver;

        } else {

            setWebDriver(driverType);
        }
        return _webDriver;
    }

    private void setWebDriver(BrowserTypes driverType) {

        switch (driverType) {
            case FireFox:
                getFirefoxDriver();
                break;

            case IE:
                getIEDriver();
                break;

            case Chrome:
                getChromeDriver();
                break;

            case Edge:
                getEdgeDriver();
                break;

        }
    }

    public void releaseWebDriver(WebDriver driver) {
        driver.quit();
        if (_webDriver.toString().contains("InternetExplorerDriver")) {
            try {
                Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        _webDriver = null;

    }

    public static String getTitle(WebDriver driver) {
        return driver.getTitle();
    }

}