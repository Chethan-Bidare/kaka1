package com.pharmeasy.MercuryUI.PurchaseEntry;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.pharmeasy.MercuryUI.Base.TestBase;
import com.pharmeasy.MercuryUI.Page.LandingPage;
import com.pharmeasy.MercuryUI.Page.PurchaseEntryPage;

public class TC024_VerifyPurchaseEntryCreationFromPendingEntryPage extends TestBase{

	public static final Logger log = Logger.getLogger(TC024_VerifyPurchaseEntryCreationFromPendingEntryPage.class.getSimpleName());
	
	@Test(priority=0)
	public void verifyPurchaseEntryCreationFromPendingEntryPage() throws InterruptedException {
		
		log.info("=========== Test Started ===========");
		
		LandingPage landingPage = new LandingPage();
		PurchaseEntryPage purchaseEntry = new PurchaseEntryPage();
		landingPage.loginByCredentials(OR.getProperty("userEmail"),OR.getProperty("userPwd"));
		Thread.sleep(5000);
		landingPage.selectMainMenuOption("Pur.Entry");
		Thread.sleep(1000);
		landingPage.selectSubMainMenuoption("Open Purchase Entries");
		landingPage.clickOnNewEntry();
		landingPage.selectVendor(OR.getProperty("vendorName"));
		String invNumber = purchaseEntry.getInvoiceNum();
		purchaseEntry.enterInvoiceNumber(invNumber);
		purchaseEntry.enterItemDetailsWithoutBatchCreation(OR.getProperty("testItem"),"5","20");
		purchaseEntry.ClickOnaddRowButtonToAddItem();
		Thread.sleep(1000);
		purchaseEntry.enterItemDetailsWithoutBatchCreation(OR.getProperty("testItem2"),"5","10");
		purchaseEntry.clickOnSaveAndContinueButton();
		Thread.sleep(2000);
		
		landingPage.selectMainMenuOption("Pur.Entry");
		Thread.sleep(1000);
		landingPage.selectSubMainMenuoption("Pending Entries");
		Thread.sleep(2000);
		purchaseEntry.clickOnPendingEntriesBasedOnInvoiceNumber(invNumber);
		Thread.sleep(1000);
		purchaseEntry.clickOnReviewButton();
		Thread.sleep(2000);
		purchaseEntry.clickOnCreateButton();
		Thread.sleep(2000);
		
		Assert.assertEquals(landingPage.getToastMSG(), APP.getProperty("PurEntrySuccessMSG"));
		
		log.info("=========== Test Completed ===========");
	}
	
}
