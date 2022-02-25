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

    private final String _userAccountName = "//a[@class='dropdown-toggle']";
    @FindBy(xpath = _userAccountName) private WebElement userAccountName;

    private final String _signOutButton = "//div[@class='pull-right']/a[@class='btn btn-default btn-flat']";
    @FindBy(xpath = _signOutButton) private WebElement signOutButton;

    private final String _endTourButton = "//button[@class='btn btn-default btn-sm']";
    @FindBy(xpath = _endTourButton) private WebElement endTourButton;

    private final String _dropdownButton = "//div[@class='navbar-custom-menu']";
    @FindBy(xpath =_dropdownButton) private List<WebElement> dropdownButton;

    private final String _signOutOptions = "//li[@class='user-footer']";
    @FindBy(xpath = _signOutOptions) private List<WebElement> signOutOptions;

    /**User Actions Methods**/

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

    public void clickOnUserAccountName() {
        page.clickOnElement(userName);
    }

    public void clickOnEndTourButton() {
        page.clickOnElement(endTourButton);
    }

    public LoginPage clickOnUserAccountName(String text) {
        for (int i = 0; i < dropdownButton.size(); i++) {
            String value = dropdownButton.get(i).getText();
            if (value.equals(text)) {
                page.clickOnElement(dropdownButton.get(i));
                break;
            }
        }
        return new LoginPage(driver);
    }

    public LoginPage selectFromDropDownForSignOut(String text) {
        for (int i = 0; i < signOutOptions.size(); i++) {
            String value = signOutOptions.get(i).getText();
            if (value.equals(text)) {
                page.clickOnElement(signOutOptions.get(i));
                break;
            }
        }
        return new LoginPage(driver);
    }

}

