package com.buffalocart.testscripts;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.TestListener;
import com.buffalocart.automationcore.Base;
import com.buffalocart.listeners.TestListeners;
import com.buffalocart.pages.HomePage;
import com.buffalocart.pages.LoginPage;
import com.buffalocart.utilities.ExcelUtility;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class LoginPageTest extends Base {
    LoginPage login;
    HomePage home;
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

}
