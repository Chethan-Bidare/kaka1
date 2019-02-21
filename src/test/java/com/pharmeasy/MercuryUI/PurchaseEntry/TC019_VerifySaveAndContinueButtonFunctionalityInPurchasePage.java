package com.pharmeasy.MercuryUI.PurchaseEntry;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.pharmeasy.MercuryUI.Base.TestBase;
import com.pharmeasy.MercuryUI.Page.LandingPage;
import com.pharmeasy.MercuryUI.Page.PurchaseEntryPage;

public class TC019_VerifySaveAndContinueButtonFunctionalityInPurchasePage extends TestBase{

	public static final Logger log = Logger.getLogger(TC019_VerifySaveAndContinueButtonFunctionalityInPurchasePage.class.getSimpleName());
	
	/* Create Purchase Entry
	 * Click on Save And Continue button
	 * Verify the entry is saved in Pending Entries Page
	 */
	@Test(priority=0)
	public void verifySaveAndContinueFunctionality() throws InterruptedException {
		
		log.info(" ====== Test Started ======");
		
		LandingPage landingPage = new LandingPage();
		PurchaseEntryPage purchaseEntry = new PurchaseEntryPage();
		landingPage.loginByCredentials(OR.getProperty("userEmail"),OR.getProperty("userPwd"));
		Thread.sleep(5000);
		landingPage.selectMainMenuOption("Pur.Entry");
		Thread.sleep(1000);
		landingPage.selectSubMainMenuoption("Open Purchase Entries");
		landingPage.clickOnNewEntry();
		String invNumber = purchaseEntry.getInvoiceNum();
		landingPage.selectVendor(OR.getProperty("vendorName"));
		purchaseEntry.enterInvoiceNumber(invNumber);
		purchaseEntry.enterItemDetailsWithoutBatchCreation(OR.getProperty("testItem"),"5","20");
		purchaseEntry.enterSchemeQty("2");
		purchaseEntry.enterDiscPercentage("10");
		purchaseEntry.clickOnSaveAndContinueButton();
		Thread.sleep(5000);
		landingPage.selectMainMenuOption("Pur.Entry");
		Thread.sleep(1000);
		landingPage.selectSubMainMenuoption("Pending Entries");
		Thread.sleep(2000);
	
		Assert.assertTrue(purchaseEntry.fetchDetailsFromPendingEntriesPage().contains(invNumber));
				
		log.info("===== Test Completed =====");
	}
}
