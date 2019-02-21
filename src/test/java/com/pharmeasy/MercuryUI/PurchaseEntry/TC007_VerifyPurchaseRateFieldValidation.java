package com.pharmeasy.MercuryUI.PurchaseEntry;

import org.testng.annotations.Test;
import org.apache.log4j.Logger;
import org.testng.Assert;
import com.pharmeasy.MercuryUI.Base.TestBase;
import com.pharmeasy.MercuryUI.Page.LandingPage;
import com.pharmeasy.MercuryUI.Page.PurchaseEntryPage;

public class TC007_VerifyPurchaseRateFieldValidation extends TestBase{

	public static final Logger log = Logger.getLogger(TC007_VerifyPurchaseRateFieldValidation.class.getSimpleName());
	
	@Test
	public void verifyPurchaseRateFieldValidation() throws InterruptedException {
		log.info(" ====== Test Started ======");
		LandingPage landingPage = new LandingPage();
		PurchaseEntryPage purchaseEntry = new PurchaseEntryPage();
		landingPage.loginByCredentials(OR.getProperty("userEmail"),OR.getProperty("userPwd"));
		Thread.sleep(5000);
		landingPage.selectMainMenuOption("Pur.Entry");
		Thread.sleep(1000);
		landingPage.selectSubMainMenuoption("Open Purchase Entries");
		landingPage.clickOnNewEntry();
		landingPage.selectVendor(OR.getProperty("vendorName"));
		purchaseEntry.enterInvoiceNumber("SI-897");
		purchaseEntry.enterItemDetailsWithoutBatchCreation(OR.getProperty("testItem"),"5","39");
		Thread.sleep(1000);
		float purRate = Integer.parseInt(String.valueOf(purchaseEntry.getItemDetailsFromPurchaseEntry().get(OR.getProperty("testItem")).get("Pur. Rt")));
		Assert.assertTrue(purRate<39);
		log.info("====== Test Completed =======");
	}
}
