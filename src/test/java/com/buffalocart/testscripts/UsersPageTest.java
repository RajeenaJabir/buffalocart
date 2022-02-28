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
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UsersPageTest extends Base {
    HomePage home;
    LoginPage login;
    UserManagementPage userManagement;
    UsersPage users;
    ExcelUtility excel = new ExcelUtility();
    ThreadLocal<ExtentTest> extentTest = TestListeners.getTestInstance();


    @DataProvider(name = "AdminCredentials")
    public Object[][] adminCredentialsData() {
        Object data[][] = new Object[1][2];
        data[0][0] = "admin";
        data[0][1] = "123456";
        return data;
    }

    @Test(priority = 1, dataProvider = "AdminCredentials", description = "TC_010_Verify Users page title")
    public void verify_the_Users_page_title(String adminName, String pswd)  {
        login = new LoginPage(driver);
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
        List<List<String>> data = excel.excelDataReader("UserManagementPage");
        String use=data.get(2).get(0);
        users =userManagement.selectTabFromUserManagement(use);
        String actualUserPageTitle=users.getUserPageTitle();
        String expectedUserPageTitle=data.get(1).get(1);
        Assert.assertEquals(actualUserPageTitle,expectedUserPageTitle,"ERROR::Invalid Title");
        extentTest.get().log(Status.PASS, "Actual and Expected user page title matched");
    }
    @Test(priority =1,description = "TC_011_Verify user search with valid data")
    public void verify_User_Search_With_Valid_Data(){
        login = new LoginPage(driver);
        List<List<String>> dataLoginPage = excel.excelDataReader("LoginPage");
        String uname = dataLoginPage.get(1).get(1);
        login.enterUserName(uname);
        String psd = dataLoginPage.get(1).get(2);
        login.enterPassword(psd);
        home = login.clickOnLoginButton();
        home.clickOnEndTourButton();
        userManagement = new UserManagementPage(driver);
        userManagement.clickOnUserManagementTab();
        extentTest.get().log(Status.PASS, "Clicked on user management Tab successfully");
        List<List<String>> dataUserManagementPage = excel.excelDataReader("UserManagementPage");
        String use = dataUserManagementPage.get(2).get(0);
        users = userManagement.selectTabFromUserManagement(use);
        List<List<String>> dataUsersPage = excel.excelDataReader("UsersPage");
        String searchText = dataUsersPage.get(1).get(0);
        users.enterTextToSearchField(searchText);
        extentTest.get().log(Status.PASS, "Search Data entered successfully");
        List<String> actualResult=users.searchValidData(searchText);
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add(dataUsersPage.get(1).get(0));
        expectedResult.add(dataUsersPage.get(1).get(1));
        expectedResult.add(dataUsersPage.get(1).get(2));
        expectedResult.add(dataUsersPage.get(1).get(3));
        expectedResult.add(dataUsersPage.get(1).get(4));
        Assert.assertEquals(actualResult,expectedResult,"ERROR ::Invalid data");
        extentTest.get().log(Status.PASS, "Actual and Expected user detailes matched");
    }
}
