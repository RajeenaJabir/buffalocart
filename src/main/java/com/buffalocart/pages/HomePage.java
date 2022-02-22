package com.buffalocart.pages;

import com.buffalocart.utilities.TestHelperUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends TestHelperUtility {
    WebDriver driver;
    /**Page Constructors**/
    public HomePage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    /**Page Elements**/
    private final String _userName="//a[@class='dropdown-toggle']//span";
    @FindBy(xpath = _userName) private WebElement userName;

    /**User Actions**/
    public String getUserAccountName(){
        String uname = page.getElementText(userName);
        return uname;
    }
}
