package com.buffalocart.pages;

import com.buffalocart.utilities.TestHelperUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResetPage extends TestHelperUtility {
    WebDriver driver;

    /**Page Constructors**/

    public ResetPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**Page Elements**/
    private final String _invalidEmailId = "email";
    @FindBy(id = _invalidEmailId) private WebElement invalidEmailId;

    private final String _sendPasswordResetLinkButton="//button[@type='submit']";
    @FindBy(xpath = _sendPasswordResetLinkButton) private WebElement sendPasswordResetLinkButton;

    private final String _resetInvalidMessage="//span[@class='help-block']/strong";
    @FindBy(xpath = _resetInvalidMessage) private WebElement resetInvalidMessage;

    /**User Actions**/
    public void enterInvalidEmailId(String emailId){
        page.enterText(invalidEmailId,emailId);
    }
    public void clickOnSendPasswordResetLinkButton(){
        page.clickOnElement(sendPasswordResetLinkButton);
    }
    public String getInvalidEmailResetMessage(){
        return page.getElementText(resetInvalidMessage);
    }
}
