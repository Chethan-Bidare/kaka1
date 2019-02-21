package com.pharmeasy.MercuryUI.Gatepass;

import org.testng.annotations.Test;
import org.testng.Assert;
import com.pharmeasy.MercuryUI.Base.TestBase;
import com.pharmeasy.MercuryUI.Page.GatepassPage;
import com.pharmeasy.MercuryUI.Page.LandingPage;

public class TC_007_VerifyErrorMessageForVendorSelection extends TestBase{

	
	//Created by Chethan Bidare on 22-01-19
	
	
	@Test
	public void verifyErrorMSGforVendor() throws InterruptedException {
		LandingPage landingPage = new LandingPage();
		landingPage.loginByCredentials(OR.getProperty("userEmail"),OR.getProperty("userPwd"));
		GatepassPage gatePassPage = new GatepassPage();
		landingPage.clickOnNewEntry();
		Thread.sleep(2000);
		landingPage.clickOnContinueButton();
		Thread.sleep(2000);
		
		Assert.assertEquals(gatePassPage.getToastMSG(), APP.getProperty("vendorMSG"));
	}
}
