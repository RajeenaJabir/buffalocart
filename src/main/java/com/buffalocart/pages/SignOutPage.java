package com.buffalocart.pages;

import com.buffalocart.utilities.TestHelperUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SignOutPage extends TestHelperUtility {
    WebDriver driver;

    /**Page Constructors**/

    public SignOutPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    /**Page Elements**/

    private final String _userName = "//a[@class='dropdown-toggle']";
    @FindBy(xpath = _userName) private WebElement userName;

    private final String _signOutButton="//div[@class='pull-right']/a[@class='btn btn-default btn-flat']";
    @FindBy(xpath = _signOutButton) private WebElement signOutButton;

    private final String _endTourButton = "//button[@class='btn btn-default btn-sm']";
    @FindBy(xpath=_endTourButton) private WebElement endTourButton;

    private final String _signOutOptions="//div[@class='pull-left' or @class='pull-right']";
    @FindBy(xpath = _signOutOptions) private List<WebElement> signOutOptions;

    /**User Actions**/
    public void clickOnUserAccountName(){
        page.clickOnElement(userName);
    }
    public void clickOnEndTourButton(){page.clickOnElement(endTourButton);}
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
