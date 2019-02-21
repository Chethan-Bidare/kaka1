package com.pharmeasy.MercuryUI.PurchaseEntry;

import org.testng.annotations.Test;
import org.apache.log4j.Logger;
import org.testng.Assert;
import com.pharmeasy.MercuryUI.Base.TestBase;
import com.pharmeasy.MercuryUI.Page.GatepassPage;
import com.pharmeasy.MercuryUI.Page.LandingPage;

public class TC003_VerifyNewEntryFunctionalityFromPurchase extends TestBase{

	
	public static final Logger log = Logger.getLogger(TC003_VerifyNewEntryFunctionalityFromPurchase.class.getSimpleName());
	
	//Verifying the New Entry button click functionality 
		//This method is created by Chethan Bidare on 24-01-19
		
		@Test
		public void verifyNewEntryFunctionality() throws InterruptedException {
			
			log.info(" ========== Test Started ============");
			LandingPage landingPage = new LandingPage();
			landingPage.loginByCredentials(OR.getProperty("userEmail"),OR.getProperty("userPwd"));
			Thread.sleep(5000);
			landingPage.selectMainMenuOption("Pur.Entry");
			Thread.sleep(1000);
			landingPage.selectSubMainMenuoption("Open Purchase Entries");
			GatepassPage gatePassPage = new GatepassPage();
			landingPage.clickOnNewEntry();
			/* Using gate pass page function to Assert because those function acts as global functions in all the pages */
			Assert.assertEquals(gatePassPage.getTextFromvendorSelectionPage(),APP.getProperty("GatePassNewEntry"));  //After clicking New Entry button, verifying page is directed to vendor selection page
			log.info(" =========== Test Completed =============");
		}
}
