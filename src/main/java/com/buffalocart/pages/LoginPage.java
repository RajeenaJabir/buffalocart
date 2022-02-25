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

    private final String _rememberMeCheckBox="//div[@class='checkbox']//input[@type='checkbox']";
    @FindBy(xpath = _rememberMeCheckBox) private WebElement rememberMeCheckBox;

    private final String _loginButton="//button[@type='submit' and @class='btn btn-primary']";
    @FindBy(xpath =_loginButton) private WebElement loginButton;

    private final String _errorMessage="//span[@class='help-block']";
    @FindBy(xpath =_errorMessage) private WebElement errorMessage;

    private final String _forgotPassword="//a[@class='btn btn-link']";
    @FindBy(xpath = _forgotPassword) private WebElement forgotPassword;

    private final String _endTourButton = "//button[@class='btn btn-default btn-sm']";
    @FindBy(xpath=_endTourButton) private WebElement endTourButton;

    /**User action methods**/

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
    public boolean rememberMeCheckBoxIsSelected(){
     return page.isSelected(rememberMeCheckBox);
    }

    public HomePage clickOnLoginButton(){
        page.clickOnElement(loginButton);
        return  new HomePage(driver);
    }
    public String getErrorMessage(){
        return   page.getElementText(errorMessage);
    }
    public ResetPage clickOnForgotPassword(){
        page.clickOnElement(forgotPassword);
        return new ResetPage(driver);
    }

}
