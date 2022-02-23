package com.buffalocart.testscripts;

import com.aventstack.extentreports.ExtentTest;
import com.buffalocart.automationcore.Base;
import com.buffalocart.listeners.TestListeners;
import com.buffalocart.pages.HomePage;
import com.buffalocart.pages.LoginPage;
import com.buffalocart.pages.SignOutPage;
import com.buffalocart.utilities.ExcelUtility;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class SignOutPageTest extends Base {
    HomePage home;
    LoginPage login;
    SignOutPage signout;
    ExcelUtility excel = new ExcelUtility();
    ThreadLocal<ExtentTest> extentTest = TestListeners.getTestInstance();

    @DataProvider(name = "AdminCredentials")
    public Object[][] adminCredentialsData() {
        Object data[][] = new Object[1][2];
        data[0][0] = "admin";
        data[0][1] = "123456";
        return data;
    }
    @Test(priority = 3,dataProvider ="AdminCredentials",description ="TC_008_Verify whether user is navigating to login page by clicking on Sign out button")
    public void  verify_Whether_User_Is_Navigating_To_Login_Page_By_Clicking_On_Sign_Out_Button(String adminName,String pswd){
        login=new LoginPage(driver);
        login.enterUserName(adminName);
        login.enterPassword(pswd);
        home=login.clickOnLoginButton();
        signout.clickOnEndTourButton();
        List<List<String>> data=excel.excelDataReader("HomePage");
        String option=data.get(0).get(3);
        login=signout.selectFromDropDownForSignOut(option);
    }
}
