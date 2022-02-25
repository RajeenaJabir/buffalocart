package com.buffalocart.testscripts;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.buffalocart.automationcore.Base;
import com.buffalocart.listeners.TestListeners;
import com.buffalocart.pages.HomePage;
import com.buffalocart.pages.LoginPage;
import com.buffalocart.utilities.ExcelUtility;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class HomePageTest extends Base {
    HomePage home;
    LoginPage login;
    ExcelUtility excel = new ExcelUtility();
    ThreadLocal<ExtentTest> extentTest = TestListeners.getTestInstance();

    @Test(priority = 1,description = "TC_006_Verify home page tilte")
    public void verify_Home_Page_Title(){
        login=new LoginPage(driver);
        List<List<String>> data=excel.excelDataReader("HomePage");
        String uname=data.get(1).get(1);
        login.enterUserName(uname);
        String pswd=data.get(1).get(2);
        login.enterPassword(pswd);
        home=login.clickOnLoginButton();
        String actualHomePageTitle=home.getHomePageTitle();
        extentTest.get().log(Status.PASS, "Home page title recieved");
        String expectedHomePageTitle=data.get(1).get(0);
        Assert.assertEquals(actualHomePageTitle,expectedHomePageTitle,"ERROR ::Invalid Title");
        extentTest.get().log(Status.PASS, "Expected title is matched with actual home page title");
    }
    @Test(priority = 2,dataProvider ="AdminCredentials",description = "TC_007_Verify date displayed in home page")
    public void verify_date_displayed_in_home_page(String adminName,String pswd){
        login=new LoginPage(driver);
        List<List<String>> data=excel.excelDataReader("HomePage");
        login.enterUserName(adminName);
        login.enterPassword(pswd);
        home=login.clickOnLoginButton();
        String actualDate=home.getDateDisplayedOnHomePage();
        String expectedDate= home.getSystemDate();
        Assert.assertEquals(actualDate,expectedDate,"ERROR ::Not current Date");
    }
    @DataProvider(name = "AdminCredentials")
    public Object[][] adminCredentialsData() {
        Object data[][] = new Object[1][2];
        data[0][0] = "admin";
        data[0][1] = "123456";
        return data;
    }
    @Test(priority = 3,dataProvider ="AdminCredentials",description ="TC_008_Verify whether user is navigating to login page by clicking on Sign out button")
    public void  verify_Whether_User_Is_Navigating_To_Login_Page_By_Clicking_On_Sign_Out_Button(String adminName,String pswd){
        extentTest.get().assignCategory("Smoke");
        login = new LoginPage(driver);
        List<List<String>> data = excel.excelDataReader("HomePage");
        login.enterUserName(adminName);
        extentTest.get().log(Status.PASS, "User name entered successfully");
        login.enterPassword(pswd);
        extentTest.get().log(Status.PASS, "Password entered successfully");
        home = login.clickOnLoginButton();
        extentTest.get().log(Status.PASS, "clicked on login button successfully");
        home.clickOnEndTourButton();
        extentTest.get().log(Status.PASS, "clicked on EndTour button successfully");
        String dropdown=data.get(1).get(4);
        login=home.clickOnUserAccountName(dropdown);
        extentTest.get().log(Status.PASS, "clicked on userAccount Name successfully");
        String option=data.get(1).get(3);
        login=home.selectFromDropDownForSignOut(option);
        extentTest.get().log(Status.PASS, "clicked on SignOut button successfully");
    }
}
