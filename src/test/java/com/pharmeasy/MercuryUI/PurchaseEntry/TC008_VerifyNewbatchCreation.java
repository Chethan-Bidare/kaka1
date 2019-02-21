package com.pharmeasy.MercuryUI.PurchaseEntry;

import org.testng.annotations.Test;
import org.apache.log4j.Logger;
import com.pharmeasy.MercuryUI.Base.TestBase;
import com.pharmeasy.MercuryUI.Page.LandingPage;
import com.pharmeasy.MercuryUI.Page.PurchaseEntryPage;

public class TC008_VerifyNewbatchCreation extends TestBase{

	
	public static final Logger log = Logger.getLogger(TC008_VerifyNewbatchCreation.class.getSimpleName());
	
	
	@Test
	public void verifyNewBatch() throws InterruptedException {
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
		purchaseEntry.enterItemDetailsWithoutBatchCreation(OR.getProperty("testItemWithoutBatch"),"5","20");
		purchaseEntry.createBatch("test1", "30", "12", "22");
		purchaseEntry.clickOnReviewButton();
		Thread.sleep(1000);
		purchaseEntry.clickOnCreateButton();
	}
}
