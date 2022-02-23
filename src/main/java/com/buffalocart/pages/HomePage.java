package com.buffalocart.pages;

import com.buffalocart.utilities.TestHelperUtility;
import com.buffalocart.utilities.WaitUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.List;

public class HomePage extends TestHelperUtility {
    WebDriver driver;

    /**Page Constructors**/

    public HomePage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    /**Page Elements**/

    private final String _userName = "//a[@class='dropdown-toggle']";
    @FindBy(xpath = _userName) private WebElement userName;

    private final String _homePageTitle = "//span[@class='logo-lg']";
    @FindBy(xpath = _homePageTitle) private WebElement homePageTitle;

    private final String _dateDisplayed="//div[@class='m-8 pull-left mt-15 hidden-xs']/strong";
    @FindBy(xpath = _dateDisplayed) private WebElement dateDisplayed;

    /**User Actions**/

    public String getUserAccountName(){
        String uname = page.getElementText(userName);
        return uname;
    }
    public String getHomePageTitle(){
        return  page.getPageTitle(driver);
    }
    public String getDateDisplayedOnHomePage(){
        return page.getElementText(dateDisplayed);
    }
    public String getSystemDate(){
        return date.getDateOfSystem();
    }
}
