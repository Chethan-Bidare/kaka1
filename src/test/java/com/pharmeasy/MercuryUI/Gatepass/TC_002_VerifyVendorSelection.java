package com.pharmeasy.MercuryUI.Gatepass;

import org.testng.annotations.Test;
import org.testng.Assert;
import com.pharmeasy.MercuryUI.Base.TestBase;
import com.pharmeasy.MercuryUI.Page.GatepassPage;
import com.pharmeasy.MercuryUI.Page.LandingPage;

public class TC_002_VerifyVendorSelection extends TestBase{

		//Verifying the Vendor is selected and clicked on Continue button
		//This method is created by Chethan Bidare on 22-01-19
	
		@Test
		public void verifyVendorSelectionInGatePassPage() throws InterruptedException {
		
			LandingPage landingPage = new LandingPage();
			landingPage.loginByCredentials(OR.getProperty("userEmail"),OR.getProperty("userPwd"));
			GatepassPage gatePassPage = new GatepassPage();
			landingPage.clickOnNewEntry();	
			landingPage.selectVendor(OR.getProperty("vendorName"));
			Assert.assertEquals(gatePassPage.getTextFromGatePassEntryDetails(),APP.getProperty("GatePassDetails")); 
		}
}
