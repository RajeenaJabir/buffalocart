package com.buffalocart.pages;

import com.buffalocart.utilities.TestHelperUtility;
import com.buffalocart.utilities.WaitUtility;
import org.openqa.selenium.PrintsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class UsersPage extends TestHelperUtility {
    WebDriver driver;

    /**page constructor**/

   public UsersPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    /**Page Elements**/

    private final String _usersPageTitle="//section[@class='content-header']";
    @FindBy(xpath = _usersPageTitle) private WebElement usersPageTitle;

    private final String _userTableRows="//table[@id='users_table']//tr";
    @FindBy(xpath=_userTableRows) private List<WebElement> userTableRows;

    private final String _userTabelColumns="//table[@id='users_table']//tr/td[@class='sorting_1']";
    @FindBy(xpath = _userTabelColumns) private  List<WebElement> userTabelColumns;

    private final String _searchButton="//input[@class='form-control input-sm']";
    @FindBy(xpath =_searchButton ) private WebElement searchButton;

    /**User action methods**/

    public String getUserPageTitle(){
      String userPageTitle = page.getElementText(usersPageTitle);
      return userPageTitle;
    }
    public void enterDataOnSearchBox(String dataToEnter) {
        page.EnterText(searchBox, dataToEnter);
    }
    public String getActualSearchData() {
        wait.waitForElementToBeVisible(driver, _userTabelColumns, WaitUtility.LocatorType.Xpath);
        List<WebElement> usersWebElementList = page.(driver, _userTabelColumns);
        String actualUserValue = page.getElementText(usersWebElementList.get(0));
        if (actualUserValue != " ") {
            System.out.println("True");
            return actualUserValue;
        } else {
            return " ";
        }


}
