package com.buffalocart.pages;

import com.buffalocart.utilities.TestHelperUtility;
import org.openqa.selenium.PrintsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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


    /**User action methods**/

    public String getUserPageTitle(){
      String userPageTitle = page.getElementText(usersPageTitle);
      return userPageTitle;
    }
}
