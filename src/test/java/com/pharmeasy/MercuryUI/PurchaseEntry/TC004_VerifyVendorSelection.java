package com.pharmeasy.MercuryUI.PurchaseEntry;

import org.testng.annotations.Test;
import org.apache.log4j.Logger;
import org.testng.Assert;

import com.pharmeasy.MercuryUI.Base.TestBase;
import com.pharmeasy.MercuryUI.Page.LandingPage;
import com.pharmeasy.MercuryUI.Page.PurchaseEntryPage;

public class TC004_VerifyVendorSelection extends TestBase{
	
	public static final Logger log = Logger.getLogger(TC004_VerifyVendorSelection.class.getSimpleName());
	
	@Test
	public void verifyVendorSelection() throws InterruptedException {
		log.info(" ========== Test Started ============");
		LandingPage landingPage = new LandingPage();
		PurchaseEntryPage purchaseEntry = new PurchaseEntryPage();
		landingPage.loginByCredentials(OR.getProperty("userEmail"),OR.getProperty("userPwd"));
		Thread.sleep(5000);
		landingPage.selectMainMenuOption("Pur.Entry");
		Thread.sleep(1000);
		landingPage.selectSubMainMenuoption("Open Purchase Entries");
		landingPage.clickOnNewEntry();
		landingPage.selectVendor(OR.getProperty("vendorName"));
		Thread.sleep(1000);
		Assert.assertEquals(purchaseEntry.getHeaderNameFromPurchaseEntry(),OR.getProperty("vendorName"));
		
		log.info("======= Test Completed ========");
	}

}
