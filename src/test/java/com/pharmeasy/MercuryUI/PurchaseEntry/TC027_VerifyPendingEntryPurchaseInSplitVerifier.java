package com.pharmeasy.MercuryUI.PurchaseEntry;

import static org.testng.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.pharmeasy.MercuryUI.Base.TestBase;
import com.pharmeasy.MercuryUI.Page.LandingPage;
import com.pharmeasy.MercuryUI.Page.PurchaseEntryPage;

public class TC027_VerifyPendingEntryPurchaseInSplitVerifier extends TestBase{

public static final Logger log = Logger.getLogger(TC027_VerifyPendingEntryPurchaseInSplitVerifier.class.getSimpleName());
	
	/*
	 * Create a purchase Entry and Save as a Draft
	 * Verify that Purchase in Pending ENtry page
	 * Verify Purchase in Split Verifier which was created from Pending entry page
	 */
	
	@Test
	public void verifyPendingPurchaseInSplitVerifier() throws InterruptedException {

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
		Thread.sleep(1000);
		purchaseEntry.clickOnSaveAndContinueButton();
		Thread.sleep(2000);
		landingPage.selectMainMenuOption("Pur.Entry");
		Thread.sleep(1000);
		landingPage.selectSubMainMenuoption("Pending Entries");
		
		purchaseEntry.clickOnPendingEntriesBasedOnInvoiceNumber(invNumber);
		Thread.sleep(1000);
		purchaseEntry.clickOnReviewButton();
		Thread.sleep(2000);
		purchaseEntry.clickOnCreateButton();
		Thread.sleep(2000);
		driver.navigate().back();
		landingPage.selectMainMenuOption("Pur.Entry");
		Thread.sleep(1000);
		landingPage.selectSubMainMenuoption("Split Verifier");
		Thread.sleep(2000);
		landingPage.searchByInvoiceNumber(invNumber);
		Thread.sleep(1000);
		
		assertTrue(purchaseEntry.fetchDetailsFromPendingEntriesPage().contains(invNumber));
		
		log.info("=========== Test Completed ===========");
	}
}
