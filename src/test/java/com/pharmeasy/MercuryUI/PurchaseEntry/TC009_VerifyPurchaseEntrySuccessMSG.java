package com.pharmeasy.MercuryUI.PurchaseEntry;

import org.testng.annotations.Test;
import org.apache.log4j.Logger;
import org.testng.Assert;
import com.pharmeasy.MercuryUI.Base.TestBase;
import com.pharmeasy.MercuryUI.Page.GatepassPage;
import com.pharmeasy.MercuryUI.Page.LandingPage;
import com.pharmeasy.MercuryUI.Page.PurchaseEntryPage;

public class TC009_VerifyPurchaseEntrySuccessMSG extends TestBase{

public static final Logger log = Logger.getLogger(TC009_VerifyPurchaseEntrySuccessMSG.class.getSimpleName());
	
	
	@Test
	public void verifyPurEntry() throws InterruptedException {
		log.info(" ====== Test Started ======");
		LandingPage landingPage = new LandingPage();
		PurchaseEntryPage purchaseEntry = new PurchaseEntryPage();
		GatepassPage gp = new GatepassPage();
		landingPage.loginByCredentials(OR.getProperty("userEmail"),OR.getProperty("userPwd"));
		Thread.sleep(5000);
		landingPage.selectMainMenuOption("Pur.Entry");
		Thread.sleep(1000);
		landingPage.selectSubMainMenuoption("Open Purchase Entries");
		landingPage.clickOnNewEntry();
		landingPage.selectVendor(OR.getProperty("vendorName"));
		purchaseEntry.enterInvoiceNumber(purchaseEntry.getInvoiceNum());
		purchaseEntry.enterItemDetailsWithoutBatchCreation(OR.getProperty("testItem"),"5","20");
		purchaseEntry.clickOnReviewButton();
		Thread.sleep(2000);
		purchaseEntry.clickOnCreateButton();
		Thread.sleep(2000);
		
		Assert.assertEquals(gp.getToastMSG(), APP.getProperty("PurEntrySuccessMSG"));
		
		log.info(" ====== Test Completed ======");
	}
}
