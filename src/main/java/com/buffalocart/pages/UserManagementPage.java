package com.buffalocart.pages;

import com.buffalocart.automationcore.Base;
import com.buffalocart.utilities.TestHelperUtility;
import com.buffalocart.utilities.WaitUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class UserManagementPage extends TestHelperUtility {
    WebDriver driver;
    /**page constructor**/
    public UserManagementPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    /**Page Elements**/

    private final String _userManagementAllTabs="//ul[@class='treeview-menu menu-open']//span";
    @FindBy(xpath = _userManagementAllTabs) private List<WebElement> userManagementAllTabs;

    private final String _userMgmtTabs2="//li[starts-with(@class,'treeview')]//ul[@class='treeview-menu menu-open']//span";
    @FindBy(xpath = _userMgmtTabs2) private List<WebElement> userMgmtTabs2;

    private final String _userManagementTab="//li[starts-with(@class,'treeview')]//a[@href='#']//span[@class='title']";
    @FindBy(xpath = _userManagementTab) private WebElement userManagementTab;

    /**User action methods**/

    public void clickOnUserManagementTab() {
       // wait.waitForElementToBeVisible(driver, _userManagementAllTabs, WaitUtility.LocatorType.Xpath);
        page.clickOnElement(userManagementTab);
    }
    public List<String> getAllUserSubManagementTab() {
        wait.waitForElementToBeVisible(driver, _userManagementAllTabs, WaitUtility.LocatorType.Xpath);
        List<String> subTabValues = new ArrayList<>();
        for (int i = 0; i < userManagementAllTabs.size(); i++) {
            subTabValues.add(userManagementAllTabs.get(i).getText());
        }
        return subTabValues;
    }
    public UsersPage selectTabFromUserManagement(String userText) {
        wait.waitForElementToBeVisible(driver, _userMgmtTabs2, WaitUtility.LocatorType.Xpath);
        for (int j = 0; j < userMgmtTabs2.size(); j++) {
            String value=userMgmtTabs2.get(j).getText();
            if (value.equals(userText)) {
                userMgmtTabs2.get(j).click();
                break;
            }
        }
        return  new UsersPage(driver);
    }
}
