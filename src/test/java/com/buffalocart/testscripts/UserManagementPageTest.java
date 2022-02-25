package com.buffalocart.testscripts;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.buffalocart.automationcore.Base;
import com.buffalocart.listeners.TestListeners;
import com.buffalocart.pages.HomePage;
import com.buffalocart.pages.LoginPage;
import com.buffalocart.pages.UserManagementPage;
import com.buffalocart.pages.UsersPage;
import com.buffalocart.utilities.ExcelUtility;
import com.buffalocart.utilities.TestHelperUtility;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class UserManagementPageTest extends Base {
    HomePage home;
    LoginPage login;
    UserManagementPage userManagement;
    UsersPage users;
    ExcelUtility excel = new ExcelUtility();
    ThreadLocal<ExtentTest> extentTest = TestListeners.getTestInstance();

    @Test(priority = 1, dataProvider = "AdminCredentials", description = "TC_009_Verify the Usermangement sub tabs")
    public void verify_the_Usermangement_sub_tabs(String adminName, String pswd) throws InterruptedException {
        login = new LoginPage(driver);
        List<List<String>> data = excel.excelDataReader("UserManagementPage");
        login.enterUserName(adminName);
        extentTest.get().log(Status.PASS, "User name entered successfully");
        login.enterPassword(pswd);
        extentTest.get().log(Status.PASS, "User password entered successfully");
        home = login.clickOnLoginButton();
        extentTest.get().log(Status.PASS, "Login button clicked successfully");
        home.clickOnEndTourButton();
        extentTest.get().log(Status.PASS, "End tour clicked successfully");
        userManagement = new UserManagementPage(driver);
        userManagement.clickOnUserManagementTab();
        extentTest.get().log(Status.PASS, "Clicked on user management Tab successfully");
        List<String> expectedSubTabs = new ArrayList<>();
        expectedSubTabs.add(data.get(2).get(0));
        expectedSubTabs.add(data.get(3).get(0));
        expectedSubTabs.add(data.get(4).get(0));
        List<String> actualSubTabs = userManagement.getAllUserSubManagementTab();
        Assert.assertEquals(actualSubTabs, expectedSubTabs, "Error ::SubTabs not match");
        extentTest.get().log(Status.PASS, "Actual and Expected userManagement subTabs matched");
    }

    @DataProvider(name = "AdminCredentials")
    public Object[][] adminCredentialsData() {
        Object data[][] = new Object[1][2];
        data[0][0] = "admin";
        data[0][1] = "123456";
        return data;
    }

}
