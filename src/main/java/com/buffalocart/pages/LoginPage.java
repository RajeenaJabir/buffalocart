package com.buffalocart.pages;

import com.buffalocart.utilities.TestHelperUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends TestHelperUtility {
    WebDriver driver;

    /**Page Constructors**/

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**Page Elements**/
    private final String _userName="username";
    @FindBy(id = _userName) private WebElement username;
    private final String _password="password";
    @FindBy(id=_password) private WebElement password;
    private final String _rememberMeCheckBox="//div[@class='checkbox']";
    @FindBy(xpath = _rememberMeCheckBox) private WebElement rememberMeCheckBox;
    private final String _loginButton="//button[@type='submit' and @class='btn btn-primary']";
    @FindBy(xpath =_loginButton) private WebElement loginButton;


    /**User Actions**/
    public String getLoginPageTitle() {
      return   page.getPageTitle(driver);
    }
    public void enterUserName(String uname){
        page.enterText(username,uname);
    }
    public void enterPassword(String pswd){
        page.enterText(password,pswd);
    }
    public void clickOnRememberMeCheckBox(){
        page.clickOnElement(rememberMeCheckBox);
    }
    public HomePage clickOnLoginButton(){
        page.clickOnElement(loginButton);
        return new HomePage(driver);
    }

}
