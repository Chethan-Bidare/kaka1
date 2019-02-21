package com.pharmeasy.MercuryUI.Gatepass;

import org.testng.annotations.Test;
import org.testng.Assert;
import com.pharmeasy.MercuryUI.Base.TestBase;
import com.pharmeasy.MercuryUI.Page.GatepassPage;
import com.pharmeasy.MercuryUI.Page.LandingPage;



public class TC_001_VerifyNewEntryFunctionality extends TestBase{

	//Verifying the New Entry button click functionality 
	//This method is created by Chethan Bidare on 22-01-19
	
	@Test
	public void verifyNewEntryFunctionality() throws InterruptedException {
		LandingPage landingPage = new LandingPage();
		landingPage.loginByCredentials(OR.getProperty("userEmail"),OR.getProperty("userPwd"));
		GatepassPage gatePassPage = new GatepassPage();
		landingPage.clickOnNewEntry();
		Assert.assertEquals(gatePassPage.getTextFromvendorSelectionPage(),APP.getProperty("GatePassNewEntry"));  //After clicking New Entry button, verifying page is directed to vendor selection page
	}
	
	
	
}
