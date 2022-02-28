package com.buffalocart.pages;

import com.buffalocart.utilities.TestHelperUtility;
import com.buffalocart.utilities.WaitUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.PrintsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UsersPage extends TestHelperUtility {
    WebDriver driver;

    /**page constructor**/

    public UsersPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**Page Elements**/

    private final String _usersPageTitle = "//section[@class='content-header']";
    @FindBy(xpath = _usersPageTitle)
    private WebElement usersPageTitle;

    private final String _userTableRows = "//table[@id='users_table']//tr";
    @FindBy(xpath = _userTableRows) private List<WebElement> userTableRows;

    private final String _userTabelColumns = "//table[@id='users_table']//tr//td[@class='sorting_1']";
    @FindBy(xpath = _userTabelColumns) private List<WebElement> userTabelColumns;

    private final String _searchField = "//input[@class='form-control input-sm']";
    @FindBy(xpath = _searchField) private WebElement searchField;

    /**User action methods**/

    public String getUserPageTitle() {
        String userPageTitle = page.getElementText(usersPageTitle);
        return userPageTitle;
    }

    public void enterTextToSearchField(String value) {
        page.enterText(searchField, value);
    }

    public List<String> searchValidData(String text){
        List<String> actData = new ArrayList<>();
        wait.waitForElementToBeVisible(driver, _userTabelColumns, WaitUtility.LocatorType.Xpath);
        for (int i = 1; i < userTableRows.size(); i++) {
           List<WebElement> userTabelColumns = driver.findElements(By.xpath("//table[@id='users_table']//tr[" + i + "]//td"));
            wait.waitForElementToBeVisible(driver, _userTabelColumns, WaitUtility.LocatorType.Xpath);
           String cellValue = userTabelColumns.get(0).getText();
            if (cellValue.equals(text)) {
                for (int j = 0; j < userTabelColumns.size(); j++) {
                    wait.waitForElementToBeVisible(driver, _userTabelColumns, WaitUtility.LocatorType.Xpath);
                    actData.add(userTabelColumns.get(j).getText());
                }
            }
        }
        return actData;
    }
}