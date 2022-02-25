package com.buffalocart.testscripts;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.buffalocart.automationcore.Base;
import com.buffalocart.listeners.TestListeners;
import com.buffalocart.pages.HomePage;
import com.buffalocart.pages.LoginPage;
import com.buffalocart.pages.ResetPage;
import com.buffalocart.utilities.ExcelUtility;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class LoginPageTest extends Base {
    LoginPage login;
    HomePage home;
    ResetPage reset;
    ExcelUtility excel = new ExcelUtility();
    ThreadLocal<ExtentTest> extentTest = TestListeners.getTestInstance();

    @Test(priority = 1,description ="TC_001_Verify Login Page title")
    public void verifyLoginPageTitle(){
        extentTest.get().assignCategory("Smoke");
        login = new LoginPage(driver);
        List<List<String>> data=excel.excelDataReader("LoginPage");
        String expectedTitle= data.get(1).get(0);
        String actualTitle= login.getLoginPageTitle();
        extentTest.get().log(Status.PASS, "Login page title recieved");
        Assert.assertEquals(actualTitle,expectedTitle,"ERROR ::Invalid Title");
        extentTest.get().log(Status.PASS, "Expected title is matched with actual home page title");
    }
    @Test(priority = 2,description = "TC_002_Verify user login with valid user credentials")
    public void verifyUserLoginWithValidUserCredentials(){
        login = new LoginPage(driver);
        List<List<String>> data=excel.excelDataReader("LoginPage");
        String uname=data.get(1).get(1);
        login.enterUserName(uname);
        String psd=data.get(1).get(2);
        login.enterPassword(psd);
        home=login.clickOnLoginButton();
        String actualUserAccountName=home.getUserAccountName();
        String expectedUserAccountName=data.get(1).get(3);
        Assert.assertEquals(actualUserAccountName,expectedUserAccountName,"ERROR ::Invalid user");
        extentTest.get().log(Status.PASS, "User name entered successfully");
        extentTest.get().log(Status.PASS, "Password entered successfully");
        extentTest.get().log(Status.PASS, "clicked on login button successfully");
        extentTest.get().log(Status.PASS, "user logged in successfully");
    }

    @Test(priority = 3,description = "TC_003_Verify the error message displayed for user login with invalid credentials")
    public void verifyUserLoginWithInValidUserCredentials(){
        login = new LoginPage(driver);
        List<List<String>> data=excel.excelDataReader("LoginPage");
        String uname=data.get(2).get(1);
        login.enterUserName(uname);
        String psd=data.get(2).get(2);
        login.enterPassword(psd);
        home=login.clickOnLoginButton();
        String actualErrorMessage=login.getErrorMessage();
        String expectedUserAccountName=data.get(1).get(4);
        Assert.assertEquals(actualErrorMessage,expectedUserAccountName,"ERROR ::Invalid user");
        extentTest.get().log(Status.PASS, "invalid User name entered successfully");
        extentTest.get().log(Status.PASS, "invalid Password entered successfully");
        extentTest.get().log(Status.PASS, "clicked on login button successfully");
        extentTest.get().log(Status.PASS, "Expected error message matched with actual error message ");
    }
    @Test(priority = 4,description = "TC_004_Verify whetehr the user is able to click on 'Remember me' checkbox")
    public void verify_Whether_User_Able_To_Click_On_RemememberMe_CheckBox(){
        login = new LoginPage(driver);
        login.clickOnRememberMeCheckBox();
        extentTest.get().log(Status.PASS, "clicked on CheckBox successfully");
        login.rememberMeCheckBoxIsSelected();
        extentTest.get().log(Status.PASS, "CheckBox  selected successfully");
        Assert.assertTrue(login.rememberMeCheckBoxIsSelected());
        extentTest.get().log(Status.PASS, "Assertion True for checkbox selected ");
    }
    @Test(priority=5,description = "TC_005_Verify error meesage displyed on  Reset Password page with invalid email id")
    public void Verify_Error_Meesage_Displyed_On_Reset_Password_page_with_invalid_email_id(){
        login =new LoginPage(driver);
        List<List<String>> data=excel.excelDataReader("ResetPage");
        reset=login.clickOnForgotPassword();
        extentTest.get().log(Status.PASS, "clicked on Forgot Password successfully");
        String emailid=data.get(1).get(1);
        reset.enterInvalidEmailId(emailid);
        extentTest.get().log(Status.PASS, "Invalid Email Id entered successfully");
        reset.clickOnSendPasswordResetLinkButton();
        extentTest.get().log(Status.PASS, "clicked on resend password button successfully");
        String actualResetMessage= reset.getInvalidEmailResetMessage();
        extentTest.get().log(Status.PASS, "Invalid message received successfully");
        String expectedResetMessage=data.get(1).get(2);
        Assert.assertEquals(actualResetMessage,expectedResetMessage,"ERROR ::Message Mismatch");
        extentTest.get().log(Status.PASS, "Expected error message matched with actual error message ");
    }
    @DataProvider(name = "AdminCredentials")
    public Object[][] adminCredentialsData() {
        Object data[][] = new Object[1][2];
        data[0][0] = "admin";
        data[0][1] = "123456";
        return data;
    }
}
